package screen.otp


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpVerificationScreen(viewModel: OtpViewModel = viewModel()) {
    val otpValues by viewModel.otpValues.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val focusedIndex by viewModel.focusedIndex.collectAsState()

    val focusRequesters = List(4) { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter OTP", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            otpValues.forEachIndexed { index, value ->
                OutlinedTextField(
                    value = value,
                    onValueChange = { newValue ->
                        viewModel.updateOtp(index, newValue)
                        if (newValue.isNotEmpty() && index < 3) {
                            focusRequesters[index + 1].requestFocus()
                        }
                    },
                    modifier = Modifier
                        .size(56.dp)
                        .shadow(6.dp, shape = RoundedCornerShape(30.dp), clip = false)
                        .border(
                            width = 2.dp,
                            color = when {
                                isError -> Color.Red
                                focusedIndex == index -> Color.Blue  // Change color when focused
                                else -> Color.Gray
                            },
                            shape = RoundedCornerShape(30.dp)
                        )
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.White)
                        .focusRequester(focusRequesters[index])
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) viewModel.setFocusedIndex(index)
                        },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (index == 3) ImeAction.Done else ImeAction.Next
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Transparent, // Handled in border()
                        unfocusedBorderColor = Color.Transparent, // Handled in border()
                        errorBorderColor = Color.Red,
                    ),
                    shape = RoundedCornerShape(30.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.validateOtp() }
        ) {
            Text(text = "Verify OTP")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(
            onClick = {
                viewModel.resetOtp()
                focusRequesters.first().requestFocus() // Focus on first field after reset
                keyboardController?.show()
            }
        ) {
            Text(text = "Resend OTP", color = Color.Blue)
        }
    }

    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
        keyboardController?.show()
    }
}

class OtpViewModel : ViewModel() {
    private val _otpValues = MutableStateFlow(List(4) { "" })
    val otpValues: StateFlow<List<String>> = _otpValues

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private val _focusedIndex = MutableStateFlow(-1)
    val focusedIndex: StateFlow<Int> = _focusedIndex

    private val correctOtp = "1234"  // Default correct OTP

    fun updateOtp(index: Int, value: String) {
        if (value.length <= 1) {
            _otpValues.value = _otpValues.value.toMutableList().apply { this[index] = value }
            if (_isError.value) _isError.value = false
        }
    }

    fun validateOtp() {
        val enteredOtp = _otpValues.value.joinToString("")
        _isError.value = enteredOtp != correctOtp
    }

    fun resetOtp() {
        _otpValues.value = List(4) { "" }
        _isError.value = false
        _focusedIndex.value = 0
    }

    fun setFocusedIndex(index: Int) {
        _focusedIndex.value = index
    }
}

