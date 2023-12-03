package luis.mvi.uzumbank.presentation.screens.passcodescreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.ConnectivityObserver
import luis.mvi.uzumbank.utils.SharedPref
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import java.util.Arrays
import java.util.Collections
import javax.inject.Inject

/*
created by Xo'jaakbar on 01.10.2023 at 2:43
*/

@HiltViewModel
class PasscodeFragViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val sharedPref: SharedPref
) : ViewModel(),PasscodeFragViewModel {
    override val toastLiveData = MutableLiveData<String>()
    override val closeScreenLiveData = MutableLiveData<Unit>()
    override val quitLiveData = MutableLiveData<Unit>()
    override val whatNumberLiveData = MutableLiveData<Int>()
    override val showDialogLiveData = MutableLiveData<Unit>()

    override fun isCorrectPassword(password: List<String>) {
        Log.d("password", "in viewModel: $password")
        if (password.toString()=="[0, 0, 0, 0]")
            viewModelScope.launch {
                if (isFirstTime){
                    isFirstTime = false
                    if (!sharedPref.isLogIn){
                    navigator.navigateTo(PasscodeScreenDirections.actionPasscodeScreen2ToSignInScreen())}
                    else{
                        navigator.navigateTo(PasscodeScreenDirections.actionPasscodeScreen2ToHomeScreen())
                    }
                }
                else{
                    closeScreenLiveData.value = Unit
                }
            }
        else toastLiveData.value = "PIN-Код не совпадает"
    }

    override fun exitAccount() {
        viewModelScope.launch {
            sharedPref.isLogIn = false
            sharedPref.token = ""
            sharedPref.code = ""
            sharedPref.password = ""
            sharedPref.accessToken = ""
            sharedPref.phone = ""
            navigator.navigateTo(PasscodeScreenDirections.actionPasscodeScreen2ToSignInScreen())
        }
    }

    override fun quite() {
        quitLiveData.value = Unit
    }

    override fun dialog() {
        showDialogLiveData.value = Unit
    }

    override fun numbers(which: Int) {
        whatNumberLiveData.value = which
    }

    override fun biometricScan(isSimilar: Boolean) {
        if (isSimilar){
            if (sharedPref.isLogIn){
                viewModelScope.launch {
                    navigator.navigateTo(PasscodeScreenDirections.actionPasscodeScreen2ToHomeScreen())
                }
            } else {
                viewModelScope.launch {
                    navigator.navigateTo(PasscodeScreenDirections.actionPasscodeScreen2ToSignInScreen())
                }
            }
        }
    }
}
