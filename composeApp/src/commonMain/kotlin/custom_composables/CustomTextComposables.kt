package custom_composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Transparent,
    fontSize: TextUnit = 16.sp,
    fontFamily: FontFamily = FontFamily.Default,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    padding: PaddingValues = PaddingValues(0.dp),
    isClickable: Boolean = false,
    onClick: (() -> Unit)? = null,
    style: TextStyle = TextStyle.Default
) {
    val textStyle = style.copy(
        color = color,
        fontSize = fontSize,
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        textAlign = textAlign,
        textDecoration = textDecoration,
        lineHeight = lineHeight
    )

    if (isClickable && onClick != null) {
        ClickableText(
            text = AnnotatedString(text),
            onClick = { onClick() },
            modifier = modifier.padding(padding),
            style = textStyle,
            maxLines = maxLines
        )
    } else {
        Text(
            text = text,
            modifier = modifier.padding(padding),
            style = textStyle,
            maxLines = maxLines
        )
    }
}




@Composable
fun HighlightSubstringText(
    fullText: String,
    substring: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 16.sp,
    fontFamily: FontFamily = FontFamily.Default,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    padding: PaddingValues = PaddingValues(0.dp),
    mainTextColor: Color = Color.Black,
    highlightColor: Color = Color.Red
) {
    // Create an AnnotatedString to style parts of the text differently
    val annotatedString = buildAnnotatedString {
        // Find the start and end index of the substring
        val startIndex = fullText.indexOf(substring)
        val endIndex = startIndex + substring.length

        if (startIndex == -1) {
            // If the substring is not found, display the full text in the main color
            withStyle(
                style = SpanStyle(
                    color = mainTextColor,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight
                )
            ) {
                append(fullText)
            }
        } else {
            // Add the text before the substring
            withStyle(
                style = SpanStyle(
                    color = mainTextColor,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight
                )
            ) {
                append(fullText.substring(0, startIndex))
            }

            // Add the substring with the highlight color
            withStyle(
                style = SpanStyle(
                    color = highlightColor,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight
                )
            ) {
                append(fullText.substring(startIndex, endIndex))
            }

            // Add the text after the substring
            withStyle(
                style = SpanStyle(
                    color = mainTextColor,
                    fontSize = fontSize,
                    fontFamily = fontFamily,
                    fontWeight = fontWeight
                )
            ) {
                append(fullText.substring(endIndex))
            }
        }
    }

    // Display the styled text
    Text(
        text = annotatedString,
        modifier = modifier.padding(padding),
        textAlign = textAlign
    )
}
