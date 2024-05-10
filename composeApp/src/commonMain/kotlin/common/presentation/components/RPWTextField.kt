package common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import common.ui.theme.LocalTheme

@Composable
fun RPWText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                color = LocalTheme.current.colors.primary,
                shape = RoundedCornerShape(22.dp)
            )
            .background(LocalTheme.current.colors.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = LocalTheme.current.colors.onBackground,
            style = LocalTheme.current.typo.regular
        )
    }

}