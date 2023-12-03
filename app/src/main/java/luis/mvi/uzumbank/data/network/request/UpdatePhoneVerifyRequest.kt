package luis.mvi.uzumbank.data.network.request

data class UpdatePhoneVerifyRequest(
    val code: String,
    val token: String
)