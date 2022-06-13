package com.example.tmshw.recyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tmshw.tasks.ArithmeticFragment
import com.example.tmshw.R
import com.example.tmshw.data.Task
import com.example.tmshw.tasks.TimerFragment
import com.example.tmshw.databinding.ItemTaskBinding
import com.example.tmshw.tasks.DolgunecFragment
import com.example.tmshw.tasks.player.PlayerFragment


class MyRecyclerViewAdapter(private val taskList: List<Task>) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemTaskBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_task, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(taskList[position])
        holder.itemView.setOnClickListener { v ->
            when (position) {
                0 -> {

                    openFragment(v, ArithmeticFragment())
                }

                1 -> {

                    openFragment(v, TimerFragment())
                }
                2 -> {

                    openFragment(v, DolgunecFragment())
                }
                4 -> {

                }
                5 -> {
                    openFragment(v, PlayerFragment())
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    private fun openFragment(v: View, frag: Fragment) {

        val activity = v.context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view_tag, frag)
            .addToBackStack(null)
            .commit()
    }
}

class MyViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.numTaskTv.text = task.numTask.toString()
        binding.tvDecItem.text = task.description

    }
}