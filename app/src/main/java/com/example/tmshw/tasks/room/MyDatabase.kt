package com.example.tmshw.tasks.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.github.javafaker.Faker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [Employee::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun employeeDao(): EmployeeDao


    //Чтобы удалить весь контент и повторно заполнить базу данных при каждом создании приложения
    private class EmployeeDatabaseCallback(

        //Поскольку не можем выполнять операции с БД Room в потоке пользовательского интерфейса,
        // onCreate()запускает сопрограмму в диспетчере ввода-вывода.
        private val scope: CoroutineScope,
    ) : RoomDatabase.Callback() {


        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    firstData(database.employeeDao())
                }
            }
        }

        // данные по умолчанию при создании приложения
        suspend fun firstData(employeeDao: EmployeeDao) {
            employeeDao.deleteAll()

            val list = listOf<Employee>(
                Employee(0, "Иван", "Пупкин", "Ведущий специалист", "УКС",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMdbQhKUAOhz-qHUe57gBSBWtmeyYAbpNUxQ&usqp=CAU"),
                Employee(0,
                    "Петр",
                    "Блэйд",
                    "Инженер",
                    "ТЭЦ",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQarmIZq0LCH1lk2cjclT4xN09PjGd1j7a0CQ&usqp=CAU"),
                Employee(0,
                    "Зенон",
                    "Китийский",
                    "HR",
                    "Отдел кадров",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Paolo_Monti_-_Servizio_fotografico_%28Napoli%2C_1969%29_-_BEIC_6353768.jpg/271px-Paolo_Monti_-_Servizio_fotografico_%28Napoli%2C_1969%29_-_BEIC_6353768.jpg"),
                Employee(0,
                    "Анаксагор",
                    "",
                    "Проект менеджер",
                    "Менеджмент",
                    "https://stihi.ru/pics/2015/08/21/2399.jpg"),
                Employee(
                    0,
                    "Анаксимен",
                    "Милетский",
                    "Специалист по развитию персонала",
                    "Отдел кадров",
                    "https://www.grandars.ru/images/1/review/id/818/af63a3fa80.jpg"),
                Employee(0, "Иван", "Пупкин", "Ведущий специалист", "УКС",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRMdbQhKUAOhz-qHUe57gBSBWtmeyYAbpNUxQ&usqp=CAU"),
                Employee(0,
                    "Петр",
                    "Блэйд",
                    "Инженер",
                    "ТЭЦ",
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQarmIZq0LCH1lk2cjclT4xN09PjGd1j7a0CQ&usqp=CAU"),
                Employee(0,
                    "Зенон",
                    "Китийский",
                    "HR",
                    "Отдел кадров",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/Paolo_Monti_-_Servizio_fotografico_%28Napoli%2C_1969%29_-_BEIC_6353768.jpg/271px-Paolo_Monti_-_Servizio_fotografico_%28Napoli%2C_1969%29_-_BEIC_6353768.jpg"),
                Employee(0,
                    "Анаксагор",
                    "",
                    "Проект менеджер",
                    "Менеджмент",
                    "https://stihi.ru/pics/2015/08/21/2399.jpg"),
                Employee(
                    0,
                    "Анаксимен",
                    "Милетский",
                    "Специалист по развитию персонала",
                    "Отдел кадров",
                    "https://www.grandars.ru/images/1/review/id/818/af63a3fa80.jpg")
            )
            for (employee in list) {
                employeeDao.insertEmployee(employee)
            }

        }

    }


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MyDatabase? = null

        //Для запуска корутин нужен  CoroutineScope.
        // добавим  в конструктор getDatabase scope.
        fun getDatabase(
            context: Context,
            scope: CoroutineScope,
        ): MyDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "My_database"
                ).addCallback(EmployeeDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}


