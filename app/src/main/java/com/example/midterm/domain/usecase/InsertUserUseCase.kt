package com.example.midterm.domain.usecase

import com.example.midterm.data.local.entity.UserEntity
import com.example.midterm.domain.repository.UserRepository

class InsertUserUseCase(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) = repository.insertUser(user)
}
