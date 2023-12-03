package luis.mvi.uzumbank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import luis.mvi.uzumbank.utils.navigator.NavigationDispatchers
import luis.mvi.uzumbank.utils.navigator.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindAppNavigator(impl: NavigationDispatchers): AppNavigator

    @Binds
    fun bindNavigationHandler(impl: NavigationDispatchers): NavigationHandler
}