package luis.mvi.uzumbank.presentation.screens.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.usecase.auth.signup.SignUpUseCase
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 30.07.2023 at 15:44
*/

@HiltViewModel
class SignUpViewModelImpl @Inject constructor(
    private val useCase: SignUpUseCase,
    private val navigator: AppNavigator,
    private val sharedPref: SharedPref,
) : SignUpViewModel, ViewModel() {

    override val toastLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Boolean>()

    init {
        progressLiveData.value = false
    }

    override fun goToSignIn() {
        viewModelScope.launch {
            navigator.navigateTo(SignUpScreenDirections.actionScreenSignUpToSignInScreen())
        }
    }

    override fun signUp(
        phone: String,
        firstName: String,
        lastName: String,
        password: String,
        confirmPassword: String,
    ) {
        when {
            phone.length != 13 -> {
                toastLiveData.value = "Не правильно набран номер телефона"
            }

            firstName.isEmpty() && firstName.isNotBlank() -> {
                toastLiveData.value = "Введите имя"
            }

            lastName.isEmpty() && lastName.isNotBlank() -> {
                toastLiveData.value = "Введите фамилию"
            }

            password.isEmpty() && password.isNotBlank() -> {
                toastLiveData.value = "Заполните пароль"
            }

            confirmPassword.isEmpty() && confirmPassword.isNotBlank() -> {
                toastLiveData.value = "Подтвердите пароль"
            }

            confirmPassword != password -> {
                toastLiveData.value = "пароли не совпадают"
            }

            else -> {

                progressLiveData.value = true

                useCase.invoke(
                    SignUpRequestParam(
                        firstName, lastName, password, phone
                    )
                ).onEach {
                    it.onSuccess {
                        sharedPref.token = it.token
                        sharedPref.isLogIn = false
                        progressLiveData.value = false
                        sharedPref.code = it.code
                        viewModelScope.launch {
                            navigator.navigateTo(SignUpScreenDirections.actionScreenSignUpToSignUpVerifyScreen())
                        }
                    }
                    it.onFailure {
                        progressLiveData.value = false
                        toastLiveData.value = it.message.toString()
                        sharedPref.isLogIn = false
                    }
                    it.onMessage {
                        progressLiveData.value = false
                        toastLiveData.value = it
                        sharedPref.isLogIn = false
                    }
                    it.onNotSend {
                        progressLiveData.value = false
                        toastLiveData.value = "Ошибка сети"
                        sharedPref.isLogIn = false
                    }
                }.launchIn(viewModelScope)
            }
        }
    }
}
