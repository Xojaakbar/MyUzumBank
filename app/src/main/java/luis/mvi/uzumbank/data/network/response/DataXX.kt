package luis.mvi.uzumbank.data.network.response

import luis.mvi.uzumbank.domain.models.CurrentTransferData

data class DataXX(
    val amount: String,
    val expire_month: Int,
    val expire_year: Int,
    val id: Int,
    val name: String,
    val pan: String,
    val phone_number: String,
    val theme: Any
){
    fun toCurrentCardData(): CurrentTransferData =
        CurrentTransferData(myCardsPan = pan,
            myCardsAmount = amount,
            expire_month = expire_month,
            expire_year = expire_year,
            fromCardId = id,
            myCardsName = name,
            toCard = "",
            phone_number = phone_number,
            transferSumma = 0,
            otherCardsName = "")
}