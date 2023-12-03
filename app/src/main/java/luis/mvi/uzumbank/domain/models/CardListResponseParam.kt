package luis.mvi.uzumbank.domain.models

import luis.mvi.uzumbank.data.network.response.DataXX
import luis.mvi.uzumbank.data.network.response.LinksXX
import luis.mvi.uzumbank.data.network.response.MetaXX

/*
created by Xo'jaakbar on 01.11.2023 at 18:20
*/

data class CardListResponseParam(
    val `data`: List<DataXX>,
    val links: LinksXX,
    val meta: MetaXX
)
