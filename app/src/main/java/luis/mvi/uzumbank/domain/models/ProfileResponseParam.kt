package luis.mvi.uzumbank.domain.models

/*
created by Xo'jaakbar on 26.10.2023 at 1:47
*/

data class ProfileResponseParam(
    val created_at: String?=null,
    val email: Any?=null,
    val first_name: String?=null,
    val id: Int?=null,
    val last_name: String?=null,
    val name: Any?=null,
    val phone_number: String?=null,
    val phone_number_verified: Boolean?=null,
    val updated_at: String?=null
)
