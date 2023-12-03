package luis.mvi.uzumbank.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import luis.mvi.uzumbank.utils.ConnectivityObserver
import luis.mvi.uzumbank.utils.NetworkConnectivityObserver
import javax.inject.Singleton

/*
created by Xo'jaakbar on 23.09.2023 at 17:38
*/
@Module
@InstallIn(SingletonComponent::class)
interface InternetConnectionModule {

      @Binds
      fun bindConnection(networkConnectivityObserver: NetworkConnectivityObserver): ConnectivityObserver

}