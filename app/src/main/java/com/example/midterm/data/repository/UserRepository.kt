package com.example.midterm.domain.repository

import com.example.midterm.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun insertUser(user: UserEntity)
    fun getUsers(): Flow<List<UserEntity>>
    suspend fun deleteUser(user: UserEntity)
}
