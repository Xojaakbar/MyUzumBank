package luis.mvi.uzumbank.presentation.activities.mainactivity

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import luis.mvi.uzumbank.utils.ConnectivityObserver
import luis.mvi.uzumbank.utils.NetworkConnectivityObserver
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import java.sql.Time
import java.time.LocalDateTime
import java.util.Date
import javax.inject.Inject

/*
created by Xo'jaakbar on 25.09.2023 at 14:29
*/
@HiltViewModel
class MainViewModelImpl @Inject constructor(
    @ApplicationContext context:Context,
    private val appNavigator: AppNavigator
): ViewModel(), MainViewModel {

    override val connectionLiveData = MutableLiveData<Boolean>()
    override val passcodeLiveData = MutableLiveData<Unit>()

    val connectivityObserver:ConnectivityObserver = NetworkConnectivityObserver(context)

    init {
        checkConnection()
    }

    override fun checkConnection() {
        connectivityObserver.observe().onEach {
            when (it) {
                ConnectivityObserver.Status.Available -> {
                    connectionLiveData.value = true
                }

                ConnectivityObserver.Status.UnAvailable -> {
                    connectionLiveData.value = false
                }

                ConnectivityObserver.Status.Losing -> {
                    connectionLiveData.value = false
                }

                ConnectivityObserver.Status.Lost -> {
                    connectionLiveData.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun passcodeActivation(resumeTime: LocalDateTime, pausedTime:LocalDateTime) {
        val resumeHour = resumeTime.hour.toLong()
        val resumeMinute = resumeTime.minute.toLong()
        val resumeSecond = resumeTime.second.toLong()
        val pauseHour = pausedTime.hour.toLong()
        val pauseMinute = pausedTime.minute.toLong()
        val pauseSecond = pausedTime.second.toLong()
        Log.d("difference of time", "resumeMinute: $resumeMinute,\npausedMinute: $pauseMinute")
        Log.d("difference of time", "resumeSecond: $resumeSecond,\npauseSecond: $pauseSecond")
        when{
            resumeHour>pauseHour ->  { passcodeLiveData.value = Unit }
            resumeMinute-pauseMinute>=2L -> {passcodeLiveData.value = Unit}
            resumeMinute-pauseMinute>=1L -> {
                if (pauseSecond<60- limitTime) passcodeLiveData.value = Unit
                else { if (limitTime<60-pauseSecond+resumeSecond) {passcodeLiveData.value = Unit}
                     else return }}
            resumeSecond-pauseSecond>= limitTime.toLong() -> { passcodeLiveData.value = Unit }
        }
    }
}
const val limitTime = 15

