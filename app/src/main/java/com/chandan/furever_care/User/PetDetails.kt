package com.chandan.furever_care.User

import android.net.Uri

data class PetDetails(
    val uri: String,
    val petName : String,
    val petType : String,
    val petAge : String,
    val petGender : String,
    val probDesc : String
)
