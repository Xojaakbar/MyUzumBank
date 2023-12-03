package luis.mvi.uzumbank.domain.usecase.transfer

import kotlinx.coroutines.flow.Flow
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.models.TransferResponseParam
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.models.TransferVerifyResponseParam
import luis.mvi.uzumbank.domain.repository.transfer.TransferRepository
import luis.mvi.uzumbank.utils.BaseResult
import javax.inject.Inject

/*
created by Xo'jaakbar on 11.11.2023 at 4:04
*/

class TransferUseCaseImpl @Inject constructor(
    private val repository: TransferRepository
):TransferUseCase {
    override fun transfer(requestParam: TransferRequestParam): Flow<BaseResult<TransferResponseParam>> {
        return repository.transferCard(requestParam)
    }

    override fun verify(transferVerifyRequestParam: TransferVerifyRequestParam): Flow<BaseResult<TransferVerifyResponseParam>> {
        return repository.transferVerify(transferVerifyRequestParam)
    }
}