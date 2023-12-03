package luis.mvi.uzumbank.data.network.response

data class MetaX(
    val current_page: Int,
    val from: Any,
    val last_page: Int,
    val links: List<LinkXXX>,
    val path: String,
    val per_page: Int,
    val to: Any,
    val total: Int
)