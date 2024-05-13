package common.presentation.determination

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import common.presentation.components.ParametersViewer
import common.presentation.main.CurrentScreen
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalSolvesState

@Composable
fun DeterminationScreen(
) {
    val navigator = LocalNavigator.current
    val solvesState = LocalSolvesState.current
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            with(solvesState.determination.value) {
                ParametersViewer(
                    name = "Определение показателей эффективности двигателей",
                    Pair("Удальная тяга (1)", specificGravityCalculatedFirst.toString()),
                    Pair(
                        "Удельная тяга (2)",
                        specificGravityCalculatedSecond.toString()
                    ),
                    Pair(
                        "Удельная тяга в пустоте (1)",
                        specificGravityInVoidFirst.toString()
                    ),
                    Pair(
                        "Удельная тяга в пустоте (2)",
                        specificGravityInVoidSecond.toString()
                    ),
                    Pair(
                        "Удельная тяга при старте с земли",
                        specificGravityOnStartFromEarth.toString()
                    ),
                    Pair("Средняя удельная тяга", middleSpecificGravity.toString()),
                    Pair(
                        "Тяговооруженность при старте (1)",
                        firstStageThrustCapacityInVoid.toString()
                    )
                )
            }
            IconButton(
                {
                    navigator.navigate(CurrentScreen.UndefinedDesign.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
            IconButton(
                {
                    navigator.popBackStack()
                },
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}


