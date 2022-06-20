package com.example.tmshw.tasks.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmshw.R
import com.example.tmshw.databinding.ItemRoomBinding
import com.example.tmshw.tasks.room.Employee

class EmployeeAdapter(
    private val employeeList: List<Employee>,
    private val fragmentRoomBinding: (Employee) -> Unit,
) :
    RecyclerView.Adapter<EmployeeHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EmployeeHolder(binding)
    }

    override fun onBindViewHolder(holder: EmployeeHolder, position: Int) {

        val employee = employeeList[position]
        holder.itemView.animation= AnimationUtils.loadAnimation(holder.itemView.context,R.anim.recycler_anim)
        holder.bind(employee, fragmentRoomBinding)

    }

    override fun getItemCount(): Int = employeeList.size

}

class EmployeeHolder(private val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(employee: Employee, fragmentRoomBinding: (Employee) -> Unit) {
        with(binding) {
            tvNameEmployee.text = employee.name
            tvLastName.text = employee.lastName
            tvPosition.text = employee.position
            tvDepartmentEmployee.text = employee.department
            Glide.with(itemView.context)
                .load(employee.avatarUri)
                .centerCrop()
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(ivAvatarEmployee)
        }
        binding.ivAvatarEmployee.setOnClickListener {
            fragmentRoomBinding(employee)
        }

    }

}