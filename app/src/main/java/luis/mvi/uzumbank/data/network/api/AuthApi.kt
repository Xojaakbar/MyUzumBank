package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.request.SignInRequest
import luis.mvi.uzumbank.data.network.request.SignUpRequest
import luis.mvi.uzumbank.data.network.request.SignUpResendRequest
import luis.mvi.uzumbank.data.network.request.SignUpVerifyRequest
import luis.mvi.uzumbank.data.network.response.SignInResponse
import luis.mvi.uzumbank.data.network.response.SignUpResponse
import luis.mvi.uzumbank.data.network.response.SignUpVerifyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import luis.mvi.uzumbank.data.network.response.SignUpResendResponse

/*
created by Xo'jaakbar on 07.10.2023 at 20:18
*/

interface AuthApi {

    @POST("auth/sign-up")
    suspend fun signUp(@Body signUpRequest: SignUpRequest): Response<SignUpResponse>

    @POST("auth/sign-in")
    suspend fun signIn(@Body signInRequest: SignInRequest): Response<SignInResponse>

    @POST("auth/sign-up/verify")
    suspend fun signUpVerify(@Body signUpVerifyRequest: SignUpVerifyRequest): Response<SignUpVerifyResponse>

    @POST("auth/sign-up/resend")
    suspend fun signUpResend(@Body signUpResendRequest: SignUpResendRequest): Response<SignUpResendResponse>

}