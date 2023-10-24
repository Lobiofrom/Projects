package com.example.roomcodelab.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.roomcodelab.data.WordRepository
import com.example.roomcodelab.entity.Word
import kotlinx.coroutines.launch

class WordViewModel(
    private val wordRepository: WordRepository
) : ViewModel() {
    val allWords = wordRepository.allWords.asLiveData()

    fun insert(word: Word) {
        viewModelScope.launch {
            wordRepository.insert(word)
        }
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}