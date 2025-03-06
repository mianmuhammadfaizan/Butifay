package screen.login_signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginSignUpViewModel:ViewModel() {
    var email  = mutableStateOf("")

}