package com.jesil.games.letterlink.core

import android.app.Application
import com.jesil.games.letterlink.core.domain.GameHistoryRepository
import com.jesil.games.letterlink.core.domain.InMemoryGameHistoryRepository

class LetterlinkApp : Application() {
    val historyRepo: GameHistoryRepository by lazy { InMemoryGameHistoryRepository() }
}
