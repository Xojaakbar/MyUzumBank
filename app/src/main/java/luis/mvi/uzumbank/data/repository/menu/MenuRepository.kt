package luis.mvi.uzumbank.data.repository.menu

import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.MenuCategoryData

/*
created by Xo'jaakbar on 19.10.2023 at 20:11
*/

interface MenuRepository {
    fun getMenuCategories():List<MenuCategoryData>
}