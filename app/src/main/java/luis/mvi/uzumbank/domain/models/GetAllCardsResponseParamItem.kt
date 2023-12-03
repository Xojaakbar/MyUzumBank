package luis.mvi.uzumbank.domain.models

data class GetAllCardsResponseParamItem(
    val amount: String,
    val created_at: String,
    val expire_month: Int,
    val expire_year: Int,
    val id: Int,
    val owner: String,
    val pan: String,
    val phone_number: String,
    val updated_at: String
)