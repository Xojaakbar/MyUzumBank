package luis.mvi.uzumbank.presentation.screens.main.home.viewmodel

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import luis.mvi.uzumbank.domain.models.CardListResponseParam

/*
created by Xo'jaakbar on 13.09.2023 at 18:03
*/

interface HomeViewModel {

    val cardsLiveData: LiveData<List<CardData>>
    val fastDostupLiveData: LiveData<List<FastDostupData>>
    val oplataNaMestaxLiveData: LiveData<List<OplataNaMestaxData>>
    val progressLiveData:LiveData<Boolean>
    val showBalanceLiveData:LiveData<Boolean>
    val cardsListLiveData:LiveData<CardListResponseParam>

    fun getFastList()
    fun profile()
    fun getCardList()
    fun showBalance()
    fun goToSearch()
}