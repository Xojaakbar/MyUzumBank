package luis.mvi.uzumbank.domain.usecase.profile

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.R
import luis.mvi.uzumbank.data.local.MenuCategoryData
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.domain.repository.profile.ProfileRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 26.10.2023 at 1:55
*/

class ProfileUseCaseImpl @Inject constructor(
    private val repository:ProfileRepository
) : ProfileUseCase {
    private val recyclerViewItemList = listOf(
        MenuCategoryData(R.drawable.settings_icon,"Настройки приложения"),
        MenuCategoryData(R.drawable.baseline_notifications_none_24,"Push-уведомления"),
        MenuCategoryData(R.drawable.baseline_question_mark_24,"Центр справки и поддержки"),
        MenuCategoryData(R.drawable.baseline_wifi_calling_3_24,"Связь с банком"),
        MenuCategoryData(R.drawable.list_paper_icon2,"Квитанция"),
        MenuCategoryData(R.drawable.exit_icon,"Выйти")
    )

    override fun getProfileInfo(): Flow<BaseResult<ProfileResponseParam>> {
        return repository.getProfileInfo()
    }

    override fun recyclerViewInfo(): List<MenuCategoryData> = recyclerViewItemList


}