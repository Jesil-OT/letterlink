package com.jesil.games.letterlink.core.domain

interface WordDictionary {
    fun isWord(word: String): Boolean
    fun neighbors(word: String): List<String>
    fun randomStart(): String
}