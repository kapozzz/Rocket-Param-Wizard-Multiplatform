package common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import common.ui.theme.LocalPlatformProvider
import common.ui.theme.LocalTheme
import common.ui.theme.Platform

@Composable
fun ParametersViewer(
    name: String,
    vararg elements: Pair<String, String>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = LocalTheme.current.typo.title,
                color = LocalTheme.current.colors.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            elements.forEachIndexed { index, element ->
                ElementInViewer(
                    value = element,
                    isEven = (index + 1) % 2 == 0
                )
            }
        }
    }
}

@Composable
private fun ElementInViewer(
    value: Pair<String, String>,
    isEven: Boolean,
    modifier: Modifier = Modifier
) {
    val backColor =
        if (isEven) LocalTheme.current.colors.hint else LocalTheme.current.colors.background
    val textColor =
        LocalTheme.current.colors.onBackground
    val scrollState = rememberScrollState()
    Row(
        modifier = modifier
            .height(42.dp)
            .fillMaxWidth()
            .background(backColor)
            .then(
                if (LocalPlatformProvider.current == Platform.Android) Modifier.horizontalScroll(
                    scrollState
                )
                else Modifier
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = value.first,
            style = LocalTheme.current.typo.secondaryRegular,
            color = textColor,
            modifier = Modifier.padding(start = 16.dp)
        )
        Text(
            text = value.second,
            style = LocalTheme.current.typo.secondaryRegular,
            color = textColor,
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}
