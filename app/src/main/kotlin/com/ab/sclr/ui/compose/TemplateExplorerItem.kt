package com.ab.sclr.ui.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ab.sclr.R


@Composable
fun TemplateExplorerItem(
    templateName: String,
    onItemClick: () -> Unit,
    onUseTemplateClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() }
    ) {
        Column {
            SkeletonItem( // Placeholder for Preview Image
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f)
            )
            Column(Modifier.padding(8.dp)) {
                Text(templateName, style = MaterialTheme.typography.titleMedium)
                Text(stringResource(R.string.label_images_x_slides_y, 0, 0), style = MaterialTheme.typography.bodySmall)
                Text(stringResource(R.string.label_author_z,
                    stringResource(R.string.placeholder_author_unknown)), style = MaterialTheme.typography.bodySmall)
                Spacer(Modifier.height(8.dp))
                Button(onClick = onUseTemplateClick, modifier = Modifier.align(
                    Alignment.End
                )) {
                    Text(stringResource(R.string.button_use_template))
                }
            }
        }
    }
}