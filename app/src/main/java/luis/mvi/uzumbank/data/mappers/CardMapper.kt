package luis.mvi.uzumbank.data.mappers

import luis.mvi.uzumbank.data.network.response.CardListResponse
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.models.GetAllCardsResponseParam

/*
created by Xo'jaakbar on 01.11.2023 at 18:23
*/

fun CardListResponse.toParam(): CardListResponseParam =
    CardListResponseParam(data, links, meta)
