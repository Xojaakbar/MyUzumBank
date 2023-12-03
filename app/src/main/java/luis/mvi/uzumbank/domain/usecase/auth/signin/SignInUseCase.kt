package luis.mvi.uzumbank.domain.usecase.auth.signin

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignInRequestParam
import luis.mvi.uzumbank.domain.models.SignInResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 25.10.2023 at 15:54
*/

interface SignInUseCase {

    operator fun invoke(signInRequestParam: SignInRequestParam): Flow<BaseResult<SignInResponseParam>>

}