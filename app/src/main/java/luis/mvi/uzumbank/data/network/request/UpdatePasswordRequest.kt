package luis.mvi.uzumbank.data.network.request

data class UpdatePasswordRequest(
    val current_password: String,
    val password: String,
    val password_confirmation: String
)