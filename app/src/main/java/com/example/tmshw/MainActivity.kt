package com.example.tmshw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels

import com.example.tmshw.databinding.ActivityMainBinding
import com.example.tmshw.recyclerView.TaskRVFragment
import com.example.tmshw.tasks.room.MainViewModel
//import com.example.tmshw.tasks.room.MainViewModelFactory
import com.example.tmshw.tasks.room.RoomApp

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private val viewModel: MainViewModel by viewModels {
//        MainViewModelFactory((application as RoomApp).repository)
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       // viewModel
        setContentView(binding.root)
        val recyclerViewFragment = TaskRVFragment()
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view_tag, recyclerViewFragment)
            commit()
        }
    }
}