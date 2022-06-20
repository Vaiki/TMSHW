package com.example.tmshw.tasks.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "EMPLOYEE_TABLE")
data class Employee(

    @PrimaryKey(autoGenerate = true)
    var id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "last_name")
    val lastName: String,

    @ColumnInfo(name = "position")
    var position: String,

    @ColumnInfo(name = "department")
    var department: String,

    @ColumnInfo(name = "avatar_uri")
    var avatarUri: String,
)