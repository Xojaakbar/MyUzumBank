package luis.mvi.uzumbank.presentation.screens.main.home.profile

import androidx.lifecycle.LiveData
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.domain.models.ProfileResponseParam

/*
created by Xo'jaakbar on 26.10.2023 at 0:51
*/

interface ProfileViewModel {
    val profileInfoLiveData:LiveData<ProfileResponseParam>
    val toastLiveData: LiveData<String>
    val rvLiveData:LiveData<List<MenuCategoryData>>

    fun getProfileInfo()
    fun backToHome()
}