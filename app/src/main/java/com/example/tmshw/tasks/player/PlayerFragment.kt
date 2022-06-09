package com.example.tmshw.tasks.player

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tmshw.R
import com.example.tmshw.databinding.FragmentPlayerBinding


class PlayerFragment : Fragment() {

    lateinit var binding: FragmentPlayerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_player, container, false)

        binding.btnStart.setOnClickListener {  when (binding.btnStart.text) {
            getString(R.string.start) -> {
                binding.btnStart.text = getString(R.string.pause_btn)
                binding.tvPlayer.text = getString(R.string.play)
            }
            getString(R.string.pause_btn) -> {
                binding.btnStart.text = getString(R.string.start)
                binding.tvPlayer.text = getString(R.string.pause_tv)
            }

        }

            Intent(it.context as AppCompatActivity, MyService::class.java).also { it ->
                (context as AppCompatActivity).startService(it)



            }
        }
        binding.btnStop.setOnClickListener {
            Intent(it.context as AppCompatActivity, MyService::class.java).also { it ->
                (context as AppCompatActivity).stopService(it)
                binding.tvPlayer.text = getString(R.string.stop)
                binding.btnStart.text = getString(R.string.start)
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }


}