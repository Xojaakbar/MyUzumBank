package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.response.HistoryByCardResponse
import luis.mvi.uzumbank.data.network.response.HistoryResponse
import retrofit2.Response
import retrofit2.http.GET

/*
created by Xo'jaakbar on 17.10.2023 at 22:24
*/

interface HistoryApi {

    @GET("history")
    fun getHistory():Response<HistoryResponse>

    @GET("history/for-card/1")
    fun getHistoryByCard():Response<HistoryByCardResponse>

}