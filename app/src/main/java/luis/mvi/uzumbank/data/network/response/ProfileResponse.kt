package luis.mvi.uzumbank.data.network.response

data class ProfileResponse(
    val created_at: String,
    val email: Any,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val name: Any,
    val phone_number: String,
    val phone_number_verified: Boolean,
    val updated_at: String
)