package luis.mvi.uzumbank.domain.repository.cards

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.data.network.response.CardListResponse
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 01.11.2023 at 18:18
*/

interface CardsRepository {

    fun getCardsList(): Flow<BaseResult<CardListResponseParam>>
    fun getAllCards():Flow<BaseResult<GetAllCardsResponse>>
}