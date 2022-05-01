package com.example.tmshw

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmshw.databinding.FragmentTimerBinding
import kotlinx.coroutines.CoroutineScope
import java.util.*
import java.util.concurrent.TimeUnit


class TimerFragment : Fragment() {

    private lateinit var binding: FragmentTimerBinding
    private var timer: CountDownTimer? = null
    private var timerRunning = false
    private var time = 300000L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false)

        binding.btnStart.setOnClickListener(
            View.OnClickListener {
                startStop()

            }
        )


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun startStop() {
        if (timerRunning) pauseTimer() else startTimer()
    }

    private fun startTimer() {
        timer = object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time = millisUntilFinished
                binding.tvMin.text = (millisUntilFinished / 60000).toString()
                binding.tvSecond.text = (millisUntilFinished % 60000 / 1000).toString()

            }

            override fun onFinish() {

            }
        }.start()
        binding.btnStart.text = "Pause"
        timerRunning = true
    }

    private fun pauseTimer() {
        timer?.cancel()
        binding.btnStart.text = "Start"
        timerRunning = false
    }

}