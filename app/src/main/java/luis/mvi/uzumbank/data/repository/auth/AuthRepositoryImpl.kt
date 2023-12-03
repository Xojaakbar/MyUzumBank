package luis.mvi.uzumbank.data.repository.auth

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import luis.mvi.uzumbank.data.mappers.mapToParam
import luis.mvi.uzumbank.data.mappers.mapToResponse
import luis.mvi.uzumbank.data.mappers.mapToSignInRequest
import luis.mvi.uzumbank.data.mappers.mapToSignInResponseParam
import luis.mvi.uzumbank.data.mappers.mapToSignUpRequest
import luis.mvi.uzumbank.data.mappers.mapToSignUpRequestParam
import luis.mvi.uzumbank.data.network.api.AuthApi
import luis.mvi.uzumbank.data.network.response.ErrorResponse
import luis.mvi.uzumbank.domain.models.SignInRequestParam
import luis.mvi.uzumbank.domain.models.SignInResponseParam
import luis.mvi.uzumbank.domain.models.SignUpRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendRequestParam
import luis.mvi.uzumbank.domain.models.SignUpResendResponseParam
import luis.mvi.uzumbank.domain.models.SignUpResponseParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyRequestParam
import luis.mvi.uzumbank.domain.models.SignUpVerifyResponseParam
import luis.mvi.uzumbank.domain.repository.auth.AuthRepository
import luis.mvi.uzumbank.utils.BaseResult
import java.lang.Exception
import javax.inject.Inject

/*
created by Xo'jaakbar on 08.10.2023 at 2:54
*/

class AuthRepositoryImpl @Inject constructor(
    private val api:AuthApi,
    private val gson: Gson
): AuthRepository {
    override fun signUp(signUpRequestParam: SignUpRequestParam): Flow<BaseResult<SignUpResponseParam>> = flow<BaseResult<SignUpResponseParam>> {
    val response = api.signUp(signUpRequestParam.mapToSignUpRequest())
        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.mapToSignUpRequestParam()))
        }else{
            response.errorBody().let {
                val error = gson.fromJson(it!!.string(),ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch {
        Log.d("auth throwable", "${it.message}")
        emit(BaseResult.NotSentRequest())
    }.flowOn(Dispatchers.IO)

    override fun signIn(signInRequestParam: SignInRequestParam): Flow<BaseResult<SignInResponseParam>> = flow<BaseResult<SignInResponseParam>> {
        val response = api.signIn(signInRequestParam.mapToSignInRequest())
        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.mapToSignInResponseParam()))
        } else{
            response.errorBody().let {
                val error = gson.fromJson(it!!.string(),ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch {
        Log.d("auth throwable", "$it")
        emit(BaseResult.NotSentRequest())
    }.flowOn(Dispatchers.IO)


    override fun signUpVerify(signUpVerifyRequestParam: SignUpVerifyRequestParam): Flow<BaseResult<SignUpVerifyResponseParam>> = flow<BaseResult<SignUpVerifyResponseParam>> {
        val response = api.signUpVerify(signUpVerifyRequestParam.mapToResponse())

        if(response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.mapToParam()))
        }else{
            response.errorBody()?.let {
                val error = gson.fromJson(it.string(),ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch {
        emit(BaseResult.NotSentRequest())}
        .flowOn(Dispatchers.IO)

    override fun resend(signUpResendRequestParam: SignUpResendRequestParam): Flow<BaseResult<SignUpResendResponseParam>> = flow<BaseResult<SignUpResendResponseParam>> {
        val response = api.signUpResend(signUpResendRequestParam.mapToResponse())
        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.mapToParam()))
        }else{
            response.errorBody()?.let {
                val error = gson.fromJson(it.string(), ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch { emit(BaseResult.NotSentRequest()) }
        .flowOn(Dispatchers.IO)
}