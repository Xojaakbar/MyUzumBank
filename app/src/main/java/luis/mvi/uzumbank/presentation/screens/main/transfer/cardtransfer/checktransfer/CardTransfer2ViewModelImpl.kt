package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.domain.models.CurrentTransferData
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.usecase.transfer.TransferUseCase
import luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.CardTransferScreenDirections
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 16.11.2023 at 22:31
*/

@HiltViewModel
class CardTransfer2ViewModelImpl @Inject constructor(
    private val pref: SharedPref,
    private val navigator: AppNavigator,
    private val sharedPref: SharedPref,
    private val transferUseCase: TransferUseCase
):ViewModel(), CardTransfer2ViewModel {
    override val toastLiveData = MutableLiveData<String>()
    override val currentLiveData = MutableLiveData<CurrentTransferData>()


    override fun transfer(transferRequestParam: TransferRequestParam) {
        Log.d("transferRequest", "\n ***transferReuest $transferRequestParam")

        transferUseCase.transfer(transferRequestParam).onEach { it ->
            it.onSuccess {
                sharedPref.code = it.code
                sharedPref.token = it.token
                viewModelScope.launch {
                    navigator.navigateTo(CardTransferScreen2Directions.actionCardTransferScreen2ToTransferVerifyScreen())
                }
            }
            it.onFailure {
                Log.d("xatolik", "${it.message} ")
                toastLiveData.value = it.message }
            it.onMessage { toastLiveData.value = it  }
            it.onNotSend { toastLiveData.value = "Операция не выполнена"  }
        }.launchIn(viewModelScope)
    }

    override fun currentTransferInfo(currentTransfer: CurrentTransferData) {
        currentLiveData.value = currentTransfer
    }

    override fun back() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun setSumma(summa: String) {
        sharedPref.summa = summa
    }

}