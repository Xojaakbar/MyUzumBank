package luis.mvi.uzumbank.utils

import java.lang.Exception

/*
created by Xo'jaakbar on 08.10.2023 at 2:44
*/

sealed class BaseResult<T>{
    data class Success<T>(val data: T): BaseResult<T>()
    data class Error<T>(val error: Exception): BaseResult<T>()
    data class Message<T>(val message: String): BaseResult<T>()
    class NotSentRequest<T>:BaseResult<T>()
    fun onSuccess(block:(T)->Unit):BaseResult<T>{
        if (this is Success) block(this.data)
        return this
    }
    fun onFailure(block: (Throwable) -> Unit): BaseResult<T>{
        if (this is Error) block(this.error)
        return this
    }

    fun onMessage(block:(String)->Unit):BaseResult<T>{
        if (this is Message) block(this.message)
        return this
    }

    fun onNotSend(block: () -> Unit):BaseResult<T>{
        if (this is NotSentRequest) block()
        return this
    }

}
