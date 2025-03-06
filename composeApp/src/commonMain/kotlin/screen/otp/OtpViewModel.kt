package screen.otp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

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