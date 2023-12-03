package luis.mvi.uzumbank.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 26.10.2023 at 1:55
*/

interface ProfileUseCase {

    fun getProfileInfo(): Flow<BaseResult<ProfileResponseParam>>

    fun recyclerViewInfo():List<MenuCategoryData>

}