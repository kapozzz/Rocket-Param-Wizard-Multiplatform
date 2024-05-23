package common.presentation.main

sealed class AppScreen(val route: String) {

    data object Main : AppScreen("main")

    data object Determination : AppScreen("determination")

    data object UndefinedDesign : AppScreen("undefined_screen")

    data object DefinedDesign: AppScreen("defined_screen")

    data object UndefinedVerification: AppScreen("undefined_verification")

    data object DefinedVerification: AppScreen("defined_verification")

    data object InfoScreen: AppScreen("info_screen")

    data object MassAnalyze: AppScreen("mass_analyze")

    data object Geometry: AppScreen("geometry")

}