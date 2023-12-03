package luis.mvi.uzumbank.domain.usecase.auth.signupverify

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyResponseParam
import luis.mvi.uzumbank.domain.repository.auth.AuthRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 06.11.2023 at 19:17
*/

class SignUpVerifyUseCaseImpl @Inject constructor(
    private val repository:AuthRepository
): SignUpVerifyUseCase {
    override fun invoke(signUpVerifyResponseParam: SignUpVerifyRequestParam): Flow<BaseResult<SignUpVerifyResponseParam>> {
        return repository.signUpVerify(signUpVerifyResponseParam)
    }
}