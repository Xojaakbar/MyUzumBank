package luis.mvi.uzumbank.presentation.screens.main.transfer.cardtransfer.checktransfer.verifytransfer.successtransfer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 23.11.2023 at 22:14
*/
@HiltViewModel
class SuccessViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val sharedPref: SharedPref
):ViewModel(),SuccessTransferViewModel {

    override val summaLiveData = MutableLiveData<String>()

    override fun goHome() {
        viewModelScope.launch {
            navigator.navigateTo(SuccessTransferScreenDirections.actionSuccessTransferScreenToHomeScreen())
        }
    }

    init {
        summaLiveData.value = sharedPref.summa
    }
}