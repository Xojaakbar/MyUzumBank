package luis.mvi.uzumbank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luis.mvi.uzumbank.data.repository.auth.AuthRepositoryImpl
import luis.mvi.uzumbank.data.repository.cards.CardsRepositoryImpl
import luis.mvi.uzumbank.data.repository.home.HomeRepository
import luis.mvi.uzumbank.data.repository.home.HomeRepositoryImpl
import luis.mvi.uzumbank.data.repository.menu.MenuRepository
import luis.mvi.uzumbank.data.repository.menu.MenuRepositoryImpl
import luis.mvi.uzumbank.domain.repository.payment.PaymentRepository
import luis.mvi.uzumbank.data.repository.payment.PaymentRepositoryImpl
import luis.mvi.uzumbank.data.repository.profile.ProfileRepositoryImpl
import luis.mvi.uzumbank.data.repository.transfer.TransferRepositoryImpl
import luis.mvi.uzumbank.domain.repository.auth.AuthRepository
import luis.mvi.uzumbank.domain.repository.cards.CardsRepository
import luis.mvi.uzumbank.domain.repository.profile.ProfileRepository
import luis.mvi.uzumbank.domain.repository.transfer.TransferRepository
import javax.inject.Singleton

/*
created by Xo'jaakbar on 14.09.2023 at 2:29
*/

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModules {

    @Binds
    @Singleton
    fun bindHomeRep(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    @Singleton
    fun bindPaymentRep(impl: PaymentRepositoryImpl): PaymentRepository

    @[Binds Singleton]
    fun authRepositoryBinding(impl:AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindMenuRep(impl:MenuRepositoryImpl):MenuRepository

    @Binds
    @Singleton
    fun bindProfileRep(impl: ProfileRepositoryImpl):ProfileRepository

    @Binds
    @Singleton
    fun bindTransferRep(impl: TransferRepositoryImpl):TransferRepository

    @Binds
    @Singleton
    fun bindCardsRep(impl: CardsRepositoryImpl):CardsRepository

}