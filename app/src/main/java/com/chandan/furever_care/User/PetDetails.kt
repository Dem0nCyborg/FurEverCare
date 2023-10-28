package com.chandan.furever_care.User

import android.net.Uri
import javax.net.ssl.SSLEngineResult.Status

data class PetDetails(
    val key : String,
    val uri: String,
    val petName : String,
    val petType : String,
    val petAge : String,
    val petGender : String,
    val probDesc : String,
    val status: String
)
