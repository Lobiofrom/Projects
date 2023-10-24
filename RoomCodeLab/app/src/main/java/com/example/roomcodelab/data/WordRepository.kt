package com.example.roomcodelab.data

import androidx.annotation.WorkerThread
import com.example.roomcodelab.entity.Word

class WordRepository(
    private val wordDao: WordDao
) {
    val allWords = wordDao.getAlphabetizedWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}