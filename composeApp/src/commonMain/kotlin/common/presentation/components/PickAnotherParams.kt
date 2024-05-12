package common.presentation.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.ui.theme.LocalTheme

data class MutableParam(
    val name: String,
    val text: MutableState<String>,
    val onChange: (value: String) -> Unit,
)

@Composable
fun PickAnotherParams(
    vararg params: MutableParam,
    onOpenClick: () -> Unit = {}
) {

    val isVisible: MutableState<Boolean> = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AnimatedContent(
            targetState = isVisible.value
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .border(
                        color = LocalTheme.current.colors.primary,
                        width = 1.dp,
                        shape = RoundedCornerShape(22.dp)
                    )
                    .background(LocalTheme.current.colors.background)
            ) {
                Button(
                    onClick = {
                        isVisible.value = !isVisible.value
                        if (isVisible.value) onOpenClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(42.dp)
                        .background(LocalTheme.current.colors.primary),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = LocalTheme.current.colors.primary
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Изменить табличные параметры",
                            color = LocalTheme.current.colors.onPrimary,
                            style = LocalTheme.current.typo.secondaryRegular
                        )
                        Icon(
                            imageVector = if (it) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = LocalTheme.current.colors.onPrimary
                        )
                    }
                }
                if (it) {
                    params.forEach { param ->
                        ParamEditField(param)
                    }
                }
            }
        }
    }
}


@Composable
private fun ParamEditField(param: MutableParam) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.weight(0.5f),
            text = param.name,
            style = LocalTheme.current.typo.secondaryRegular,
            color = LocalTheme.current.colors.onBackground,
            textAlign = TextAlign.Center
        )
        TextField(
            modifier = Modifier.weight(0.5f).height(IntrinsicSize.Min),
            value = param.text.value,
            onValueChange = {
                val input = it.replace(',', '.')
                if (input.matches("^\\d*\\.?\\d*\$".toRegex()) || input.isBlank()) {
                    param.text.value = it
                    if (it.isNotEmpty()) {
                        param.onChange(it)
                    }
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = LocalTheme.current.colors.background,
                unfocusedIndicatorColor = LocalTheme.current.colors.background,
                focusedIndicatorColor = LocalTheme.current.colors.background
            )
        )
    }
}
















