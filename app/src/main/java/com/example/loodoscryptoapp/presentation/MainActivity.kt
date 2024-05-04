package com.example.loodoscryptoapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loodoscryptoapp.presentation.detail.DetailScreen
import com.example.loodoscryptoapp.presentation.favorite.FavoriteScreen
import com.example.loodoscryptoapp.presentation.home.HomeScreen
import com.example.loodoscryptoapp.presentation.sign_in.SignInScreen
import com.example.loodoscryptoapp.presentation.sign_up.SignUpScreen
import com.example.loodoscryptoapp.presentation.theme.ui.LoodosCryptoAppTheme
import com.example.loodoscryptoapp.presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoodosCryptoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenTransations()
                }
            }
        }
    }
}

@Composable
fun ScreenTransations() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "welcome") {
        composable("welcome") { backStackEntry ->
            WelcomeScreen(navController)
        }
        composable("signup") { backStackEntry ->
            SignUpScreen(navController = navController)
        }
        composable("signin") { backStackEntry ->
            SignInScreen(navController = navController)
        }
        composable("home") { backStackEntry ->
            HomeScreen(navController = navController)
        }
        composable("fav") { backStackEntry ->
            FavoriteScreen(navController)
        }
        composable("detail/{asset_id}/{id_icon}/{name}/{price_usd}",
            arguments= listOf(
                navArgument("asset_id") {type = NavType.StringType},
                navArgument("id_icon") {type = NavType.StringType},
                navArgument("name") {type = NavType.StringType},
                navArgument("price_usd") {type = NavType.StringType},
            )) { backStackEntry ->
            val asset_id = backStackEntry.arguments!!.getString("asset_id")
            val id_icon = backStackEntry.arguments!!.getString("id_icon")
            val name = backStackEntry.arguments!!.getString("name")
            val price_usd = backStackEntry.arguments!!.getString("price_usd")
            DetailScreen(asset_id!!, id_icon!!, name!!, price_usd!!)
        }

    }
}

