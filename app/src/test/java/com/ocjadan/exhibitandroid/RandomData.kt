package com.ocjadan.exhibitandroid

import kotlin.random.Random

object RandomData {
    fun getRandomInt(): Int {
        return Random.nextInt()
    }

    fun getRandomLong(): Long {
        return Random.nextLong()
    }

    fun getRandomInts(amount: Int): List<Int> {
        return IntArray(amount) { getRandomInt() }.toList()
    }

    fun getRandomLongs(amount: Int): List<Long> {
        return LongArray(amount) { getRandomLong() }.toList()
    }
}