package org.kapozzz.rpw

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import common.ui.theme.LocalPlatformProvider
import common.ui.theme.Platform

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalPlatformProvider provides Platform.Android,
                content = {
                    App()
                }
            )
        }
    }
}
