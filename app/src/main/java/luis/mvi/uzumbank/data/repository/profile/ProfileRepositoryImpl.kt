package luis.mvi.uzumbank.data.repository.profile

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import luis.mvi.uzumbank.data.mappers.maptoProfileResponseParam
import luis.mvi.uzumbank.data.network.api.ProfileApi
import luis.mvi.uzumbank.data.network.response.ErrorResponse
import luis.mvi.uzumbank.domain.models.ProfileResponseParam
import luis.mvi.uzumbank.domain.repository.profile.ProfileRepository
import luis.mvi.uzumbank.utils.BaseResult
import java.lang.Exception
import javax.inject.Inject

/*
created by Xo'jaakbar on 26.10.2023 at 2:16
*/

class ProfileRepositoryImpl @Inject constructor(
    private val api: ProfileApi,
    private val gson: Gson,
) : ProfileRepository {
    override fun getProfileInfo(): Flow<BaseResult<ProfileResponseParam>> =
        flow<BaseResult<ProfileResponseParam>> {
            val response = api.getProfileInfo()
            Log.d("exception in profile" ,  response.body().toString()+"<----------------------")

            if (response.isSuccessful) {
                emit(BaseResult.Success(response.body()!!.maptoProfileResponseParam()))
            } else {
                response.errorBody()?.let {
                    val error = gson.fromJson(it.string(), ErrorResponse::class.java)
                    emit(BaseResult.Error(Exception(error.message)))
                }
            }
        }/*.catch {
            emit(BaseResult.NotSentRequest())
            Log.d("exception in profile" ,  it.message.toString())
        }*/.flowOn(Dispatchers.IO)

    override fun updateProfileName() {

    }

    override fun updatePassword() {

    }

    override fun updatePhone() {

    }

    override fun updatePhoneVerify() {

    }
}