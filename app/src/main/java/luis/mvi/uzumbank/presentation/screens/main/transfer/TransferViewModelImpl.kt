package luis.mvi.uzumbank.presentation.screens.main.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 17.09.2023 at 3:56
*/

@HiltViewModel
class TransferViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
):ViewModel(),TransferViewModel {

    override fun cardScreen() {
        viewModelScope.launch {
            appNavigator.navigateTo(TransferScreenDirections.actionTransferScreenToCardTransferScreen())
        }
    }

    override fun schetScreen() {
        viewModelScope.launch {
            appNavigator.navigateTo(TransferScreenDirections.actionTransferScreenToTransferNaSchetScreen())
        }
    }

    override fun rekvizitScreen() {
        viewModelScope.launch {
           appNavigator.navigateTo(TransferScreenDirections.actionTransferScreenToRekvizitTransfer())
        }
    }

    override fun walletScreen() {

    }

}