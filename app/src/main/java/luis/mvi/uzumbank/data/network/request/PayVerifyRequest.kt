package luis.mvi.uzumbank.data.network.request

data class PayVerifyRequest(
    val code: String,
    val token: String
)