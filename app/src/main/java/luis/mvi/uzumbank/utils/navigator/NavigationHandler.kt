package luis.mvi.uzumbank.utils.navigator

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.utils.navigator.NavigationArgs

interface NavigationHandler {
    val navigationBuffer:Flow<NavigationArgs>
}