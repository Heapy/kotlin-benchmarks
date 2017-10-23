package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

data class SampleToString(val a: String, val b: String)

/*
# Run complete. Total time: 00:13:27

Benchmark                       Mode  Cnt         Score        Error  Units
StringTemplateKotlin.plain     thrpt  200  44897001.422 ± 579116.212  ops/s
StringTemplateKotlin.template  thrpt  200  44863228.471 ± 600940.776  ops/s
 */
@State(Scope.Thread)
open class StringTemplateKotlin {
    lateinit var obj: SampleToString

    @Setup
    fun setup() {
        obj = SampleToString("Hello", "World")
    }

    @Benchmark
    fun template(): String {
        return "$obj"
    }

    @Benchmark
    fun plain(): String {
        return obj.toString()
    }
}
