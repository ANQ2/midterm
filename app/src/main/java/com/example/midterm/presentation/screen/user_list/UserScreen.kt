package com.example.midterm.presentation.screen.user_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.midterm.presentation.viewmodel.UserViewModel

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel()
) {
    val users = viewModel.users.collectAsState()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (age.isNotEmpty()) {
                    viewModel.insertUser(name, email, age.toInt())
                    name = ""
                    email = ""
                    age = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add User")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(users.value) { user ->
                Text("${user.name} - ${user.email} - ${user.age} years old")
            }
        }
    }
}
