package luis.mvi.uzumbank.data.repository.menu

import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.MenuCategoryData
import javax.inject.Inject

/*
created by Xo'jaakbar on 19.10.2023 at 20:11
*/

class MenuRepositoryImpl @Inject constructor(): MenuRepository {

    val categoryList = arrayListOf<MenuCategoryData>(
        MenuCategoryData(img = R.drawable.uz_icon, title = "UzASBO"),
        MenuCategoryData(img = R.drawable.cashback_icon , title = "Кешбек"),
        MenuCategoryData(img = R.drawable.home_icon , title = "Мой дом"),
        MenuCategoryData(img = R.drawable.calendar_icon , title = "Календарь-автоплатежи"),
        MenuCategoryData(img = R.drawable.humopay_icon , title = "HUMOPay"),
        MenuCategoryData(img = R.drawable.bonus_icon , title = "Бонус"),
        MenuCategoryData(img = R.drawable.card_icon2 , title = "Карты"),
        MenuCategoryData(img = R.drawable.add_icon_2 , title = "Заказать карту"),
        MenuCategoryData(img = R.drawable.history_icon , title = "История"),
        MenuCategoryData(img = R.drawable.dollar_icon , title = "Курс валют"),
        MenuCategoryData(img = R.drawable.list_paper_icon2 , title = "Заявка")
    )

    override fun getMenuCategories(): List<MenuCategoryData> {
        return categoryList
    }

}