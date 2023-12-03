package luis.mvi.uzumbank.domain.usecase.auth.signupverify

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 06.11.2023 at 18:58
*/

interface SignUpVerifyUseCase {
    operator fun invoke(signUpVerifyResponseParam: SignUpVerifyRequestParam): Flow<BaseResult<SignUpVerifyResponseParam>>
}