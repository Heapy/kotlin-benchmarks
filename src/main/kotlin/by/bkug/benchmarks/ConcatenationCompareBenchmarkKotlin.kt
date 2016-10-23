package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark

open class ConcatenationCompareBenchmarkKotlin {

    @Benchmark
    fun stringValueOf(): Any {
        return 1.toString()
    }

    @Benchmark
    fun stringBuffer(): Any {
        return StringBuffer().append(1).toString()
    }

    @Benchmark
    fun stringPlus(): Any {
        return "" + 1
    }
}

