package luis.mvi.uzumbank.data.network.response

data class CreateCardResponse(
    val exception: String,
    val `file`: String,
    val line: Int,
    val message: String,
    val trace: List<TraceX>
)