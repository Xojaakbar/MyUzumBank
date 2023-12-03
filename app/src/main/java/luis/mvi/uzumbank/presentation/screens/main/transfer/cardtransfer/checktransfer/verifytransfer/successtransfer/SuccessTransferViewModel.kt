package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer.successtransfer

import androidx.lifecycle.LiveData

/*
created by Xo'jaakbar on 23.11.2023 at 22:14
*/

interface SuccessTransferViewModel {
    val summaLiveData:LiveData<String>
    fun goHome()
}