package com.jesil.games.letterlink.core.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// One finished game. Later this becomes a Room @Entity (players can be stored as JSON
// with a TypeConverter, or as a separate table).
data class GameRecord(
    val id: Long = 0,
    val finishedAt: Long,
    val players: List<Player>,
    val winnerId: Int,
)

// The ViewModel only knows this interface. Swap the implementation when you add Room.
interface GameHistoryRepository {
    val records: Flow<List<GameRecord>>   // newest first
    suspend fun add(record: GameRecord)
    suspend fun clear()
}

// Lives only as long as the app process. Replace with a Room-backed class later.
class InMemoryGameHistoryRepository : GameHistoryRepository {
    private val _records = MutableStateFlow<List<GameRecord>>(emptyList())
    override val records: Flow<List<GameRecord>> = _records.asStateFlow()

    override suspend fun add(record: GameRecord) {
        _records.update { listOf(record) + it }
    }

    override suspend fun clear() {
        _records.value = emptyList()
    }
}
