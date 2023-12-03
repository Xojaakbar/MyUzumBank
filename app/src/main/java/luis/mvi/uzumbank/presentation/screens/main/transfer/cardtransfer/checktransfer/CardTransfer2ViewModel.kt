package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.domain.models.CurrentTransferData
import luis.mvi.uzumbank.domain.models.TransferRequestParam

/*
created by Xo'jaakbar on 16.11.2023 at 22:30
*/

interface CardTransfer2ViewModel {
    val toastLiveData: LiveData<String>
    val currentLiveData:LiveData<CurrentTransferData>
    fun transfer(transferRequestParam: TransferRequestParam)
    fun currentTransferInfo(currentTransfer: CurrentTransferData)
    fun back()
    fun setSumma(summa:String)
}