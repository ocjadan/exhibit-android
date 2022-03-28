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
        val result: MutableList<Int> = mutableListOf()
        for (index in 1..amount) {
            result.add(getRandomInt())
        }
        return result
    }

    fun getRandomLongs(amount: Int): List<Long> {
        val result: MutableList<Long> = mutableListOf()
        for (index in 1..amount) {
            result.add(getRandomLong())
        }
        return result
    }
}