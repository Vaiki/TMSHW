package com.example.tmshw

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmshw.databinding.FragmentTaskOneBinding


class TaskOneFragment : Fragment() {
    private var binding: FragmentTaskOneBinding? = null
var intA = 0
    var intB = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_one, container, false)




        return binding?.root
    }


}