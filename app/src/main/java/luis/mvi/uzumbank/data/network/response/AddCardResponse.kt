package luis.mvi.uzumbank.data.network.response

data class AddCardResponse(
    val amount: String,
    val expire_month: Int,
    val expire_year: Int,
    val id: Int,
    val name: String,
    val pan: String,
    val phone_number: String,
    val theme: Any
)