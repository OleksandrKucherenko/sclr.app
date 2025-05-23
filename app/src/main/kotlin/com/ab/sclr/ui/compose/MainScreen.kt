package com.ab.sclr.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.R
import com.ab.sclr.ui.UiDestinations
import com.ab.sclr.ui.theme.SclrcloneTheme


enum class Tab(val title: Int, val icon: Int) {
    HOME(R.string.home_tab_templates, R.drawable.ic_home_templates),
    SAVED(R.string.home_tab_saved, R.drawable.ic_home_saved)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    var selectedTab by remember { mutableStateOf(Tab.HOME) }
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(true)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(selectedTab.title)) },
            )
        },
        bottomBar = {
            NavigationBar {
                Tab.entries.toTypedArray().forEach { value ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = value.icon),
                                contentDescription = stringResource(id = value.title)
                            )
                        },
                        label = { Text(text = stringResource(id = value.title)) },
                        selected = selectedTab == value,
                        onClick = { selectedTab = value }
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(Icons.Filled.Add, stringResource(R.string.create_new_project))
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (selectedTab) {
                Tab.HOME -> TemplatesTabScreen(
                    navController = navController,
                    onNewProjectClick = { showBottomSheet = true })

                Tab.SAVED -> SavedTabScreen(navController = navController)
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState
        ) {
            NewProjectSelectionContent(
                onBlankClick = {
                    showBottomSheet = false
                    navController.navigate(UiDestinations.EDITOR) // Potentially pass args
                },
                onFromTemplateClick = {
                    showBottomSheet = false
                    navController.navigate(UiDestinations.EXPLORER)
                }
            )
        }
    }
}

@Composable
fun NewProjectSelectionContent(onBlankClick: () -> Unit, onFromTemplateClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            stringResource(R.string.title_create_new_project),
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onBlankClick, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.button_project_blank))
        }
        Button(onClick = onFromTemplateClick, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.button_from_template))
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}


@Preview(showBackground = true)
@Composable
fun UiMainScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        MainScreen(navController = navController)
    }
}