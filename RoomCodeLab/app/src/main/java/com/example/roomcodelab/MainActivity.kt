package com.example.roomcodelab

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomcodelab.databinding.ActivityMainBinding
import com.example.roomcodelab.entity.Word
import com.example.roomcodelab.viewModel.WordViewModel
import com.example.roomcodelab.viewModel.WordViewModelFactory


class MainActivity : AppCompatActivity() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as WordsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WordListAdapter()
        binding.recyclerview.adapter = adapter

        wordViewModel.allWords.observe(this) { words ->
            words?.let { adapter.submitList(it) }
        }

        val newWordActivityContract =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val reply = data?.getStringExtra(NewWordActivity.EXTRA_REPLY)
                    if (reply != null) {
                        val word = Word(reply)
                        wordViewModel.insert(word)
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        R.string.empty_not_saved,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            newWordActivityContract.launch(intent)
        }
    }
}