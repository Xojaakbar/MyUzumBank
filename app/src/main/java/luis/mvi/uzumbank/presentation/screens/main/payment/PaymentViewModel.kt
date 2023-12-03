package luis.mvi.uzumbank.presentation.screens.main.payment

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData

/*
created by Xo'jaakbar on 19.09.2023 at 3:13
*/

interface PaymentViewModel {

    val categoryListLiveData: LiveData<List<CategoryData>>
    val favouriteListLiveData: LiveData<List<FavouritePaysData>>
    val rebuildLiveData:LiveData<Boolean>

    fun rebuildCategoryList()

}