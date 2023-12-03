package luis.mvi.uzumbank.data.mappers

import luis.mvi.uzumbank.data.network.response.ProfileResponse
import luis.mvi.uzumbank.domain.models.ProfileResponseParam

/*
created by Xo'jaakbar on 26.10.2023 at 2:20
*/

fun ProfileResponse.maptoProfileResponseParam():ProfileResponseParam =
    ProfileResponseParam(
        this.created_at, this.email, this.first_name, this.id, this.last_name, this.name, this.phone_number, this.phone_number_verified, this.updated_at
    )
