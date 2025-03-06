package screen.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom_composables.CustomText
import custom_composables.HighlightSubstringText
import custom_composables.colorEffectClickableWithRipple
import custom_composables.customClickEffect
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.ic_go_on
import demo.composeapp.generated.resources.ic_image
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun InfoScreen(navigateToHome: () -> Unit) {
    val viewModel = koinViewModel<DetailViewModel>()

    Scaffold(contentWindowInsets = WindowInsets.systemBars, bottomBar = {
        Column(
            modifier = Modifier.fillMaxWidth().navigationBarsPadding().background(
                shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
                color = Color.White
            ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HighlightSubstringText(
                "Your Gateway to Aesthetic & Medical Care",
                "Aesthetic",
                mainTextColor = Color.Black,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                highlightColor = Color.Blue.copy(alpha = .7f),
                textAlign = TextAlign.Center
            )
            CustomText(
                "Browse top-rated clinics and professionals for aesthetic and medical services and Schedule consultations effortlessly.",
                color = Color.Black.copy(alpha = .5f),
                modifier = Modifier.padding(5.dp).colorEffectClickableWithRipple(
                    onClick = { println("Box clicked!") },
                    defaultColor = Color.Transparent,
                    pressedColor = Color.Transparent,
                    rippleColor = Color.Black.copy(alpha = 0.3f) // Custom ripple color
                ),
                textAlign = TextAlign.Center
            )
            Image(
                painter = painterResource(Res.drawable.ic_go_on),
                "",
                modifier = Modifier
//                    .colorEffectClickableWithRipple(
//                    onClick = { println("Box clicked!") },
//                    defaultColor = Color.Transparent,
//                    pressedColor = Color.Transparent,
//                    rippleColor = Color.Black.copy(alpha = 0.3f) // Custom ripple color
//                )
                    .customClickEffect(
                    onClick = { navigateToHome() },
                    scaleFactor = 0.9f, // Custom scale factor
                    durationMillis = 150 // Custom animation duration
                )
                    .size(100.dp).background(Color.Transparent)
            )
        }
    }) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(Res.drawable.ic_image),
                "",
                modifier = Modifier.fillMaxSize().colorEffectClickableWithRipple(
                    onClick = {  },
                    defaultColor = Color.Green,
                    pressedColor = Color.Yellow,
                    rippleColor = Color.Red.copy(alpha = 0.3f) // Custom ripple color
                ),
                contentScale = ContentScale.FillBounds
            )


        }
    }
}