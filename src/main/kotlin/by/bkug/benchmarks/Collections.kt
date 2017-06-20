package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.stream.Collectors

@State(Scope.Thread)
open class Collections {

    @Param("1", "10", "100", "10000", "1000000")
    var size: Int = 0

    lateinit var list: List<Int>

    @Setup
    fun setup() {
        list = generateList(size)
    }

    @Benchmark
    fun simpleKotlin(): List<Int> {
        val mutable = mutableListOf<Int>()
        for (item in list) {
            mutable.add(item)
        }
        return mutable
    }

    @Benchmark
    fun simpleKotlinFilter(): List<Int> {
        val mutable = mutableListOf<Int>()
        for (item in list) {
            if (item % 2 == 0) {
                mutable.add(item)
            }
        }
        return mutable
    }


    @Benchmark
    fun kotlin(): List<Int> {
        return list
            .map { it }
    }

    @Benchmark
    fun kotlinFilter(): List<Int> {
        return list
            .filter { it % 2 == 0 }
            .map { it }
    }

    @Benchmark
    fun kotlinSequence(): List<Int> {
        return list
            .asSequence()
            .map { it }.toList()
    }

    @Benchmark
    fun kotlinSequenceFilter(): List<Int> {
        return list
            .asSequence()
            .filter { it % 2 == 0 }
            .map { it }.toList()
    }

    @Benchmark
    fun javaStream(): List<Int> {
        return list.stream()
            .map { it }
            .collect(Collectors.toList())
    }

    @Benchmark
    fun javaStreamFilter(): List<Int> {
        return list.stream()
            .filter { it % 2 == 0 }
            .map { it }
            .collect(Collectors.toList())
    }
}


fun generateList(size: Int): List<Int> = (1..size).toList()
