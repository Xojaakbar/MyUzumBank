package luis.mvi.uzumbank.presentation.screens.signup

import androidx.lifecycle.LiveData

/*
created by Xo'jaakbar on 30.07.2023 at 15:44
*/

interface SignUpViewModel {

    val toastLiveData: LiveData<String>
    val progressLiveData: LiveData<Boolean>

    fun goToSignIn()

    fun signUp(phone:String, firstName:String, lastName:String,password:String,confirmPassword:String)

}