package com.example.tmshw.tasks.room

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlin.collections.List

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insertEmployee(employee: Employee)

    @Delete
    suspend fun deleteEmployee(employee: Employee)

    @Update
    suspend fun updateEmployee(employee: Employee)

    @Query("DELETE FROM EMPLOYEE_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM EMPLOYEE_TABLE")
    fun getAllEmployee(): Flow<List<Employee>>

    @Query("SELECT * FROM EMPLOYEE_TABLE WHERE department = :department ORDER BY last_name ASC")
    fun getAllEmployeeFromDepartment(department: String): Flow<List<Employee>>

    @Query("SELECT * FROM EMPLOYEE_TABLE WHERE id = (:id)")
    suspend fun getEmployee(id: Int): Employee
}