package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import java.util.*

/**
 * By @Aksigera
 *
 * # Run complete. Total time: 00:27:10
 *
 * Benchmark                                Mode  Cnt      Score     Error  Units
 * BranchPredictionArrays.shuffledBoolean  thrpt  200  34788.069 ±  77.522  ops/s
 * BranchPredictionArrays.shuffledIf       thrpt  200  33769.478 ± 215.462  ops/s
 * BranchPredictionArrays.sortedBoolean    thrpt  200  34382.297 ± 169.428  ops/s
 * BranchPredictionArrays.sortedIf         thrpt  200  34524.087 ± 101.174  ops/s
 *
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
            if (i > 0.5)
                hole = true
            else
                hole = false
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
            if (i > 0.5)
                hole = true
            else
                hole = false
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
