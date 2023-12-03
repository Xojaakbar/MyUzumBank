package luis.mvi.uzumbank.data.network.response

import luis.mvi.uzumbank.data.network.response.Trace

data class CardsErrorResponse(
    val exception: String,
    val file: String,
    val line: Int,
    val message: String,
    val trace: List<Trace>
)