package luis.mvi.uzumbank.domain.usecase.auth.resend

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 08.11.2023 at 20:57
*/

interface ResendUseCase {
    operator fun invoke(response:SignUpResendRequestParam): Flow<BaseResult<SignUpResendResponseParam>>
}