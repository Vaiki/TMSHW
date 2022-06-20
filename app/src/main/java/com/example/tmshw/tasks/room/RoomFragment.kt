package com.example.tmshw.tasks.room

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tmshw.R
import com.example.tmshw.databinding.FragmentRoomBinding
import com.example.tmshw.tasks.room.adapter.EmployeeAdapter

class RoomFragment : Fragment(R.layout.fragment_room) {
    private var _binding: FragmentRoomBinding? = null
    private val binding get() = _binding!!
    private var avatarUrl: String = ""
    private var idEmployee = 0

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoomBinding.bind(view)
        initMenuPosition(viewModel.itemsMenuPosition)
        initMenuDepartment(viewModel.itemsMenuDepartment)

        initRecyclerView()
        saveOrUpdateOrDelete()






    }

    private fun initRecyclerView() {
        binding.rvEmployee.layoutManager = LinearLayoutManager(context)
        displayEmployeeList()
    }

    private fun displayEmployeeList() {
        viewModel.getAllEmployee.observe(viewLifecycleOwner, {
            val adapter =
                EmployeeAdapter(it) { selectedItem: Employee -> itemClicked(selectedItem, binding) }
            binding.rvEmployee.adapter = adapter

        })
    }

    private fun saveOrUpdateOrDelete(){
        viewModel.isSelected.observe(viewLifecycleOwner) {
            when (it) {
                false -> {
                    binding.btnSaveOrUpdate.setOnClickListener {
                        if (TextUtils.isEmpty(binding.teName.text) || TextUtils.isEmpty(binding.teLastName.text))
                            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_LONG)
                                .show()
                        else {
                            viewModel.insert(getEmployee())
                            clearView()
                        }
                    }
                }
                true -> {
                    binding.btnSaveOrUpdate.setOnClickListener {
                        viewModel.update(getEmployee())

                        binding.btnSaveOrUpdate.text = getString(R.string.save)
                        viewModel.isSelected.value = false

                        clearView()
                    }
                    binding.btnDelete.setOnClickListener {
                        viewModel.delete(getEmployee())
                        clearView()
                    }
                }
            }
        }
    }

    private fun initMenuPosition(items: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_menu, items)
        binding.autoCompletePosition.setAdapter(adapter)
    }

    private fun initMenuDepartment(items: List<String>) {
        val adapter = ArrayAdapter(requireContext(), R.layout.item_menu, items)
        binding.autoCompleteDepartment.setAdapter(adapter)
    }

    private fun itemClicked(employee: Employee, fragmentRoomBinding: FragmentRoomBinding) {
        with(fragmentRoomBinding) {
            idEmployee = employee.id
            teName.setText(employee.name)
            teLastName.setText(employee.lastName)
            autoCompletePosition.setText(employee.position)
            autoCompleteDepartment.setText(employee.department)
            avatarUrl = employee.avatarUri
            btnSaveOrUpdate.text = getString(R.string.update)
            viewModel.isSelected.value = true
        }
    }

    private fun clearView() {
        idEmployee = 0
        avatarUrl = ""
        with(binding) {
            teName.setText("")
            teLastName.setText("")
            autoCompletePosition.setText("")
            autoCompleteDepartment.setText("")
        }
    }

    private fun getEmployee(): Employee = Employee(idEmployee,
        binding.teName.text.toString(),
        binding.teLastName.text.toString(),
        binding.autoCompletePosition.text.toString(),
        binding.autoCompleteDepartment.text.toString(),
        avatarUrl)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}