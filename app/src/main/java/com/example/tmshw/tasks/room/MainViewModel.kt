package com.example.tmshw.tasks.room

import androidx.lifecycle.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainViewModel(private val employeeRepository: EmployeeRepository) : ViewModel() {


    val isSelected = MutableLiveData<Boolean>(false)


    val getAllEmployee: LiveData<List<Employee>> = employeeRepository.employees.asLiveData()

    fun insert(employee: Employee) = viewModelScope.launch { employeeRepository.insert(employee) }

    fun update(employee: Employee): Job =
        viewModelScope.launch { employeeRepository.update(employee) }

    fun delete(employee: Employee) = viewModelScope.launch { employeeRepository.delete(employee) }

    fun getAllEmployeeFromDepartment(department: String) =
        viewModelScope.launch { employeeRepository.getAllEmployeeFromDepartment(department) }

    val itemsMenuPosition = listOf<String>("Ведущий специалист",
        "Инженер",
        "HR",
        "Cпециалист по развитию персонала",
        "Менеджмент")
    val itemsMenuDepartment = listOf("УМТО", "ТЭЦ", "УКС", "Отдел кадров", "Менеджмент")

    fun getEmployeeById(id: Int): Job = viewModelScope.launch { employeeRepository.getEmployee(id) }

}


//class MainViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return MainViewModel(repository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
