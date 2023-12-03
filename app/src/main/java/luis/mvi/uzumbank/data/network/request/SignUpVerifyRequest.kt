package luis.mvi.uzumbank.data.network.request

data class SignUpVerifyRequest(
    val code: String,
    val token: String
)