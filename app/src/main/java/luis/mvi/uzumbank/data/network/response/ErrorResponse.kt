package luis.mvi.uzumbank.data.network.response

/*
created by Xo'jaakbar on 07.10.2023 at 21:02
*/

data class ErrorResponse(
    val errors: Errors,
    val message: String
)