package luis.mvi.uzumbank.data.mappers

import luis.mvi.uzumbank.data.network.request.SignInRequest
import luis.mvi.uzumbank.data.network.request.SignUpRequest
import luis.mvi.uzumbank.data.network.request.SignUpResendRequest
import luis.mvi.uzumbank.data.network.request.SignUpVerifyRequest
import luis.mvi.uzumbank.data.network.response.SignInResponse
import luis.mvi.uzumbank.data.network.response.SignUpResendResponse
import luis.mvi.uzumbank.data.network.response.SignUpResponse
import luis.mvi.uzumbank.data.network.response.SignUpVerifyResponse
import luis.mvi.uzumbank.domain.models.SignInRequestParam
import luis.mvi.uzumbank.domain.models.SignInResponseParam
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendResponseParam
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyResponseParam

/*
created by Xo'jaakbar on 08.10.2023 at 2:57
*/

fun SignUpRequestParam.mapToSignUpRequest(): SignUpRequest =
    SignUpRequest(
        this.first_name,
        this.last_name,
        this.password,
        this.phone_number,
    )

fun SignUpResponse.mapToSignUpRequestParam(): SignUpResponseParam =
    SignUpResponseParam(
        this.code,
        this.token
    )

fun SignInResponse.mapToSignInResponseParam(): SignInResponseParam =
    SignInResponseParam(this.access_token)

fun SignInRequestParam.mapToSignInRequest(): SignInRequest =
    SignInRequest(
        password = this.password,
        phone = this.phone,)

fun SignUpVerifyRequestParam.mapToResponse(): SignUpVerifyRequest =
    SignUpVerifyRequest(this.code,this.token)

fun SignUpVerifyResponse.mapToParam():SignUpVerifyResponseParam = SignUpVerifyResponseParam(this.acccess_token)

fun SignUpResendRequestParam.mapToResponse(): SignUpResendRequest = SignUpResendRequest(this.token)

fun SignUpResendResponse.mapToParam(): SignUpResendResponseParam = SignUpResendResponseParam(this.code, this.token)