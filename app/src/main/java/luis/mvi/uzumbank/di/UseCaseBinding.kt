package luis.mvi.uzumbank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luis.mvi.uzumbank.domain.usecase.auth.resend.ResendUseCase
import luis.mvi.uzumbank.domain.usecase.auth.resend.ResendUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.card.CardUseCase
import luis.mvi.uzumbank.domain.usecase.card.CardUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.profile.ProfileUseCase
import luis.mvi.uzumbank.domain.usecase.profile.ProfileUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.auth.signin.SignInUseCase
import luis.mvi.uzumbank.domain.usecase.auth.signin.SignInUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.auth.signup.SignUpUseCase
import luis.mvi.uzumbank.domain.usecase.auth.signup.SignUpUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.auth.signupverify.SignUpVerifyUseCase
import luis.mvi.uzumbank.domain.usecase.auth.signupverify.SignUpVerifyUseCaseImpl
import luis.mvi.uzumbank.domain.usecase.transfer.TransferUseCase
import luis.mvi.uzumbank.domain.usecase.transfer.TransferUseCaseImpl

/*
created by Xo'jaakbar on 12.10.2023 at 2:22
*/

@Module
@InstallIn(SingletonComponent::class)
interface UseCaseBinding {
    @Binds
    fun signUpUseCaseBinding(impl: SignUpUseCaseImpl): SignUpUseCase

    @Binds
    fun signInUseCaseBinding(impl: SignInUseCaseImpl): SignInUseCase

    @Binds
    fun profileUseCaseBinding(impl:ProfileUseCaseImpl):ProfileUseCase

    @Binds
    fun cardUseCaseBinding(impl:CardUseCaseImpl):CardUseCase

    @Binds
    fun signUpVerifyUseCaseBinding(impl: SignUpVerifyUseCaseImpl): SignUpVerifyUseCase

    @Binds
    fun signUpResendUseCaseBinding(impl: ResendUseCaseImpl): ResendUseCase

    @Binds
    fun transferUseCaseBinding(impl: TransferUseCaseImpl): TransferUseCase

}