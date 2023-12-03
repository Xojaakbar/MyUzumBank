package luis.mvi.uzumbank.data.network.response

data class HistoryResponse(
    val `data`: List<Any>,
    val links: LinksX,
    val meta: MetaX
)