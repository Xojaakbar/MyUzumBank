package luis.mvi.uzumbank.presentation.screens.verifysignup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.usecase.auth.resend.ResendUseCase
import luis.mvi.uzumbank.domain.usecase.auth.signupverify.SignUpVerifyUseCase
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 06.11.2023 at 0:44
*/
@HiltViewModel
class SignUpVerifyViewModelImpl @Inject constructor(
    private val sharedPref: SharedPref,
    private val navigator: AppNavigator,
    private val useCase: SignUpVerifyUseCase,
    private val resendUseCase: ResendUseCase,
) : ViewModel(), SignUpVerifyViewModel {

    override val progressLiveData = MutableLiveData<Boolean>()
    override val incorrectLiveData = MutableLiveData<Unit>()
    override val resendLiveData = MutableLiveData<Unit>()
    override val codeLiveData = MutableLiveData<String>()
    override val toastLiveData = MutableLiveData<String>()

    override fun resend() {
        progressLiveData.value = true
        resendUseCase.invoke(SignUpResendRequestParam(sharedPref.token)).onEach {
            it.onSuccess {
                sharedPref.code = it.code
                sharedPref.token = it.token
                resendLiveData.value = Unit
                progressLiveData.value = false
            }
            it.onFailure {
                toastLiveData.value = it.message
                progressLiveData.value = false
            }
            it.onMessage {
                toastLiveData.value = it
                progressLiveData.value = false
            }
            it.onNotSend {
                toastLiveData.value = "Запрос не отправлён"
                progressLiveData.value = false
            }
        }.launchIn(viewModelScope)
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun setCode() {
        viewModelScope.launch {
            delay(3000)
            codeLiveData.value = sharedPref.code
        }
    }

    override fun verify(code: String) {
        progressLiveData.value = true
        if (code == sharedPref.code) {

            useCase.invoke(SignUpVerifyRequestParam(code, sharedPref.token)).onEach {
                it.onSuccess {
                    sharedPref.accessToken = it.acccess_token
                    sharedPref.isLogIn = true
                    progressLiveData.value = false
                    viewModelScope.launch {
                        sharedPref.isLogIn = true
                        navigator.navigateTo(SignUpVerifyScreenDirections.actionSignUpVerifyScreenToHomeScreen())
                    }
                }
                it.onFailure {
                    progressLiveData.value = false
                    sharedPref.accessToken = ""
                    sharedPref.isLogIn = false
                    toastLiveData.value = "Код неверный"
                    incorrectLiveData.value = Unit
                }
                it.onMessage {
                    progressLiveData.value = false
                    sharedPref.accessToken = ""
                    sharedPref.isLogIn = false
                    toastLiveData.value = it
                    incorrectLiveData.value = Unit
                }
                it.onNotSend {
                    progressLiveData.value = false
                    sharedPref.accessToken = ""
                    sharedPref.isLogIn = false
                    toastLiveData.value = "Запрос не отправлён"
                    incorrectLiveData.value = Unit
                }
            }.launchIn(viewModelScope)
        }
    }
}