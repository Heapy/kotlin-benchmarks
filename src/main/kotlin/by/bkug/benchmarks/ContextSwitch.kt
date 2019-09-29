package by.bkug.benchmarks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import java.lang.Thread.sleep
import kotlin.concurrent.thread

@State(Scope.Thread)
open class ContextSwitch {
    @Param("1", "2", "4", "8", "16", "32", "64", "128", "256")
    var time: Long = 0

    @Benchmark
    open fun coroutines() {
        val jobs = List(10000) {
            GlobalScope.launch(Dispatchers.IO) {
                repeat(10) {
                    delay(time)
                }
            }
        }

        runBlocking {
            jobs.joinAll()
        }
    }

    @Benchmark
    open fun threads() {
        val threads = List(10000) {
            thread(start = true) {
                repeat(10) {
                    sleep(time)
                }
            }
        }

        threads.forEach { it.join() }
    }
}
