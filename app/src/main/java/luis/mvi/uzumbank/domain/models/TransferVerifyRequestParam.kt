package luis.mvi.uzumbank.domain.models

/*
created by Xo'jaakbar on 31.10.2023 at 1:37
*/

data class TransferVerifyRequestParam(
    val code: String,
    val token: String,
)
