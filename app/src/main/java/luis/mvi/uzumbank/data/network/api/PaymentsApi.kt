package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.request.PayRequest
import luis.mvi.uzumbank.data.network.request.PayVerifyRequest
import luis.mvi.uzumbank.data.network.response.PayResponse
import luis.mvi.uzumbank.data.network.response.PayVerifyResponse
import luis.mvi.uzumbank.data.network.response.PaymentCategoriesResponse
import luis.mvi.uzumbank.data.network.response.PaymentTypeFieldsResponse
import luis.mvi.uzumbank.data.network.response.PaymentTypesByCategoriesResponse
import luis.mvi.uzumbank.data.network.response.SingleCategoriesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/*
created by Xo'jaakbar on 17.10.2023 at 21:57
*/

interface PaymentsApi {

    @GET("payments/categories?include=types")
    fun getPaymentCategories():Response<PaymentCategoriesResponse>

    @GET("payments/categories/1")
    fun getSingleCategories():Response<SingleCategoriesResponse>

    @GET("payments/categories/1/types")
    fun getPaymentTypesByCategories():Response<PaymentTypesByCategoriesResponse>

    @GET("payments/types/1/fields")
    fun getPaymentTypeFields():Response<PaymentTypeFieldsResponse>

    @POST("payments/pay")
    fun pay(@Body payRequest: PayRequest):Response<PayResponse>

    @POST("payments/pay/verify")
    fun payVerify(@Body payVerifyRequest: PayVerifyRequest):Response<PayVerifyResponse>

}