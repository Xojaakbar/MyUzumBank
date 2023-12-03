package luis.mvi.uzumbank.data.network.response

data class PaymentTypeFieldsResponseItem(
    val created_at: String,
    val id: Int,
    val input_options: Any,
    val input_type: String,
    val is_required: Boolean,
    val key: String,
    val label: String,
    val payment_type_id: Int,
    val updated_at: String
)