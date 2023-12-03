package luis.mvi.uzumbank.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 03.11.2023 at 20:27
*/

interface CardUseCase {
    fun getCardsList(): Flow<BaseResult<CardListResponseParam>>
    fun getAllCards(): Flow<BaseResult<GetAllCardsResponse>>
}