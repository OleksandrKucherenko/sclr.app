package com.ab.sclr.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.ui.UiDestinations


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = UiDestinations.HOME) {
        composable(UiDestinations.SPLASH) { SplashScreen(navController) }
        composable(UiDestinations.HOME) { MainScreen(navController) }
        composable(UiDestinations.EXPLORER) { ExplorerScreen(navController) }
        composable("${UiDestinations.EDITOR}?templateId={templateId}") { backStackEntry ->
            EditorScreen(
                navController = navController,
                templateId = backStackEntry.arguments?.getString("templateId")
            )
        }
        composable(UiDestinations.EDITOR) { // For blank project
            EditorScreen(navController = navController)
        }
        composable("${UiDestinations.PREVIEW}/{templateId}") { backStackEntry ->
            PreviewScreen(
                navController = navController,
                templateId = backStackEntry.arguments?.getString("templateId")
            )
        }
    }
}
