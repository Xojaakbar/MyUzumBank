package luis.mvi.uzumbank.data.network.request

data class PayRequest(
    val amount: Int,
    val card_id: Int,
    val payment_type_id: Int,
    val phone_number: String
)