package com.ocjadan.exhibitandroid.users

import java.io.Serializable

data class Owner(val accountId: Int, val userId: Int, val profileImage: String, val name: String) : Serializable