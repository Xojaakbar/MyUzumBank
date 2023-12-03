package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.models.CurrentTransferData

/*
created by Xo'jaakbar on 30.10.2023 at 4:15
*/

interface CardTransferViewModel {

    val toastLiveData: LiveData<String>
    val dialogLiveData: LiveData<Unit>
    val cardsListLiveData: LiveData<CardListResponseParam>
    val cardLiveData:LiveData<DataXX>
    val allCardsLiveData:LiveData<GetAllCardsResponse>
    val otherCardsNameLiveData:LiveData<String>

    fun backToHome()

    fun goToTransfer(currentTransferData: CurrentTransferData)

    fun chooseCard()

    fun chosenCard(card: DataXX)

    fun getCardsList()

    fun getAllCards()

    fun searchCard(pan:String)
}