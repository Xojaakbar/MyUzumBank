package luis.mvi.uzumbank.data.repository.cards

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import luis.mvi.uzumbank.data.mappers.toParam
import luis.mvi.uzumbank.data.network.api.CardsApi
import luis.mvi.uzumbank.data.network.response.ErrorResponse
import luis.mvi.uzumbank.data.network.response.GetAllCardsResponse
import luis.mvi.uzumbank.domain.models.CardListResponseParam
import luis.mvi.uzumbank.domain.repository.cards.CardsRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 01.11.2023 at 18:18
*/

class CardsRepositoryImpl @Inject constructor(
    private val api: CardsApi,
    private val gson: Gson
):CardsRepository {

    override fun getCardsList(): Flow<BaseResult<CardListResponseParam>> = flow<BaseResult<CardListResponseParam>> {
        val response = api.getCardsList()
        Log.d("onviewModel", "getCardsList on repository: ${response.body()}")
        if (response.isSuccessful){
            emit(BaseResult.Success(response.body()!!.toParam()))
        } else{
            response.body()?.let {
                val error = gson.fromJson(it.toString(),ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }.catch {
        emit(BaseResult.NotSentRequest())
    }.flowOn(Dispatchers.IO)

    override fun getAllCards(): Flow<BaseResult<GetAllCardsResponse>> = flow<BaseResult<GetAllCardsResponse>> {
        val response = api.getAllCards()
        if (response.isSuccessful) {
            emit(BaseResult.Success(response.body()!!))
        }else{
            response.body()?.let {
                val error = gson.fromJson(it.toString(),ErrorResponse::class.java)
                emit(BaseResult.Error(Exception(error.message)))
            }
        }
    }/*.catch { emit(BaseResult.NotSentRequest()) }*/.flowOn(Dispatchers.IO)


}