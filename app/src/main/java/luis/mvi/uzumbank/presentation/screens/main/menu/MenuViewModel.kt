package luis.mvi.uzumbank.presentation.screens.main.menu

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.data.local.MenuCategoryData

/*
created by Xo'jaakbar on 19.10.2023 at 18:26
*/

interface MenuViewModel {

    val recyclerLiveData:LiveData<List<MenuCategoryData>>
    fun initAdapter()
}