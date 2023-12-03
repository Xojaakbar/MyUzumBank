package luis.mvi.uzumbank.utils

import kotlinx.coroutines.flow.Flow

/*
created by Xo'jaakbar on 23.09.2023 at 17:25
*/

abstract class ConnectivityObserver {
    abstract fun observe(): Flow<Status>

    enum class Status{
        Available, UnAvailable, Losing, Lost
    }
}