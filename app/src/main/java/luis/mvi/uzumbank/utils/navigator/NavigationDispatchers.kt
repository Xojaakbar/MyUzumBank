package luis.mvi.uzumbank.utils.navigator

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class NavigationDispatchers @Inject constructor() : AppNavigator, NavigationHandler {
    override suspend fun navigateTo(screen: AppScreen)  = navigate{
        navigate(screen)
    }

    override suspend fun back() = navigate {
        navigateUp()
    }

    override val navigationBuffer = MutableSharedFlow<NavigationArgs>()
    private suspend fun navigate(args: NavigationArgs) {
        navigationBuffer.emit(args)
    }
}