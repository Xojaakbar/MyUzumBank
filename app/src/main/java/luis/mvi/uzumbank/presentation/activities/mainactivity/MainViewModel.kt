package luis.mvi.uzumbank.presentation.activities.mainactivity

import androidx.lifecycle.LiveData
import java.sql.Time
import java.time.LocalDateTime
import java.util.Date

/*
created by Xo'jaakbar on 25.09.2023 at 14:29
*/

interface MainViewModel {

    val connectionLiveData:LiveData<Boolean>

    val passcodeLiveData:LiveData<Unit>

    fun checkConnection()

    fun passcodeActivation(resumeTime:LocalDateTime,pausedTime: LocalDateTime)

}