package luis.mvi.uzumbank.domain.usecase.auth.signup

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.repository.auth.AuthRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 08.10.2023 at 3:17
*/

class SignUpUseCaseImpl @Inject constructor(private val repository: AuthRepository): SignUpUseCase {
    override fun invoke(signUpRequestParam: SignUpRequestParam): Flow<BaseResult<SignUpResponseParam>> =
        repository.signUp(signUpRequestParam)

}