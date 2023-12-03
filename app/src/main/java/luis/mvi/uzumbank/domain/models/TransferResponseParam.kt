package luis.mvi.uzumbank.domain.models

import luis.mvi.uzumbank.data.network.response.ErrorsXXXX

/*
created by Xo'jaakbar on 30.10.2023 at 16:24
*/

data class TransferResponseParam(
    val token: String,
    val code: String
)
