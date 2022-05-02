package com.example.tmshw

import android.text.TextUtils.replace
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.tmshw.databinding.ItemTaskBinding

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
            if (position == 0) {
                val activity = v!!.context as AppCompatActivity
                val taskOneFragment = TaskOneFragment()
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_tag, taskOneFragment).addToBackStack(null)
                    .commit()

            }
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }
}

class MyViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: Task) {
        binding.numTaskTv.text = task.numTask.toString()

    }
}