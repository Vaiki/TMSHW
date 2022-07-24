package com.example.tmshw.tasks.room

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class RoomApp : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    //Заполнение базы данных не связано с жизненным циклом пользовательского интерфейса,
    // поэтому не следует использовать CoroutineScope, например viewModelScope.
    // Это связано с жизненным циклом приложения.
    // Используем applicationScope.
    // private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    val database by lazy { MyDatabase.getDatabase(this, applicationScope) }
//    val repository by lazy { EmployeeRepository(database.employeeDao()) }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@RoomApp)
            modules(app)
        }
    }


}

val app = module {
    val applicationScope = CoroutineScope(SupervisorJob())
    single<MyDatabase> { MyDatabase.getDatabase(androidApplication(), applicationScope)}
    single<EmployeeRepository> { EmployeeRepository((get() as MyDatabase).employeeDao()) }
    viewModel { MainViewModel(get()) }
}