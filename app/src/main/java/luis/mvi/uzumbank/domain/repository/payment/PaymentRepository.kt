package luis.mvi.uzumbank.domain.repository.payment

import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData

/*
created by Xo'jaakbar on 19.09.2023 at 3:08
*/

interface PaymentRepository {

    fun getCategoryList():List<CategoryData>
    fun getFavouritePaysList():List<FavouritePaysData>

}