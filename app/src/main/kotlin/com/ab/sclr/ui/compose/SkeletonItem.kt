package com.ab.sclr.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SkeletonItem(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(Color.Gray.copy(alpha = 0.3f))
    ) {
        // You can add shimmering effects here later
    }
}