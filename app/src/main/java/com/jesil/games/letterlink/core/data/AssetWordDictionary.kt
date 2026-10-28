package com.jesil.games.letterlink.core.data

import android.content.Context
import com.jesil.games.letterlink.core.domain.WordDictionary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AssetWordDictionary (
    private val words : Set<String>
): WordDictionary{
    private val starts: List<String> = words.filter { neighbors(it).size >= MIN_NEIGHBORS }

    companion object {
        private const val MIN_NEIGHBORS = 4
        suspend fun fromAssets(
            context: Context,
            fileName: String = "words4.txt",
        ): AssetWordDictionary = withContext(Dispatchers.IO) {
            val words = context.assets.open(fileName).bufferedReader().useLines { lines ->
                lines
                    .map { it.trim().lowercase() }
                    .filter { it.length == 4 && it.all { c -> c in 'a'..'z' } }
                    .toSet()
            }
            AssetWordDictionary(words)
        }
    }

    override fun isWord(word: String): Boolean = word.lowercase() in words

    override fun neighbors(word: String): List<String> = buildList {
        for (i in word.lowercase().indices){
            for (c in 'a'..'z'){
                if (c == word.lowercase()[i]) continue
                val candidate = word.lowercase().replaceRange(i, i + 1, c.toString())
                if (candidate in words) add(candidate)
            }
        }
    }

    override fun randomStart(): String  = starts.random()
}