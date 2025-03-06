package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import screen.info.InfoScreen
import screen.login_signup.LoginSignUp

import screen.otp.OtpVerificationScreen
import screen.splash.SplashScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.SplashScreen.route
) {




    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.SplashScreen.route) {

            SplashScreen(
                navigateToDetails = {
                    navController.navigate(Screen.InfoScreen.route)
                }
            )
        }
        composable(route = Screen.InfoScreen.route) {
            InfoScreen(
                navigateToHome = {
                    navController.navigate(Screen.LoginSignUp.route)
                }
            )
        }
        composable(route = Screen.LoginSignUp.route) {
            LoginSignUp(
                onNavigate = {
                    navController.navigate(Screen. OtpVerificationScreen.route)
                }
            )
        }
        composable(route = Screen.OtpVerificationScreen.route) {
            OtpVerificationScreen()
        }
    }
}