package luis.mvi.uzumbank.utils.navigator

interface AppNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
}