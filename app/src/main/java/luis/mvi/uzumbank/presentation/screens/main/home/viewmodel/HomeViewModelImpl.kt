package luis.mvi.uzumbank.presentation.screens.main.home.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import luis.mvi.uzumbank.data.repository.home.HomeRepository
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.usecase.card.CardUseCase
import luis.mvi.uzumbank.presentation.screens.main.home.HomeScreenDirections
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import java.util.PrimitiveIterator
import javax.inject.Inject

/*
created by Xo'jaakbar on 13.09.2023 at 18:03
*/
@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val navigator: AppNavigator,
    private val homeRepository: HomeRepository,
    private val useCase: CardUseCase
):ViewModel(), HomeViewModel {

    override val cardsLiveData = MutableLiveData<List<CardData>>()
    override val fastDostupLiveData = MutableLiveData<List<FastDostupData>>()
    override val oplataNaMestaxLiveData = MutableLiveData<List<OplataNaMestaxData>>()
    override val progressLiveData = MutableLiveData<Boolean>()
    override val showBalanceLiveData = MutableLiveData<Boolean>()
    override val cardsListLiveData = MutableLiveData<CardListResponseParam>()

    override fun getFastList() {
        viewModelScope.launch {
            oplataNaMestaxLiveData.value = homeRepository.getOplataNaMestax()
            fastDostupLiveData.value = homeRepository.getFastDostups()
            progressLiveData.value = false
        }
    }

    override fun profile() {
       viewModelScope.launch {
           navigator.navigateTo(HomeScreenDirections.actionHomeScreenToProfileScreen())
       }
    }

    override fun getCardList() {
            progressLiveData.value = true
//            cardsLiveData.value = homeRepository.getCardData()
            useCase.getCardsList().onEach {
                it.onSuccess { cardsListLiveData.value = it
                progressLiveData.value = false }
                it.onFailure { Log.d("onviewModel", "getCardList: ${it.message}")
                    progressLiveData.value = false}
                it.onMessage { Log.d("onviewModel", "getCardList: $it")
                    progressLiveData.value = false}
                it.onNotSend { Log.d("onviewModel", "getCardList: notsend")
                    progressLiveData.value = false}
            }.launchIn(viewModelScope)
    }

    override fun showBalance() {
        showBalanceLiveData.value = !((showBalanceLiveData.value) ?: true)
    }

    override fun goToSearch() {
        viewModelScope.launch { navigator.navigateTo(HomeScreenDirections.actionHomeScreenToSearchScreen()) }
    }

}