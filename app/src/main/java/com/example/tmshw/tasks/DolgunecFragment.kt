package com.example.tmshw.tasks

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmshw.R
import com.example.tmshw.databinding.FragmentDolgunecBinding
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlinx.coroutines.*
import kotlin.random.Random


class DolgunecFragment : Fragment() {
    private lateinit var binding: FragmentDolgunecBinding
    private val winners = mutableMapOf<String, Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dolgunec, container, false)

        binding.btnStart.setOnClickListener {
            GlobalScope.launch(Dispatchers.Main) {
                mining("Farmer 1", binding.progressHorizontal1)
                mining("Farmer 2", binding.progressHorizontal2)
                mining("Farmer 3", binding.progressHorizontal3)
                delay(8000)
                win()
            }
            binding.btnStart.isEnabled = false
        }
        return binding.root
    }

    private fun mining(company: String, progressHorizontal1: LinearProgressIndicator) {
        GlobalScope.launch(Dispatchers.Main) {
            var harwest = 0
            for (i in 1..15) {
                delay(500)
                harwest += Random.nextInt(0, 300)
                progressHorizontal1.setProgress(harwest, true)
            }
            winners[company] = harwest

        }
    }

    @SuppressLint("SetTextI18n")
    private fun win() {
        val podium = mutableListOf<String>()
        val result = winners.toList().sortedBy { (_, Int) -> Int }.reversed().toMap()
        for (i in result) {
            podium.add(i.key)
        }
        binding.tvWin1.text = setTv(podium[0], result[podium[0]])
        binding.tvWin2.text = setTv(podium[1], result[podium[1]])
        binding.tvWin3.text = setTv(podium[2], result[podium[2]])
    }

    private fun setTv(podium: String, value: Int?): String = "$podium - $value t."

}


