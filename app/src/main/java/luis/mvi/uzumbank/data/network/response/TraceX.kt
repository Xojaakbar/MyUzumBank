package luis.mvi.uzumbank.data.network.response

data class TraceX(
    val `class`: String,
    val `file`: String,
    val function: String,
    val line: Int,
    val type: String
)