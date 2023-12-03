package luis.mvi.uzumbank.domain.models

/*
created by Xo'jaakbar on 08.10.2023 at 2:40
*/

data class SignUpRequestParam (
    val first_name: String,
    val last_name: String,
    val password: String,
    val phone_number: String
)
