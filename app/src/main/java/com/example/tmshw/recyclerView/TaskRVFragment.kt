package com.example.tmshw.recyclerView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmshw.R
import com.example.tmshw.data.Task
import com.example.tmshw.databinding.FragmentRecyclerViewBinding
import com.example.tmshw.databinding.FragmentTimerBinding


class TaskRVFragment : Fragment() {
    private var _binding: FragmentRecyclerViewBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        val taskList = mutableListOf<Task>(
            Task(1, "Arithmetic"),
            Task(2, "Timer"),
            Task(3, "Dolgunec"),
            Task(4, "Flags"),
            Task(5, "ViewModel"),
            Task(6, "Player"),
            Task(7, "Room"),
            Task(8, "")
        )

        val adapter = MyRecyclerViewAdapter(taskList)
        binding.taskRecycler.adapter = adapter
        binding.taskRecycler.layoutManager = LinearLayoutManager(context)

        return binding.root


    }
}