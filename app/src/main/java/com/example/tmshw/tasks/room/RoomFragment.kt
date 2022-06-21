package com.example.tmshw.tasks.room

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
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
    private var imageUri: Uri? = null

    private val viewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentRoomBinding.bind(view)
        initMenuPositionAndDepartment(viewModel.itemsMenuPosition, viewModel.itemsMenuDepartment)
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

    private fun saveOrUpdateOrDelete() {
        viewModel.isSelected.observe(viewLifecycleOwner) {
            when (it) {
                false -> {
                    binding.btnSaveOrUpdate.setOnClickListener {
                        if (TextUtils.isEmpty(binding.teName.text) || TextUtils.isEmpty(binding.teLastName.text))
                            Toast.makeText(context, "Не все поля заполнены", Toast.LENGTH_LONG)
                                .show()
                        else {
                            selectAvatar()
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
                        binding.btnSaveOrUpdate.text = getString(R.string.save)
                    }
                }
            }
        }
    }

    private fun initMenuPositionAndDepartment(
        listPosition: List<String>,
        listDepartment: List<String>,
    ) {
        val adapterPos = ArrayAdapter(requireContext(), R.layout.item_menu, listPosition)
        val adapterDep = ArrayAdapter(requireContext(), R.layout.item_menu, listDepartment)
        binding.autoCompleteDepartment.setAdapter(adapterDep)
        binding.autoCompletePosition.setAdapter(adapterPos)
    }

    private fun itemClicked(employee: Employee, fragmentRoomBinding: FragmentRoomBinding) {
        with(fragmentRoomBinding) {
            idEmployee = employee.id
            teName.setText(employee.name)
            teLastName.setText(employee.lastName)
            autoCompletePosition.setText(employee.position)
            autoCompleteDepartment.setText(employee.department)
            avatarUrl = employee.avatarUri
            btnSaveOrUpdate.setText(R.string.update)
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


    //Выбор изображения из галереи
    private fun selectAvatar(): String {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startForResult.launch(gallery)
        return imageUri.toString()
    }

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(),
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            imageUri = it.data?.data
            viewModel.insert(Employee(idEmployee,
                binding.teName.text.toString(),
                binding.teLastName.text.toString(),
                binding.autoCompletePosition.text.toString(),
                binding.autoCompleteDepartment.text.toString(),
                imageUri.toString()))
        clearView()
        }
    }
}