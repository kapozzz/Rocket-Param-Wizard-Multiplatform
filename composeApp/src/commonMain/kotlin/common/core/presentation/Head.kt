package common.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.ui.theme.LocalTheme
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import rocketparamwizard.composeapp.generated.resources.Res
import rocketparamwizard.composeapp.generated.resources.main_logo

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Head(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(90.dp),
                painter = painterResource(Res.drawable.main_logo),
                contentDescription = null
            )
            Text(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        top = 22.dp
                    ),
                text = "Задача баллистического проектирования",
                style = LocalTheme.current.typo.title
            )
        }

    }
}