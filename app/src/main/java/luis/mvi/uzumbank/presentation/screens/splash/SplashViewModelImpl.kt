package luis.mvi.uzumbank.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 25.09.2023 at 14:35
*/

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
):ViewModel(), SplashViewModel {

    override fun openNavigateScreen() {
        viewModelScope.launch {
            delay(800)
            navigator.navigateTo(SplashScreenDirections.actionSplashScreenToPasscodeScreen2())
        }
    }
}