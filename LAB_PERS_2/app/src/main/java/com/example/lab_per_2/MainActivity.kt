package com.example.lab_per_2

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_per_2.adapter.PersonListAdapter
import com.example.lab_per_2.db.PersonRoomDatabase.Companion.getDatabase
import com.example.lab_per_2.repository.PersonRepository
import com.example.lab_per_2.viewModel.PersonViewModel
import com.example.lab_per_2.viewModel.PersonViewModelFactory

class MainActivity : AppCompatActivity() {
    private val wordViewModel: PersonViewModel by viewModels {
        PersonViewModelFactory((application as PersonApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = PersonListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        wordViewModel.allPerson.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.submitList(it) }
        })
    }
}
