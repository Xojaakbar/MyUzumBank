package luis.mvi.uzumbank.domain.repository.transfer

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.models.TransferResponseParam
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.models.TransferVerifyResponseParam
import luis.mvi.uzumbank.utils.BaseResult

/*
created by Xo'jaakbar on 30.10.2023 at 4:20
*/

interface TransferRepository {

    fun transferCard(request: TransferRequestParam): Flow<BaseResult<TransferResponseParam>>

    fun transferVerify(request: TransferVerifyRequestParam): Flow<BaseResult<TransferVerifyResponseParam>>
}