package luis.mvi.uzumbank.presentation.screens.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/*
created by Xo'jaakbar on 22.09.2023 at 4:45
*/

interface SignInViewModel {

    val showToastLiveData: LiveData<String>

    fun signIn(login:String, password:String)
    val progressLiveData: MutableLiveData<Boolean>
    fun goToSignUp()
}