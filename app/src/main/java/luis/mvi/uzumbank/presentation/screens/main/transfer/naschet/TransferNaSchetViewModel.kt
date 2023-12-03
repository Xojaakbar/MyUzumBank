package luis.mvi.uzumbank.presentation.screens.main.transfer.naschet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 30.10.2023 at 4:05
*/

@HiltViewModel
class TransferNaSchetViewModel @Inject constructor(
    private val navigator: AppNavigator
):ViewModel() {
    fun backToHome(){
        viewModelScope.launch {
            navigator.back()
        }
    }
}