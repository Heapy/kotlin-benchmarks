package by.bkug.benchmarks

import java.util.ArrayList

data class Player(val name: String = "Kotlin", val score: Int = 0)

fun <E> List<E>.set(index: Int, element: E): List<E> {
    val list = ArrayList<E>(this)
    list[index] = element
    return list
}

fun generatePlayers(size: Int) = (1..size).map { Player(score = size) }
