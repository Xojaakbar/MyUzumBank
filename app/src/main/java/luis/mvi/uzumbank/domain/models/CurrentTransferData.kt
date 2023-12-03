package luis.mvi.uzumbank.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
created by Xo'jaakbar on 16.11.2023 at 23:46
*/
@Parcelize
data class CurrentTransferData(
    val myCardsPan:String,
    val myCardsAmount: String,
    val expire_month: Int,
    val expire_year: Int,
    val fromCardId: Int,
    val myCardsName: String,
    var otherCardsName: String,
    var toCard: String,
    val phone_number: String,
    var transferSumma: Long
) : Parcelable