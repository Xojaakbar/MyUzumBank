package luis.mvi.uzumbank.data.network.api

import luis.mvi.uzumbank.data.network.request.UpdatePasswordRequest
import luis.mvi.uzumbank.data.network.request.UpdatePhoneNumberRequest
import luis.mvi.uzumbank.data.network.request.UpdatePhoneVerifyRequest
import luis.mvi.uzumbank.data.network.request.UpdateProfileRequest
import luis.mvi.uzumbank.data.network.response.ProfileResponse
import luis.mvi.uzumbank.data.network.response.UpdatePasswordResponse
import luis.mvi.uzumbank.data.network.response.UpdatePhoneNumberResponse
import luis.mvi.uzumbank.data.network.response.UpdatePhoneVerifyResponse
import luis.mvi.uzumbank.data.network.response.UpdateProfileResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/*
created by Xo'jaakbar on 17.10.2023 at 21:19
*/

interface ProfileApi {

    @GET("profile")
   suspend fun getProfileInfo():Response<ProfileResponse>

    @POST("profile/update")
    suspend fun updateProfile(@Body updateProfileRequest: UpdateProfileRequest):Response<UpdateProfileResponse>

    @POST("profile/update-password")
    suspend fun updatePassword(@Body updatePasswordRequest: UpdatePasswordRequest): Response<UpdatePasswordResponse>

    @POST("profile/update-phone")
    suspend fun updatePhoneNumber(@Body updatePhoneNumberRequest: UpdatePhoneNumberRequest): Response<UpdatePhoneNumberResponse>


    @POST("profile/update-phone/verify")
    suspend fun updatePhoneVerify(@Body updatePhoneVerifyRequest: UpdatePhoneVerifyRequest): Response<UpdatePhoneVerifyResponse>

}