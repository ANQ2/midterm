package com.example.midterm.domain.usecase

import com.example.midterm.domain.repository.UserRepository

class GetUsersUseCase(private val repository: UserRepository) {
    operator fun invoke() = repository.getUsers()
}
