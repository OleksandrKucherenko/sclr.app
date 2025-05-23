package com.ab.sclr.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.R
import com.ab.sclr.ui.theme.SclrcloneTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExplorerScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Explore Templates") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(
                                R.string.alt_icon_back
                            )
                        )
                    }
                }
            )
        }) { paddingValues ->
        PopularProjects(
            navController = navController,
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UiExplorerScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        ExplorerScreen(navController = navController)
    }
}