package com.jesil.games.letterlink.game.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jesil.games.letterlink.core.LetterlinkApp
import com.jesil.games.letterlink.core.data.AssetWordDictionary
import com.jesil.games.letterlink.core.domain.GameEngine
import com.jesil.games.letterlink.core.domain.GameHistoryRepository
import com.jesil.games.letterlink.core.domain.GameRecord
import com.jesil.games.letterlink.core.domain.GameSettings
import com.jesil.games.letterlink.core.domain.GameState
import com.jesil.games.letterlink.core.domain.TurnTimer
import com.jesil.games.letterlink.core.domain.WordDictionary
import com.jesil.games.letterlink.game.model.GameUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(
    private val settings: GameSettings,
    private val historyRepo: GameHistoryRepository,
    private val loadDictionary: suspend () -> WordDictionary,
) : ViewModel() {
    private val _ui = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _ui.asStateFlow()

    // Finished games, for a history screen.
    val pastGames: StateFlow<List<GameRecord>> = historyRepo.records
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    private val timer = TurnTimer(viewModelScope)
    private lateinit var engine: GameEngine
    private var hintsOn = false

    val targetScore: Int get() = settings.targetScore

    fun onAction(actions: GameActions) {
        when (actions) {
            is GameActions.OnTileSelected -> onTileSelected(actions.tileIndex)
            is GameActions.OnLetterPicked -> onLetterPicked(actions.letter)
            is GameActions.OnHint -> onToggleHints()
            is GameActions.OnSubmit -> onSubmit()
            is GameActions.OnClear -> onClearDraft()
        }
    }

    init {
        viewModelScope.launch {
            engine = GameEngine(loadDictionary(), settings)
            onNewGame()
        }
        viewModelScope.launch {
            timer.secondsLeft.collect { s -> _ui.update { it.copy(secondsLeft = s) } }
        }
    }

    fun onNewGame() {
        if (!::engine.isInitialized) return
        timer.stop()
        hintsOn = false
        val game = engine.newGame()
        _ui.value = GameUiState(
            game = game,
            draft = game.currentWord,
            secondsLeft = settings.turnSeconds,
        )
    }

    // Called when the player taps "ready" on the hand-over screen.
    fun onReady() {
        val s = _ui.value
        if (s.game == null || !s.awaitingReady) return
        _ui.update { it.copy(awaitingReady = false) }
        timer.start(settings.turnSeconds) { play { engine.timeUp(it) } }
    }

    private fun onTileSelected(index: Int) {
        val s = _ui.value
        val game = s.game ?: return
        if (s.awaitingReady) return
        _ui.value = withDraft(game, index, game.currentWord)
    }

    private fun onLetterPicked(letter: Char) {
        val s = _ui.value
        val game = s.game ?: return
        val i = s.selectedIndex ?: return
        if (s.awaitingReady) return
        val draft = game.currentWord.replaceRange(i, i + 1, letter.lowercase())
        _ui.value = withDraft(game, i, draft)
    }

    private fun onToggleHints() {
        hintsOn = !hintsOn
        val s = _ui.value
        val game = s.game ?: return
        _ui.value = withDraft(game, s.selectedIndex, s.draft)
    }

    // Delete button: drop the draft and deselect the tile.
    private fun onClearDraft() {
        val s = _ui.value
        val game = s.game ?: return
        if (s.awaitingReady || s.paused) return
        _ui.value = withDraft(game, null, game.currentWord)
    }

    fun onPause() {
        val s = _ui.value
        val game = s.game ?: return
        if (s.awaitingReady || s.paused || game.winnerId != null) return
        timer.stop()
        _ui.update { it.copy(paused = true) }
    }

    fun onResume() {
        val s = _ui.value
        if (!s.paused) return
        _ui.update { it.copy(paused = false) }
        timer.start(s.secondsLeft.coerceAtLeast(1)) { play { engine.timeUp(it) } }
    }

    // A wrong word is allowed through on purpose: the engine turns it into a miss.
    private fun onSubmit() {
        val s = _ui.value
        val game = s.game ?: return
        if (s.awaitingReady || s.draft == game.currentWord) return
        play { engine.submit(it, s.draft) }
    }

    private fun withDraft(game: GameState, selected: Int?, draft: String) = _ui.value.copy(
        selectedIndex = selected,
        draft = draft,
        draftProblem = if (draft == game.currentWord) null else engine.check(game, draft),
        hintLetters = if (hintsOn && selected != null) engine.hintLetters(
            game,
            selected
        ) else emptySet(),
    )

    // Runs one engine step, then resets the screen for the next turn.
    private fun play(step: (GameState) -> GameState) {
        val before = _ui.value.game ?: return
        val after = step(before)
        timer.stop()
        hintsOn = false
        _ui.value = GameUiState(
            game = after,
            draft = after.currentWord,
            secondsLeft = settings.turnSeconds,
            awaitingReady = after.winnerId == null,
        )
        after.winnerId?.let { winner ->
            viewModelScope.launch {
                historyRepo.add(
                    GameRecord(
                        finishedAt = System.currentTimeMillis(),
                        players = after.players,
                        winnerId = winner,
                    )
                )
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as LetterlinkApp
                GameViewModel(
                    settings = GameSettings(),
                    historyRepo = app.historyRepo,
                    loadDictionary = { AssetWordDictionary.fromAssets(app) },
                )
            }
        }
    }


}