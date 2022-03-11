package com.ocjadan.exhibitandroid.common

class UrlProvider {
    companion object {
        private const val stackExchange_url = "https://api.stackexchange.com/2.3/"
    }

    fun stackExchange(): String {
        return stackExchange_url
    }
}