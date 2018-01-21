package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.*

/**
 * By @Aksigera
 *
 * Benchmark                                Mode  Cnt      Score       Error  Units
 * BranchPredictionArrays.shuffledBoolean  thrpt    4  32137.307 ± 27899.708  ops/s
 * BranchPredictionArrays.shuffledIf       thrpt    4  30471.293 ± 27052.683  ops/s
 * BranchPredictionArrays.sortedBoolean    thrpt    4  31965.579 ± 24775.150  ops/s
 * BranchPredictionArrays.sortedIf         thrpt    4  31135.133 ± 26429.907  ops/s
 */
@State(Scope.Thread)
open class BranchPredictionArrays {
    lateinit var sorted: IntArray
    lateinit var shuffled: IntArray

    @Setup
    fun setup() {
        val random = Random()
        val list = (1..1_000_000).map { random.nextInt() }
        sorted = list.sorted().toIntArray()
        shuffled = list.toIntArray()
    }

    @Benchmark
    fun sortedIf(): Boolean {
        var hole = false
        for (i in sorted) {
            hole = if (i > 0.5) true else false
        }
        return hole
    }

    @Benchmark
    fun sortedBoolean(): Boolean {
        var hole = false
        for (i in sorted) {
            hole = i > 0.5
        }
        return hole
    }

    @Benchmark
    fun shuffledIf(): Boolean {
        var hole = false
        for (i in shuffled) {
            hole = if (i > 0.5) true else false
        }
        return hole
    }

    @Benchmark
    fun shuffledBoolean(): Boolean {
        var hole = false
        for (i in shuffled) {
            hole = i > 0.5
        }
        return hole
    }
}
