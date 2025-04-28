package com.example.midterm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midterm.data.local.entity.UserEntity
import com.example.midterm.domain.usecase.GetUsersUseCase
import com.example.midterm.domain.usecase.InsertUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val insertUserUseCase: InsertUserUseCase
) : ViewModel() {

    val users: StateFlow<List<UserEntity>> = getUsersUseCase()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insertUser(name: String, email: String, age: Int) {
        viewModelScope.launch {
            insertUserUseCase(UserEntity(name = name, email = email, age = age))
        }
    }
}
