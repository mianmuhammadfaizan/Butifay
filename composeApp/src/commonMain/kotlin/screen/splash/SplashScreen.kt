package screen.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import custom_composables.CustomText
import custom_composables.colorEffectClickableWithRipple
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.ic_logo

import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(navigateToDetails: () -> Unit) {
    val viewModel = koinViewModel<HomeViewModel>()
    var isScaledUp by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isScaledUp) 1f else 0f,
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )
    LaunchedEffect(Unit) {
        isScaledUp = true
        delay(3000)
        navigateToDetails()
    }
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center,

    ) {

        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter =      painterResource(Res.drawable.ic_logo),"",modifier = Modifier
                .size(200.dp).background(Color.Transparent).padding(end = 30.dp)
                .scale(scale) )
            CustomText(
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.W800),
                fontSize = 35.sp,
                text = "BEAUTIFY",
                color = Color.Black,
                modifier = Modifier.scale(scale) ,
                textAlign = TextAlign.Center
            )
        }



    }
}