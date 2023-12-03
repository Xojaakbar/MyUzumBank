package luis.mvi.uzumbank.data.local

/*
created by Xo'jaakbar on 13.09.2023 at 18:49
*/

data class CardData(
    val balance:String,
    val type: CardEnum,
    val lastFourNumber: String
)
