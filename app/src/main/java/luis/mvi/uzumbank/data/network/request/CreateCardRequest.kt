package luis.mvi.uzumbank.data.network.request

data class CreateCardRequest(
    val amount: String,
    val expire_month: Int,
    val expire_year: Int,
    val name: String,
    val owner: String,
    val pan: String,
    val phone_number: String
)