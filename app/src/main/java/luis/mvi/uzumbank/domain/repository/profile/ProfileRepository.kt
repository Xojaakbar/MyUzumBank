package luis.mvi.uzumbank.domain.repository.profile

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 26.10.2023 at 1:50
*/

interface ProfileRepository {
    fun getProfileInfo(): Flow<BaseResult<ProfileResponseParam>>
    fun updateProfileName()
    fun updatePassword()
    fun updatePhone()
    fun updatePhoneVerify()
}