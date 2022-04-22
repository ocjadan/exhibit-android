package com.ocjadan.exhibitandroid.networking

class UrlProvider {
    companion object {
        private const val STACK_EXCHANGE_URL = "https://api.stackexchange.com/2.3/"
    }

    fun stackExchange(): String {
        return STACK_EXCHANGE_URL
    }
}