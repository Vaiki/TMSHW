package com.example.tmshw.tasks.room

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RoomApp:Application() {

    // No need to cancel this scope as it'll be torn down with the process
    //Заполнение базы данных не связано с жизненным циклом пользовательского интерфейса,
    // поэтому не следует использовать CoroutineScope, например viewModelScope.
    // Это связано с жизненным циклом приложения.
    // Используем applicationScope.
    private val applicationScope =  CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MyDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { EmployeeRepository(database.employeeDao()) }
}