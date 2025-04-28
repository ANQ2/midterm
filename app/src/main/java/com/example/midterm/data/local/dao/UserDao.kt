package com.example.midterm.data.local.dao

import androidx.room.*
import androidx.room.Dao
import com.example.midterm.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<UserEntity>>

    @Delete
    suspend fun deleteUser(user: UserEntity)
}
