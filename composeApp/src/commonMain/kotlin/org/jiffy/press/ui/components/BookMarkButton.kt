package org.jiffy.press.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BookMarkButton(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    selected: Boolean = false,
    onBookMark: () -> Unit
) {
    val icon = if (selected) Icons.Outlined.Bookmark else Icons.Filled.BookmarkBorder
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        modifier = modifier
            .requiredSize(36.dp, 36.dp)
            .clickable {
                onBookMark()
            }
    ) {
        Icon(
            imageVector = icon,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null,
            modifier = Modifier
                .padding(6.dp)
        )
    }
}