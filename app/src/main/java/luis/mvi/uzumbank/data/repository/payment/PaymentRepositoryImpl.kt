package luis.mvi.uzumbank.data.repository.payment

import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.CategoryData
import luis.mvi.uzumbank.data.local.FavouritePaysData
import luis.mvi.uzumbank.domain.repository.payment.PaymentRepository
import javax.inject.Inject

/*
created by Xo'jaakbar on 19.09.2023 at 3:08
*/

class PaymentRepositoryImpl @Inject constructor() : PaymentRepository {

    val categorylist = listOf(
        CategoryData(R.drawable.phone_icon, "Связь", 6),
        CategoryData(R.drawable.food_icon, "Рестораны", 682),
        CategoryData(R.drawable.magazin_icon, "Магазины и Салоны", 1289),
        CategoryData(R.drawable.home_icon, "Комунальные", 12),
        CategoryData(R.drawable.baseline_medical_information_24, "Медицина", 247),
        CategoryData(R.drawable.baseline_wifi_24, "Провайдеры", 31),
        CategoryData(R.drawable.education_icon, "Образование", 367),
        CategoryData(R.drawable.razvlechenie, "Развлечение", 47),
        CategoryData(R.drawable.baseline_directions_bus_24, "Транспорт", 184),
        CategoryData(R.drawable.baseline_tv_24, "ТВ", 26),
        CategoryData(R.drawable.baseline_wifi_calling_3_24, "Телефония", 5),
        CategoryData(R.drawable.baseline_synagogue_24, "Бюджет", 24),
    )

    val favouritePaysDataLists = listOf(
        FavouritePaysData(R.drawable.add_icon_2, "Добавить"),
        FavouritePaysData(R.drawable.phone_icon, "Мой номер"),
        FavouritePaysData(R.drawable.uzmobile_icon, "Uzmobile"),
        FavouritePaysData(R.drawable.beeline_icon, "Beeline"),
        FavouritePaysData(R.drawable.comnet_icon, "comnet"),
    )

    override fun getCategoryList(): List<CategoryData> {
        return categorylist
    }

    override fun getFavouritePaysList(): List<FavouritePaysData> {
        return favouritePaysDataLists
    }
}