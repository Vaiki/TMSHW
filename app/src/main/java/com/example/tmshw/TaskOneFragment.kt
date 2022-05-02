package com.example.tmshw

import android.os.Bundle
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.tmshw.databinding.FragmentTaskOneBinding


class TaskOneFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentTaskOneBinding
    private var intA = 0
    private var intB = 0
    private var a = 0
    private var b = 0
    private var c = 0
    private var d = 0
    private var count = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_one, container, false)

        //task 1
        binding.btnABTask1.setOnClickListener(this)

        //task 2
        binding.btnMaxTask2.setOnClickListener(this)

        //task 3
        binding.etRating.filters = arrayOf<InputFilter>(MinMaxFilter(0, 100))
        binding.etRating.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString() == "") {

                    binding.answer3.text = "-"
                } else {
                    when (s.toString().toInt()) {
                        in 0..19 -> binding.answer3.text = "F"
                        in 20..39 -> binding.answer3.text = "E"
                        in 40..59 -> binding.answer3.text = "D"
                        in 60..74 -> binding.answer3.text = "C"
                        in 75..89 -> binding.answer3.text = "B"
                        in 90..100 -> binding.answer3.text = "A"
                    }
                }

            }

            override fun afterTextChanged(s: Editable?) {

            }
        })

        //task 4
        binding.btnInsertTask4.setOnClickListener(this)

        //task 5
        binding.tvAns5.text = sumNumbs().toString()
        binding.tvAns52.text = count.toString()

        //task 6
        binding.etDec.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString() != "") {
                    binding.tvAns6.text = Integer.toBinaryString(s.toString().toInt())
                } else binding.tvAns6.text = "-"
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        return binding.root
    }

    //for task 3
    inner class MinMaxFilter() : InputFilter {
        private var intMin: Int = 0
        private var intMax: Int = 0

        // Initialized
        constructor(minValue: Int, maxValue: Int) : this() {
            this.intMin = minValue
            this.intMax = maxValue
        }

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dStart: Int,
            dEnd: Int,
        ): CharSequence? {
            try {
                val input = Integer.parseInt(dest.toString() + source.toString())
                if (isInRange(intMin, intMax, input)) {
                    return null
                }
            } catch (e: NumberFormatException) {
                e.printStackTrace()
            }
            return ""
        }

        // Check if input c is in between min a and max b and
        // returns corresponding boolean
        private fun isInRange(a: Int, b: Int, c: Int): Boolean {
            return if (b > a) c in a..b else c in b..a
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.btn_a_b_task1 -> {

                intA = when (binding.etA.text.toString()) {
                    "" -> 0
                    else -> binding.etA.text.toString().toInt()
                }

                intB = when (binding.etB.text.toString()) {
                    "" -> 0
                    else -> binding.etB.text.toString().toInt()
                }
                if ((intA % 2) == 0) {

                    binding.tvSign.text = "+"
                    binding.tvSign.visibility = View.VISIBLE
                    binding.tvAnswer1.text = (intA + intB).toString()

                } else {
                    binding.tvSign.text = "*"
                    binding.tvSign.visibility = View.VISIBLE
                    binding.tvAnswer1.text = (intA * intB).toString()
                }
            }
            R.id.btn_max_task2 -> {

                a = when (binding.etATask2.text.toString()) {
                    "" -> 0
                    else -> binding.etATask2.text.toString().toInt()
                }

                b = when (binding.etBTask2.text.toString()) {
                    "" -> 0
                    else -> binding.etBTask2.text.toString().toInt()
                }

                c = when (binding.etCTask2.text.toString()) {
                    "" -> 0
                    else -> binding.etCTask2.text.toString().toInt()
                }

                val max = if ((a * b * c) > (a + b + c)) a * b * c else a + b + c

                binding.btnMaxTask2.text = max.toString()

            }
            R.id.btn_insert_task4 -> {
                a = when (binding.etATask2.text.toString()) {
                    "" -> 0
                    else -> binding.etATask2.text.toString().toInt()
                }

                b = when (binding.etBTask2.text.toString()) {
                    "" -> 0
                    else -> binding.etBTask2.text.toString().toInt()
                }

                c = when (binding.etCTask2.text.toString()) {
                    "" -> 0
                    else -> binding.etCTask2.text.toString().toInt()
                }
                d = when (binding.etDTask3.text.toString()) {
                    "" -> 0
                    else -> binding.etDTask3.text.toString().toInt()
                }

                if ((a <= c && b <= d) || (a <= d && b <= c)) binding.answer4.text =
                    getString(R.string.fit)
                else binding.answer4.text = getString(R.string.wont_fit)
            }
        }
    }

    //for task 5
    private fun sumNumbs(): Int {
        var sum = 0
        for (num in 1..99) {
            if (num % 2 == 0) {
                sum += num
                count++
            }
        }
        return sum
    }


}