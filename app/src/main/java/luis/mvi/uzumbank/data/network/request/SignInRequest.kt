package luis.mvi.uzumbank.data.network.request

import com.google.gson.annotations.SerializedName

/*
created by Xo'jaakbar on 22.09.2023 at 4:49
*/

data class SignInRequest(
    val password:String,
    @SerializedName("phone_number")
    val phone:String,
)
