package luis.mvi.uzumbank.domain.usecase.auth.signup

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 08.10.2023 at 3:17
*/

interface SignUpUseCase {
    operator fun invoke(signUpRequestParam: SignUpRequestParam): Flow<BaseResult<SignUpResponseParam>>
}