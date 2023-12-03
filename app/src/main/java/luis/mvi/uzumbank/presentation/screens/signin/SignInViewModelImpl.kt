package luis.mvi.uzumbank.presentation.screens.signin

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.domain.models.SignInRequestParam
import luis.mvi.uzumbank.domain.usecase.auth.signin.SignInUseCase
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 22.09.2023 at 4:45
*/

@HiltViewModel
class SignInViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val useCase: SignInUseCase,
    private val sharedPref: SharedPref
) : ViewModel(), SignInViewModel {
    override val showToastLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()

    init {
        progressLiveData.value = false
    }

    override fun goToSignUp() {
        viewModelScope.launch {
            navigator.navigateTo(SignInScreenDirections.actionSignInScreenToScreenSignUp())
        }
    }

    override fun signIn(phone: String, password: String) {
        Log.d("phone", "onViewModel: $phone")
        when {
            phone.length != 13 -> { showToastLiveData.value = "Не правильно набран номер телефона" }
            password.isEmpty() && password.isNotBlank() -> { showToastLiveData.value = "Заполните пароль" }

            else -> {
                progressLiveData.value = true
                useCase(SignInRequestParam(password = password, phone = phone)).onEach { it ->
                    it.onSuccess {
                        sharedPref.accessToken = it.access_token
                        sharedPref.isLogIn = true
                        sharedPref.password = password
                        sharedPref.phone = phone
                        viewModelScope.launch {
                            navigator.navigateTo(SignInScreenDirections.actionSignInScreenToHomeScreen())
                        }
                        progressLiveData.value = false
                    }
                    it.onFailure {
                        showToastLiveData.value = it.message.toString()
                        sharedPref.isLogIn = false
                        progressLiveData.value = false
                        sharedPref.accessToken = ""
                    }
                    it.onMessage {
                        showToastLiveData.value = it
                        sharedPref.isLogIn = false
                        progressLiveData.value = false
                        sharedPref.accessToken = ""
                    }
                    it.onNotSend {
                        showToastLiveData.value = "Запрос не отправлён"
                        sharedPref.isLogIn = false
                        progressLiveData.value = false
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}