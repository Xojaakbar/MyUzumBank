package luis.mvi.uzumbank.domain.models

import com.google.gson.annotations.SerializedName

/*
created by Xo'jaakbar on 30.10.2023 at 16:20
*/

data class TransferRequestParam(
    val from_card_id: Int = 1,
    @SerializedName("pan")
    val card_number: String,
    val amount: Int
)
