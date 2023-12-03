package luis.mvi.uzumbank.presentation.screens.verifysignup

import androidx.lifecycle.LiveData

/*
created by Xo'jaakbar on 06.11.2023 at 0:44
*/

interface SignUpVerifyViewModel {

    fun verify(code:String)
    val progressLiveData:LiveData<Boolean>
    val incorrectLiveData: LiveData<Unit>
    val resendLiveData: LiveData<Unit>
    val codeLiveData:LiveData<String>
    val toastLiveData: LiveData<String>
    fun resend()
    fun back()
    fun setCode()
}