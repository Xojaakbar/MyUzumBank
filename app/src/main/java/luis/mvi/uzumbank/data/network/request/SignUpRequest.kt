package luis.mvi.uzumbank.data.network.request

/*
created by Xo'jaakbar on 07.10.2023 at 20:58
*/

data class SignUpRequest(
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_number: String
)
