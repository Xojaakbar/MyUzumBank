package luis.mvi.uzumbank.data.network.request

import com.google.gson.annotations.SerializedName

data class TransferRequest(
    val amount: Int,
    val from_card_id: Int,
    @SerializedName("pan")
    val card_number: String
)