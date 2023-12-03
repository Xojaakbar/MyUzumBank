package luis.mvi.uzumbank.data.network.response

import com.google.gson.annotations.SerializedName

data class Trace(
    @SerializedName("class")
    val klass: String,
    val file: String,
    val function: String,
    val line: Int,
    val type: String
)