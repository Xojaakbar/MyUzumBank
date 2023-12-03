package luis.mvi.uzumbank.domain.usecase.transfer

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.models.TransferResponseParam
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.models.TransferVerifyResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 11.11.2023 at 4:04
*/

interface TransferUseCase {
    fun transfer(requestParam: TransferRequestParam): Flow<BaseResult<TransferResponseParam>>
    fun verify(transferVerifyRequestParam: TransferVerifyRequestParam): Flow<BaseResult<TransferVerifyResponseParam>>
}