package common.core.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.core.base.models.Fuel
import common.core.base.objects.Fuels
import common.ui.theme.LocalTheme

@Composable
fun FuelPicker(
    fuel: Fuel,
    onFuelClick: (fuel: Fuel) -> Unit
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FuelField(
            fuel = Fuels.Dimethylhydrazine,
            isPicked = fuel.name == Fuels.Dimethylhydrazine.name,
            onClick = { onFuelClick(Fuels.Dimethylhydrazine) }
        )
        FuelField(
            fuel = Fuels.Paraffin,
            isPicked = fuel.name == Fuels.Paraffin.name,
            onClick = { onFuelClick(Fuels.Paraffin) }
        )
        FuelField(
            fuel = Fuels.LiquidHydrogen,
            isPicked = fuel.name == Fuels.LiquidHydrogen.name,
            onClick = { onFuelClick(Fuels.LiquidHydrogen) }
        )
    }
}

@Composable
private fun FuelField(
    fuel: Fuel,
    isPicked: Boolean,
    onClick: () -> Unit
) {
    val animatedBackgroundColor = animateColorAsState(
        if (isPicked) LocalTheme.current.colors.primary else
            LocalTheme.current.colors.background,
        animationSpec = tween(durationMillis = 400, easing = FastOutSlowInEasing)
    )
    Box(
        modifier = Modifier
            .height(60.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                animatedBackgroundColor.value
            )
            .border(
                color = Color.Gray,
                width = 1.dp,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = fuel.name,
            style = LocalTheme.current.typo.regular,
            color = if (isPicked) LocalTheme.current.colors.onPrimary else
                LocalTheme.current.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }
}