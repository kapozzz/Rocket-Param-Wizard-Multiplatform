package common.presentation.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import common.presentation.main.CurrentScreen
import common.ui.theme.LocalNavigator
import common.ui.theme.LocalTheme

@Composable
fun InfoScreen() {
    val navigator = LocalNavigator.current
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "RPW",
                style = LocalTheme.current.typo.title,
                color = LocalTheme.current.colors.onBackground
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Важные заменчания:",
                textAlign = TextAlign.Start,
                style = LocalTheme.current.typo.regular,
                color = LocalTheme.current.colors.onBackground
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = """
                    * Внимательно смотрите на единицы измерения при вводе входных данных
                    
                    * Табличные значения следует сверять с методическим пособием т.к. функция линейной интерполяции иногда барахлит :)
                    
                    * С входными данными автора ничего не барахлит, но для вас никаких гарантий
                    
                    * Только для ДВУХСТУПЕНЧАТЫХ ракет, да я могу сделать общее решение, но не сейчас, мне нужно отдохнуть после написания функции линейной интерполяции в 1000 строк кода
                    
                    * Точность вычислений - 16 знаков после запятой (double type)
                """.trimIndent(),
                textAlign = TextAlign.Start,
                style = LocalTheme.current.typo.secondaryRegular,
                color = LocalTheme.current.colors.onBackground
            )
        }
        IconButton({
            navigator.navigate(CurrentScreen.Main.route)
        }, modifier = Modifier.padding(16.dp).align(Alignment.BottomStart)) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
}