package luis.mvi.uzumbank.data.repository.home

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.data.local.CardData
import luis.mvi.uzumbank.data.local.CardEnum
import luis.mvi.uzumbank.data.local.FastDostupData
import luis.mvi.uzumbank.data.local.OplataNaMestaxData
import javax.inject.Inject

/*
created by Xo'jaakbar on 13.09.2023 at 18:59
*/

class HomeRepositoryImpl @Inject constructor(): HomeRepository {

    private val fastList = ArrayList<FastDostupData>()
    private val oplataNaMestaxList = ArrayList<OplataNaMestaxData>()


    init {
        oplataNaMestaxList.add(OplataNaMestaxData("https://cdn.dribbble.com/users/2206920/screenshots/4574962/gaming_club.png","GAME CLUB EGOIST",20))
        oplataNaMestaxList.add(OplataNaMestaxData("https://befit.uz/wp-content/themes/befit/assets/images/befit-box.webp","Befit Wear",15))
        oplataNaMestaxList.add(OplataNaMestaxData("https://main-cdn.sbermegamarket.ru/big1/hlr-system/-12/244/167/015/112/19/600011929418b0.png","AMBASSADOR",10))
        oplataNaMestaxList.add(OplataNaMestaxData("https://i.ytimg.com/vi/OUxOrPilR_U/maxresdefault.jpg","Bargello Мега",10))
        oplataNaMestaxList.add(OplataNaMestaxData("https://bargello.ch/cdn/shop/files/15136044511649327020_e479c560-b05d-4254-a926-fad7ca93db74_1200x.jpg?v=1683982260","Bargello Shuxrat",10))
        oplataNaMestaxList.add(OplataNaMestaxData("https://cdn.dsmcdn.com/mnresize/500/-/ty421/product/media/images/20220508/0/105562354/339684830/1/1_org.jpg","Bargello Kokand",10))
        oplataNaMestaxList.add(OplataNaMestaxData("https://championat.uz/upload/storage/136965_680.jpg","Bargello Bunyodkor",15))

        fastList.add(FastDostupData("https://cdn6.aptoide.com/imgs/b/9/0/b90d98b0e1aaa6be17c169204a0452ef_icon.png","Мой номер"))
        fastList.add(FastDostupData("https://cdn-icons-png.flaticon.com/512/25/25694.png","Мой дом"))
        fastList.add(FastDostupData("https://www.kapitalbank.uz/upload/iblock/9fe/system_humo_c.png","Humo Pay"))
        fastList.add(FastDostupData("https://cdn-icons-png.flaticon.com/512/3779/3779276.png","Обмен валюты"))
        fastList.add(FastDostupData("https://cdn-icons-png.flaticon.com/512/126/126186.png","Вклады"))
        fastList.add(FastDostupData("https://static.vecteezy.com/system/resources/previews/000/357/048/original/vector-credit-card-icon.jpg","Кредиты"))
        fastList.add(FastDostupData("https://static.thenounproject.com/png/5247502-200.png","Курс валят"))
        fastList.add(FastDostupData("https://cdn-icons-png.flaticon.com/512/3003/3003948.png","Международные переводы"))
        fastList.add(FastDostupData("https://cdn-icons-png.flaticon.com/512/3359/3359235.png","Кошельки"))
    }

    override fun getFastDostups(): List<FastDostupData> {
            return fastList
    }

    override fun getOplataNaMestax(): List<OplataNaMestaxData> {
        return oplataNaMestaxList
    }
}