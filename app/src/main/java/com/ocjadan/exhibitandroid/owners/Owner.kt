package com.ocjadan.exhibitandroid.owners

import java.io.Serializable

data class Owner(val accountId: Long? = null, val userId: Long, val profileImage: String, val name: String) : Serializable