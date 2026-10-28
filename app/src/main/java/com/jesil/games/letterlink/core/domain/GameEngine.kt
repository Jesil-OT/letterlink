package com.jesil.games.letterlink.core.domain

class GameEngine(
    private val dictionary: WordDictionary,
    private val settings: GameSettings,
) {
    fun newGame() = GameState(
        players = settings.playerNames.mapIndexed { i, name -> Player(i, name) },
        history = listOf(dictionary.randomStart()),
    )

    fun check(state: GameState, word: String): Reason? = when{
        !dictionary.isWord(word) -> Reason.NOT_A_WORD
        word in state.history -> Reason.ALREADY_USED
        !differByOne(state.currentWord, word) -> Reason.NOT_ONE_LETTER
        else -> null
    }

    fun submit(state: GameState, word: String): GameState {
        if (state.winnerId != null) return state
        val reason = check(state, word)
        return if (reason == null) accept(state, word) else miss(state, reason)
    }

    fun timeUp(state: GameState): GameState =
        if (state.winnerId != null) state else miss(state, null)

    // Letters that would make a legal word if placed in the given tile.
    fun hintLetters(state: GameState, index: Int): Set<Char> =
        legalMoves(state)
            .filter { it[index] != state.currentWord[index] }
            .map { it[index] }
            .toSet()

    private fun legalMoves(state: GameState): List<String> =
        dictionary.neighbors(state.currentWord).filter { it !in state.history }

    private fun accept(state: GameState, word: String): GameState {
        val points = if (state.stealPending) 2 else 1
        val players = state.players.mapIndexed { i, p ->
            if (i == state.activeIndex) p.copy(scores = p.scores + points) else p
        }
        val scorer = players[state.activeIndex]
        val next = state.copy(
            players = players,
            history = state.history + word,
            activeIndex = (state.activeIndex + 1) % players.size,
            stealPending = false,
            missesInRow = 0,
            restarted = false,
            lastResult = TurnResult.Scored(scorer.id, points),
            winnerId = if (scorer.scores >= settings.targetScore) scorer.id else null,
        )
        // Dead end: nobody can continue from this word, so pick a fresh one.
        return if (next.winnerId == null && legalMoves(next).isEmpty()) restart(next) else next
    }

    private fun miss(state: GameState, reason: Reason?): GameState {
        val misses = state.missesInRow + 1
        val next = state.copy(
            activeIndex = (state.activeIndex + 1) % state.players.size,
            stealPending = true,
            missesInRow = misses,
            restarted = false,
            lastResult = TurnResult.Missed(state.activePlayer.id, reason),
        )
        return if (misses >= 2) restart(next) else next
    }

    private fun restart(state: GameState) = state.copy(
        history = listOf(dictionary.randomStart()),
        stealPending = false,
        missesInRow = 0,
        restarted = true,
    )

    private fun differByOne(a: String, b: String): Boolean =
        a.length == b.length && a.zip(b).count { (x,y) -> x!=y } == 1


}