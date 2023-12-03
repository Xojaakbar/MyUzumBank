package luis.mvi.uzumbank.domain.repository.auth

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignInRequestParam
import luis.mvi.uzumbank.domain.models.SignInResponseParam
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendResponseParam
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 08.10.2023 at 3:14
*/

interface AuthRepository {
    fun signUp(signUpRequestParam: SignUpRequestParam): Flow<BaseResult<SignUpResponseParam>>
    fun signIn(signInRequestParam: SignInRequestParam): Flow<BaseResult<SignInResponseParam>>
    fun signUpVerify(signUpVerifyRequestParam: SignUpVerifyRequestParam): Flow<BaseResult<SignUpVerifyResponseParam>>
    fun resend(signUpResendRequestParam: SignUpResendRequestParam): Flow<BaseResult<SignUpResendResponseParam>>
}