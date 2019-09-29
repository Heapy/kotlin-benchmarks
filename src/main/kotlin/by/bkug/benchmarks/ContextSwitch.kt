package by.bkug.benchmarks

import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.annotations.TearDown
import java.lang.Thread.sleep
import java.util.concurrent.Executors
import kotlin.concurrent.thread

@State(Scope.Thread)
open class ContextSwitch {
//    @Param("1", "2", "4", "8", "16", "32", "64", "128", "256")
    var time: Long = 1

    var ste: ExecutorCoroutineDispatcher? = null

    @Setup
    open fun setup() {
        println("Setup")
        ste = Executors.newSingleThreadExecutor().asCoroutineDispatcher()
    }

    @TearDown
    open fun tearDown() {
        ste?.close()
    }

    @Benchmark
    open fun coroutines() {
        val dispatcher = ste!!
        val jobs = List(10000) {
            GlobalScope.launch(dispatcher) {
                repeat(1000) {
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
                repeat(1000) {
                    sleep(time)
                }
            }
        }

        threads.forEach { it.join() }
    }
}
