package luis.mvi.uzumbank.data.mappers

import luis.mvi.uzumbank.data.network.request.TransferRequest
import luis.mvi.uzumbank.data.network.request.TransferVerifyRequest
import luis.mvi.uzumbank.data.network.response.TransferResponse
import luis.mvi.uzumbank.data.network.response.TransferVerifyResponse
import luis.mvi.uzumbank.domain.models.TransferRequestParam
import luis.mvi.uzumbank.domain.models.TransferResponseParam
import luis.mvi.uzumbank.domain.models.TransferVerifyRequestParam
import luis.mvi.uzumbank.domain.models.TransferVerifyResponseParam

/*
created by Xo'jaakbar on 30.10.2023 at 16:19
*/

fun TransferRequestParam.toTransferRequest(): TransferRequest =
    TransferRequest(amount, from_card_id, card_number)

fun TransferResponse.toTransferResponseParam(): TransferResponseParam =
    TransferResponseParam(token, code)

fun TransferVerifyRequestParam.toTransferRequest(): TransferVerifyRequest =
    TransferVerifyRequest(code, token)

fun TransferVerifyResponse.toTransferVerifyResponseParam(): TransferVerifyResponseParam =
    TransferVerifyResponseParam(message)
