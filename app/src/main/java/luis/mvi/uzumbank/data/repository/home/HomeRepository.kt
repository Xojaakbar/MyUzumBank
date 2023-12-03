package luis.mvi.uzumbank.data.repository.home

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 13.09.2023 at 18:59
*/

interface HomeRepository {
    fun getFastDostups():List<FastDostupData>
    fun getOplataNaMestax():List<OplataNaMestaxData>
}