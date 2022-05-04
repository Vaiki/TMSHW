package com.example.tmshw.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmshw.R
import com.example.tmshw.databinding.FragmentDolgunecBinding
import kotlinx.coroutines.*
import java.lang.Math.random
import kotlin.random.Random


class DolgunecFragment : Fragment() {
    private lateinit var binding: FragmentDolgunecBinding
    private val winners = mutableListOf<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dolgunec, container, false)

        binding.btnStart.setOnClickListener {
            randomData()
            binding.btnStart.isEnabled = false
        }
        return binding.root
    }

    private fun randomData() {

        GlobalScope.launch(Dispatchers.Main) {
            for (i in 1..100) {
                delay(Random.nextLong(1, 400))
                binding.progressHorizontal1.setProgress(i, true)
                if (i == 100) {
                    when {
                        binding.tvWin1.text == "" -> {
                            binding.tvWin1.text = getString(R.string.farmer1)
                            binding.starFarmer1.visibility = View.VISIBLE
                        }
                        binding.tvWin2.text == "" -> binding.tvWin2.text =
                            getString(R.string.farmer1)
                        else -> binding.tvWin3.text = getString(R.string.farmer1)
                    }
                }
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 1..100) {
                delay(Random.nextLong(1, 400))
                binding.progressHorizontal2.setProgress(i, true)
                if (i == 100) {
                    when {
                        binding.tvWin1.text == "" -> {
                            binding.tvWin1.text = getString(R.string.farmer2)
                            binding.starFarmer2.visibility = View.VISIBLE
                        }
                        binding.tvWin2.text == "" -> binding.tvWin2.text =
                            getString(R.string.farmer2)
                        else -> binding.tvWin3.text = getString(R.string.farmer2)
                    }
                }
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 1..100) {
                delay(Random.nextLong(1, 400))
                binding.progressHorizontal3.setProgress(i, true)
                if (i == 100) {
                    when {
                        binding.tvWin1.text == "" -> {
                            binding.tvWin1.text = getString(R.string.farmer3)
                            binding.starFarmer3.visibility = View.VISIBLE
                        }
                        binding.tvWin2.text == "" -> binding.tvWin2.text =
                            getString(R.string.farmer3)
                        else -> binding.tvWin3.text = getString(R.string.farmer3)
                    }
                }
            }
        }
    }
}

