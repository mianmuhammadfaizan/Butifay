package navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen(route = "SplashScreen")
    data object InfoScreen: Screen(route = "InfoScreen")
    data object LoginSignUp: Screen(route = "LoginSignUp")
    data object OtpVerificationScreen: Screen(route = "OtpVerificationScreen")
}