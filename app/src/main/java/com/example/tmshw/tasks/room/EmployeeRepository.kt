package com.example.tmshw.tasks.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.Flow

class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val employees = employeeDao.getAllEmployee()

    suspend fun insert(employee: Employee) = employeeDao.insertEmployee(employee)

    suspend fun update(employee: Employee) = employeeDao.updateEmployee(employee)

    suspend fun delete(employee: Employee) = employeeDao.deleteEmployee(employee)

    fun getAllEmployeeFromDepartment(department: String): Flow<List<Employee>> =
        employeeDao.getAllEmployeeFromDepartment(department)

    suspend fun getEmployee(id: Int) = employeeDao.getEmployee(id)


//    companion object {
//        //for Singleton instantiation
//        @Volatile
//        private var instance: EmployeeRepository? = null
//        fun getInstance(employeeDao: EmployeeDao) =
//            instance ?: synchronized(this) {
//                instance ?: EmployeeRepository(employeeDao).also { instance = it }
//            }
//    }


}