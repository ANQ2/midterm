package com.example.midterm

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.airbnb.lottie.compose.*
import com.example.midterm.presentation.screen.user_list.UserScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "main_screen") {
        composable("main_screen") { MainScreen(navController) }
        composable("details_screen") { DetailsScreen(navController) }
        composable("favorite_player_screen") { FavoritePlayerScreen(navController) }
        composable("favorite_club_screen") { FavoriteClubScreen(navController) }
        // Новый экран для списка пользователей
        composable("user_screen") { UserScreen() }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    BackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Добро пожаловать!",
                color = Color.White,
                fontSize = 24.sp,
                modifier = Modifier.padding(16.dp)
            )

            AnimatedButton(
                animationRes = R.raw.lottie_button,
                onClick = { navController.navigate("details_screen") }
            )

            Button(onClick = { navController.navigate("favorite_club_screen") }) {
                Text("Мой любимый клуб")
            }

            Button(onClick = { navController.navigate("favorite_player_screen") }) {
                Text("Мой любимый игрок")
            }

            Button(onClick = { navController.navigate("user_screen") }) {
                Text("Пользователи") // Кнопка на Room экран
            }
        }
    }
}

@Composable
fun DetailsScreen(navController: NavController) {
    BackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Почему я люблю футбол?", fontSize = 24.sp, color = Color.White)
            Text(
                "Я влюбился в футбол, когда мне было 5 лет. С тех пор этот вид спорта стал для меня чем-то большим, чем просто игра. Я восхищаюсь его динамикой, страстью и тем, как он объединяет людей по всему миру.",
                color = Color.White
            )
            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
    }
}

@Composable
fun FavoritePlayerScreen(navController: NavController) {
    val context = LocalContext.current

    BackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Мой любимый игрок — Cristiano Ronaldo", fontSize = 24.sp, color = Color.White)

            Button(onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=mmeLCAP74KA"))
                context.startActivity(intent)
            }) {
                Text("Смотреть лучшие голы")
            }

            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
    }
}

@Composable
fun FavoriteClubScreen(navController: NavController) {
    BackgroundBox {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.real_madrid_logo),
                contentDescription = "Real Madrid Logo",
                modifier = Modifier.size(100.dp)
            )
            Text("Мой любимый клуб — Real Madrid", fontSize = 24.sp, color = Color.White)
            Button(onClick = { navController.popBackStack() }) {
                Text("Назад")
            }
        }
    }
}

@Composable
fun AnimatedButton(animationRes: Int, onClick: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animationRes))
    val progress by animateLottieCompositionAsState(composition)

    Box(
        modifier = Modifier
            .size(100.dp)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = composition,
            progress = progress
        )
    }
}

@Composable
fun BackgroundBox(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.cristiano_ronaldo),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
        )

        content()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen(rememberNavController())
}
