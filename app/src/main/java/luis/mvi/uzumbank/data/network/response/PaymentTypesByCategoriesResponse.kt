package luis.mvi.uzumbank.data.network.response

data class PaymentTypesByCategoriesResponse(
    val current_page: Int,
    val `data`: List<DataX>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val links: List<LinkXX>,
    val next_page_url: Any,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)