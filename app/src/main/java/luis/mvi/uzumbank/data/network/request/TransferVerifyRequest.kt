package luis.mvi.uzumbank.data.network.request

data class TransferVerifyRequest(
    val code: String,
    val token: String
)