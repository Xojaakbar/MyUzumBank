package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.request.TransferRequest
import luis.mvi.uzumbank.data.network.request.TransferResendRequest
import luis.mvi.uzumbank.data.network.request.TransferVerifyRequest
import luis.mvi.uzumbank.data.network.response.TransferResendResponse
import luis.mvi.uzumbank.data.network.response.TransferResponse
import luis.mvi.uzumbank.data.network.response.TransferVerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

/*
created by Xo'jaakbar on 17.10.2023 at 21:39
*/

interface TransferApi {

    @POST("transfers")
    suspend fun transfer(@Body transferRequest: TransferRequest):Response<TransferResponse>

    @POST("transfers/verify")
    suspend fun transferVerify(@Body transferVerifyRequest: TransferVerifyRequest):Response<TransferVerifyResponse>

    @POST("transfers/resend")
    suspend fun transferResend(@Body transferResendRequest: TransferResendRequest):Response<TransferResendResponse>

}