package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.models.CurrentTransferData
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.usecase.card.CardUseCase
import luis.mvi.uzumbank.domain.usecase.transfer.TransferUseCase
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 30.10.2023 at 4:16
*/
@HiltViewModel
class CardTransferViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val cardUseCase: CardUseCase
):ViewModel(),CardTransferViewModel {
    override val toastLiveData = MutableLiveData<String>()
    override val dialogLiveData = MutableLiveData<Unit>()
    override val cardsListLiveData = MutableLiveData<CardListResponseParam>()
    override val cardLiveData = MutableLiveData<DataXX>()
    override val allCardsLiveData = MutableLiveData<GetAllCardsResponse>()
    override val otherCardsNameLiveData = MutableLiveData<String>()

    override fun backToHome() {
        viewModelScope.launch {
            navigator.back()
        }
    }

    override fun goToTransfer(currentTransferData: CurrentTransferData) {
        viewModelScope.launch {
        navigator.navigateTo(CardTransferScreenDirections.actionCardTransferScreenToCardTransferScreen2(currentTransferData))
        }
    }

    override fun chooseCard() {
        dialogLiveData.value = Unit
    }

    override fun chosenCard(card: DataXX) {
        cardLiveData.value = card
    }

    override fun getCardsList() {
                cardUseCase.getCardsList().onEach { it ->
                    it.onSuccess { cardsListLiveData.value = it }
                it.onFailure { Log.d("onviewModel", "getCardList: ${it.message}") }
                it.onMessage { Log.d("onviewModel", "getCardList: $it")}
                it.onNotSend { Log.d("onviewModel", "getCardList: not send")}
            }.launchIn(viewModelScope)
    }

    override fun getAllCards(){
        cardUseCase.getAllCards().onEach {
            it.onSuccess { allCardsLiveData.value = it }
            it.onFailure { toastLiveData.value = it.message }
            it.onMessage { toastLiveData.value = it }
            it.onNotSend { toastLiveData.value = "Запрос не отправлён" }
        }.launchIn(viewModelScope)
    }

    override fun searchCard(pan: String) {
        allCardsLiveData.value?.onEach {
            if (it.pan==pan){
                otherCardsNameLiveData.value = it.owner
            }
        }
    }
}