package common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.ui.theme.LocalTheme

@Composable
fun RPWTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .border(
                width = 1.dp,
                color = LocalTheme.current.colors.hint,
                shape = RoundedCornerShape(22.dp)
            )
            .background(LocalTheme.current.colors.primary)
    ) {
        Text(
            text = text,
            color = LocalTheme.current.colors.onPrimary,
            style = LocalTheme.current.typo.regular
        )
    }
}