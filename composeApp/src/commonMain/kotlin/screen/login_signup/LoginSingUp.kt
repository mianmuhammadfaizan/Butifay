package screen.login_signup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom_composables.CustomLogoWithText
import custom_composables.CustomText
import custom_composables.CustomTextField
import custom_composables.GradientDivider
import custom_composables.ReverseGradientDivider
import custom_composables.customClickEffect
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.ic_email
import demo.composeapp.generated.resources.ic_google
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginSignUp(onNavigate:()->Unit) {
    val loginSignUpViewModel = koinViewModel<LoginSignUpViewModel>()
    Scaffold(contentWindowInsets = WindowInsets.systemBars) { paddingValues ->


        Column(
            modifier = Modifier.fillMaxSize().padding(start = 20.dp, end = 20.dp)
                .padding(paddingValues) // Apply Scaffold's padding
//                .windowInsetsPadding(WindowInsets.statusBars) // Add status bar padding
                .windowInsetsPadding(WindowInsets.navigationBars),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CustomLogoWithText(modifier = Modifier.fillMaxWidth())
            CustomText(
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.W900),
                fontSize = 26.sp,
                text = "Welcome Back!!",
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            CustomText(
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.W900),
                fontSize = 14.sp,
                text = "Log in to book and manage your appointments & clinics.",
                color = Color.Black.copy(alpha = .5f),
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                textAlign = TextAlign.Center
            )

            CustomText(
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.ExtraBold),
                fontSize = 16.sp,
                text = "Email Address",
                color = Color.Black,
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                textAlign = TextAlign.Start
            )




            CustomTextField(
                value = loginSignUpViewModel.email.value,
                onValueChange = { loginSignUpViewModel.email.value = it },
                hint = "Enter your email",
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done,
                leadingIcon = Res.drawable.ic_email, // Material Icon
                trailingIcon = null

            )
            Box(
                modifier = Modifier.fillMaxWidth().customClickEffect(
                    onClick = {},
                    scaleFactor = 0.9f, // Custom scale factor
                    durationMillis = 150 // Custom animation duration
                ).background(color = Color(0xFF3D7197), shape = RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                CustomText(
                    style = TextStyle(color = Color.White, fontWeight = FontWeight.ExtraBold),
                    fontSize = 18.sp,
                    text = "Sign In",
                    color = Color.White,
                    modifier = Modifier.fillMaxWidth().padding(15.dp),
                    textAlign = TextAlign.Center
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ReverseGradientDivider(modifier = Modifier.weight(1f))

                CustomText(
                    style = TextStyle(color = Color.Black, fontWeight = FontWeight.ExtraBold),
                    fontSize = 18.sp,
                    text = "Or",
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth().padding(5.dp).weight(.2f),
                    textAlign = TextAlign.Center
                )
                GradientDivider(modifier = Modifier.weight(1f))

            }

            Box(
                modifier = Modifier.fillMaxWidth().customClickEffect(
                    onClick = {onNavigate()},
                    scaleFactor = 0.9f, // Custom scale factor
                    durationMillis = 150 // Custom animation duration
                ).background(color = Color.Black, shape = RoundedCornerShape(30.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(Res.drawable.ic_google), "", modifier = Modifier
                            .size(25.dp).background(Color.Transparent)
                    )
                    CustomText(
                        style = TextStyle(color = Color.White, fontWeight = FontWeight.ExtraBold),
                        fontSize = 18.sp,
                        text = "Sign In With Google",
                        color = Color.White,
                        modifier = Modifier.wrapContentSize().padding(15.dp),
                        textAlign = TextAlign.Start
                    )
                }
            }
        }


    }
}