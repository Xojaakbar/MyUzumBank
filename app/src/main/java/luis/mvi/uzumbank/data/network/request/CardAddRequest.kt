package luis.mvi.uzumbank.data.network.request

import com.google.gson.annotations.SerializedName

data class CardAddRequest(
    @SerializedName("expire_month")
    val expireMonth: Int,
    @SerializedName("expire_year")
    val expireYear: Int,
    val name: String,
    val pan: String
)