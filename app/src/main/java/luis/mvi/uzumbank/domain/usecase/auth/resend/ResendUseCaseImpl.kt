package luis.mvi.uzumbank.domain.usecase.auth.resend

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendResponseParam
import luis.mvi.uzumbank.domain.repository.auth.AuthRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 08.11.2023 at 20:58
*/

class ResendUseCaseImpl @Inject constructor(
    private val repository: AuthRepository
):ResendUseCase {
    override fun invoke(response: SignUpResendRequestParam): Flow<BaseResult<SignUpResendResponseParam>> {
        return repository.resend(response)
    }
}