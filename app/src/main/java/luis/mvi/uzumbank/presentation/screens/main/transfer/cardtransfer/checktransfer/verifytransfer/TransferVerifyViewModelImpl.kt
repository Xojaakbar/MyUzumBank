package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.usecase.transfer.TransferUseCase
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 22.11.2023 at 23:09
*/
@HiltViewModel
class TransferVerifyViewModelImpl @Inject constructor(
    private val transferUseCase: TransferUseCase,
    private val sharedPref: SharedPref,
    private val navigator: AppNavigator
): ViewModel(),TransferVerifyViewModel {
    override val toastLiveData = MutableLiveData<String>()
    override val notificationCodeLiveData = MutableLiveData<String>()

    override fun transferVerified(code: String) {
        val verifyRequest = TransferVerifyRequestParam(code,sharedPref.token)
        Log.d("verify", "transferVerified: $code ${sharedPref.token}")
            transferUseCase.verify(verifyRequest).onEach {
                it.onSuccess {
                    toastLiveData.value = "Перевод успешно выполнено"
                    viewModelScope.launch {

                        navigator.navigateTo(TransferVerifyScreenDirections.actionTransferVerifyScreenToSuccessTransferScreen())
                    }
                }
                it.onFailure { toastLiveData.value = it.message }
                it.onMessage { toastLiveData.value = it }
                it.onNotSend { toastLiveData.value = "Запрос не отправлён" }
            }.launchIn(viewModelScope)
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun makeNotification() {
        notificationCodeLiveData.value = sharedPref.code
    }
}