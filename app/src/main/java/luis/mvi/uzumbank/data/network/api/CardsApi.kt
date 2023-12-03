package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.response.DeleteCardResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import luis.mvi.uzumbank.data.network.request.CardAddRequest
import luis.mvi.uzumbank.data.network.request.CreateCardRequest
import luis.mvi.uzumbank.data.network.response.AddCardResponse
import luis.mvi.uzumbank.data.network.response.CardListResponse
import uz.gita.mobilebankingafb_5.data.network.request.CardsUpdateRequest
import luis.mvi.uzumbank.data.network.response.CardsErrorResponse
import luis.mvi.uzumbank.data.network.response.CreateCardResponse
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import retrofit2.http.GET

/*
created by Xo'jaakbar on 16.10.2023 at 10:31
*/

interface CardsApi {

    @GET("cards")
    suspend fun getCardsList():Response<CardListResponse>

    @PUT("cards/1")
    suspend fun updateCard(@Body request: CardsUpdateRequest):Response<CardsErrorResponse>

    @DELETE("cards/1")
    suspend fun deleteCard():Response<DeleteCardResponse>

    @POST("cards")
    suspend fun addCard(@Body updateVerifyRequest: CardAddRequest): Response<AddCardResponse>

    @POST("cards/create")
    suspend fun createCard(@Body createCardRequest: CreateCardRequest): Response<CreateCardResponse>

    @GET("all-cards")
    suspend fun getAllCards(): Response<GetAllCardsResponse>
}
