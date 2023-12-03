package luis.mvi.uzumbank.presentation.screens.passcodescreen

import androidx.lifecycle.LiveData

/*
created by Xo'jaakbar on 01.10.2023 at 2:43
*/

interface PasscodeFragViewModel {

    val toastLiveData: LiveData<String>
    val closeScreenLiveData: LiveData<Unit>
    val quitLiveData:LiveData<Unit>
    val whatNumberLiveData: LiveData<Int>
    val showDialogLiveData: LiveData<Unit>

    fun isCorrectPassword(password: List<String>)
    fun exitAccount()
    fun quite()
    fun dialog()
    fun numbers(which: Int)
    fun biometricScan(isSimilar:Boolean)
}