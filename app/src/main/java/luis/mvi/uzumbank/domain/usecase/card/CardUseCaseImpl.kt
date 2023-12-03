package luis.mvi.uzumbank.domain.usecase.card

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.repository.cards.CardsRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 03.11.2023 at 20:28
*/

class CardUseCaseImpl @Inject constructor(
    private val repository:CardsRepository
):CardUseCase {
    override fun getCardsList(): Flow<BaseResult<CardListResponseParam>> =
        repository.getCardsList()

    override fun getAllCards(): Flow<BaseResult<GetAllCardsResponse>> {
        return repository.getAllCards()
    }

}