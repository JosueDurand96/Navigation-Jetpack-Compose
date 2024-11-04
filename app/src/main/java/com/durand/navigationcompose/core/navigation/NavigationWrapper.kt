package com.durand.navigationcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.durand.navigationcompose.DetailScreen
import com.durand.navigationcompose.HomeScreen
import com.durand.navigationcompose.LoginScreen

@Composable
fun  NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen {
                navController.navigate(Home)
            }
        }
        composable<Home> {
            HomeScreen{
                name -> navController.navigate(Detail(name = name))
            }
        }
        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(detail.name)
        }
    }
}