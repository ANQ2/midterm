package com.example.midterm.data.repository

import com.example.midterm.data.local.dao.UserDao
import com.example.midterm.data.local.entity.UserEntity
import com.example.midterm.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    override fun getUsers(): Flow<List<UserEntity>> = userDao.getUsers()
    override suspend fun deleteUser(user: UserEntity) = userDao.deleteUser(user)
}
