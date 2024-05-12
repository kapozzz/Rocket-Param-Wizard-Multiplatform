package common.presentation.main

sealed class CurrentScreen(val route: String) {

    data object Main : CurrentScreen("main")

    data object Determination : CurrentScreen("determination")

    data object UndefinedDesign : CurrentScreen("undefined_screen")

    data object DefinedDesign: CurrentScreen("defined_screen")

    data object UndefinedVerification: CurrentScreen("undefined_verification")

    data object DefinedVerification: CurrentScreen("defined_verification")

    data object InfoScreen: CurrentScreen("info_screen")

}