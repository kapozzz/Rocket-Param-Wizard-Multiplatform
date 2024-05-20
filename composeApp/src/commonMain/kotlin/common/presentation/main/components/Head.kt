package common.presentation.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.main.AppScreen
import common.ui.theme.LocalNavigator
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
    val navigator = LocalNavigator.current
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
                    .size(60.dp),
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
            IconButton({
                navigator.navigate(AppScreen.InfoScreen.route)
            }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            }
        }

    }
}