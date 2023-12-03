package luis.mvi.uzumbank.data.network.response

data class MetaXX(
    val current_page: Int,
    val from: Int,
    val last_page: Int,
    val links: List<LinkXXXX>,
    val path: String,
    val per_page: Int,
    val to: Int,
    val total: Int
)