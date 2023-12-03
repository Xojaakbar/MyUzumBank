package luis.mvi.uzumbank.presentation.screens.main.home.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.domain.usecase.profile.ProfileUseCase
import luis.mvi.uzumbank.utils.navigator.AppNavigator
import javax.inject.Inject

/*
created by Xo'jaakbar on 26.10.2023 at 0:51
*/
@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val useCase: ProfileUseCase,
    private val navigator: AppNavigator
):ViewModel(),ProfileViewModel {
    override val profileInfoLiveData = MutableLiveData<ProfileResponseParam>()
    override val toastLiveData = MutableLiveData<String>()
    override val rvLiveData = MutableLiveData<List<MenuCategoryData>>()

    init {
        rvLiveData.value = useCase.recyclerViewInfo()
    }

    override fun getProfileInfo() {
        useCase.getProfileInfo().onEach { result ->
            result.onSuccess { profileInfo->
                Log.d("profileInfoObserver", "$profileInfo")

                profileInfoLiveData.value = profileInfo }
            result.onFailure {
                Log.d("onfailure", "${it.message}")
                toastLiveData.value = it.message }
            result.onMessage {
                Log.d("onmessage", "$it")
                toastLiveData.value = it }
            result.onNotSend { toastLiveData.value = "Запрос не отправлён" }
        }.launchIn(viewModelScope)
    }

    override fun backToHome() {
        viewModelScope.launch {
            navigator.back()
        }
    }
}