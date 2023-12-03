package luis.mvi.uzumbank.data.repository.transfer

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import luis.mvi.uzumbank.data.mappers.toTransferRequest
import luis.mvi.uzumbank.data.mappers.toTransferResponseParam
import luis.mvi.uzumbank.data.mappers.toTransferVerifyResponseParam
import luis.mvi.uzumbank.data.network.api.TransferApi
import luis.mvi.uzumbank.data.network.response.ErrorResponse
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.models.TransferResponseParam
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.models.TransferVerifyResponseParam
import luis.mvi.uzumbank.domain.repository.transfer.TransferRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 30.10.2023 at 4:20
*/

class TransferRepositoryImpl @Inject constructor(
    private val api: TransferApi,
    private val gson: Gson
):TransferRepository {

    override fun transferCard(request: TransferRequestParam): Flow<BaseResult<TransferResponseParam>> = flow<BaseResult<TransferResponseParam>> {

        val response = api.transfer(request.toTransferRequest())

        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.toTransferResponseParam()))
        }else{
            response.errorBody().let {
                val error = gson.fromJson(it!!.string(), ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch {
        Log.d("auth throwable", "${it.message}")
        emit(BaseResult.NotSentRequest())
    }.flowOn(Dispatchers.IO)

    override fun transferVerify(request: TransferVerifyRequestParam): Flow<BaseResult<TransferVerifyResponseParam>> = flow<BaseResult<TransferVerifyResponseParam>> {
        Log.d("verify", "request: \n$request")
        val response = api.transferVerify(request.toTransferRequest())
        Log.d("verify", "response: \n$response")
        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.toTransferVerifyResponseParam()))
        } else{
            val error = gson.fromJson(response.errorBody()?.string(),ErrorResponse::class.java)
            emit(BaseResult.Error(Exception(error.message)))
        }
    }/*.catch {
        emit(BaseResult.NotSentRequest())
    }*/.flowOn(Dispatchers.IO)

}