package com.durand.navigationcompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.durand.navigationcompose.DetailScreen
import com.durand.navigationcompose.HomeScreen
import com.durand.navigationcompose.LoginScreen
import com.durand.navigationcompose.SettingsScreen
import com.durand.navigationcompose.core.navigation.type.createNavType
import kotlin.reflect.typeOf

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen {
                navController.navigate(Home)
            }
        }
        composable<Home> {
            HomeScreen { name ->
                navController.navigate(Detail(name = name))
            }
        }
     //   composable<Detail> { backStackEntry ->
    //        val detail: Detail = backStackEntry.toRoute()
           // DetailScreen(detail.name) {
                //  navController.navigate(Login)
                //  navController.popBackStack()
                // navController.navigateUp()
             //   navController.navigate(Login) {
            //        popUpTo<Login> { inclusive = true }
            //    }
           // }
    //    }

        composable<Detail> { backStackEntry ->
            val detail: Detail = backStackEntry.toRoute()
            DetailScreen(name = detail.name,
                navigateBack = { navController.navigateUp() },
                navigateToSettings = {navController.navigate(Settings(it))})
        }
        composable<Settings>(typeMap = mapOf(typeOf<SettingsInfo>() to createNavType<SettingsInfo>())) { backStackEntry ->
            val settings: Settings = backStackEntry.toRoute()
            SettingsScreen(settings.info)
        }
    }
}