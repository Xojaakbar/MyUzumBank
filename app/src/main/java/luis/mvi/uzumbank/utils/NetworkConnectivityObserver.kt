package luis.mvi.uzumbank.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/*
created by Xo'jaakbar on 23.09.2023 at 17:27
*/

class NetworkConnectivityObserver @Inject constructor (@ApplicationContext private val context: Context) :
    ConnectivityObserver() {
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun observe(): Flow<ConnectivityObserver.Status> {
        return callbackFlow {
            val callback = object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    launch { send(ConnectivityObserver.Status.Available) }
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    launch { send(ConnectivityObserver.Status.Losing) }
                    super.onLosing(network, maxMsToLive)
                }

                override fun onLost(network: Network) {
                    launch { send(ConnectivityObserver.Status.Lost) }
                    super.onLost(network)
                }

                override fun onUnavailable() {
                    launch { send(ConnectivityObserver.Status.UnAvailable) }
                    super.onUnavailable()
                }
            }

            connectivityManager.registerDefaultNetworkCallback(callback)
            awaitClose {
                connectivityManager.unregisterNetworkCallback(callback)
            }
        }.distinctUntilChanged()
    }
}