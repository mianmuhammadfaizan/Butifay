package custom_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import demo.composeapp.generated.resources.Res
import demo.composeapp.generated.resources.ic_go_on
import demo.composeapp.generated.resources.ic_logo
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomLogoWithText(modifier: Modifier){


        Column(modifier=modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter =      painterResource(Res.drawable.ic_logo),"",modifier = Modifier
                .size(100.dp).background(Color.Transparent).padding(end = 10.dp)
            )
            CustomText(
                style = TextStyle(color = Color.Black, fontWeight = FontWeight.ExtraBold),
                fontSize = 20.sp,
                text = "BEAUTIFY",
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

        }



}



@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "",
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    hintColor: Color = Color.Gray,
    borderColor: Color = Color.LightGray,
    leadingIcon:  DrawableResource? = null,
    trailingIcon:  DrawableResource? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Row(
        modifier = modifier
            .clip(shape = RoundedCornerShape(30.dp))
            .border(1.dp, borderColor, shape = RoundedCornerShape(30.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leadingIcon?.let {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.padding(end = 8.dp).size(20.dp)
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            if (value.isEmpty()) {
                Text(text = hint, color = hintColor, fontSize = 16.sp)
            }
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(color = textColor, fontSize = 18.sp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onImeAction?.invoke() }
                ),
                visualTransformation = visualTransformation,
                modifier = Modifier.fillMaxWidth()
            )
        }
        trailingIcon?.let {
            Icon(
                painter = painterResource(Res.drawable.ic_go_on),
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable { onTrailingIconClick?.invoke() }
            )
        }
    }
}



@Composable
fun GradientDivider(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp) // Thickness of divider
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF3D7197), // Solid color start
                        Color(0xFF3D7197).copy(alpha = 0f) // Transparent at end
                    )
                ),
                shape = RoundedCornerShape(50) // Optional for rounded edges
            )
    )
}
@Composable
fun ReverseGradientDivider(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(2.dp) // Thickness of divider
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF3D7197).copy(alpha = 0f), // Transparent at start
                        Color(0xFF3D7197) // Solid at end
                    )
                ),
                shape = RoundedCornerShape(50) // Optional rounded edges
            )
    )
}