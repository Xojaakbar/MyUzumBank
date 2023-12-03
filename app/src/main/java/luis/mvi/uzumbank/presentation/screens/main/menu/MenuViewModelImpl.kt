package luis.mvi.uzumbank.presentation.screens.main.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.data.repository.menu.MenuRepository
import javax.inject.Inject

/*
created by Xo'jaakbar on 19.10.2023 at 18:26
*/

@HiltViewModel
class MenuViewModelImpl @Inject constructor(
    private val repository: MenuRepository
) :ViewModel(), MenuViewModel {

    override val recyclerLiveData = MutableLiveData<List<MenuCategoryData>>()

    override fun initAdapter() {
        recyclerLiveData.value = repository.getMenuCategories()
    }


}