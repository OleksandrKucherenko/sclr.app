package com.ab.sclr.ui.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ab.sclr.R
import com.ab.sclr.domain.primitives.Category
import com.ab.sclr.ui.theme.SclrcloneTheme

@Composable
fun TemplatesTabScreen(navController: NavController, onNewProjectClick: () -> Unit = {}) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                stringResource(R.string.title_templates_by_category),
                style = MaterialTheme.typography.titleMedium
            )

            // Horizontal List of Templates (Skeleton)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Category.entries.forEach { value ->
                    item {
                        SkeletonItem(modifier = Modifier.size(120.dp, 180.dp), text = value.title)
                    }
                }
            }
        }
        item {
            Text(
                stringResource(R.string.title_new_project),
                style = MaterialTheme.typography.titleMedium
            )
            Button(
                onClick = {
                    onNewProjectClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.button_create_new_project))
            }
        }
        item {
            Text(
                stringResource(R.string.title_popular_shared_projects),
                style = MaterialTheme.typography.titleMedium
            )
            PopularProjects(
                navController = navController,
                modifier = Modifier.height(400.dp),
                contentPadding = PaddingValues(0.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiTemplatesTabScreenPreview() {
    val navController = rememberNavController()

    SclrcloneTheme {
        TemplatesTabScreen(navController = navController)
    }
}