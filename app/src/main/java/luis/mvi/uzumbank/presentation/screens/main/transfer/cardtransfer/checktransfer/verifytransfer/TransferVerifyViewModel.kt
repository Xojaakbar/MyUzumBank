package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam

/*
created by Xo'jaakbar on 22.11.2023 at 23:09
*/

interface TransferVerifyViewModel {
    val toastLiveData:LiveData<String>
    val notificationCodeLiveData:LiveData<String>
    fun transferVerified(code: String)
    fun back()
    fun makeNotification()
}