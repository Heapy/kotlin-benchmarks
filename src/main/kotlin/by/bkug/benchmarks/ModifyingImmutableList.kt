package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
/*
# Run complete. Total time: 00:03:36

Benchmark                            (size)   Mode  Cnt         Score         Error  Units
ModifyingImmutableList.iterative          1  thrpt   20  26836593.343 ± 1373998.400  ops/s
ModifyingImmutableList.iterative        100  thrpt   20    932084.815 ±   31955.235  ops/s
ModifyingImmutableList.iterative      10000  thrpt   20     11633.838 ±     836.539  ops/s
ModifyingImmutableList.iterative    1000000  thrpt   20       111.311 ±       2.966  ops/s
ModifyingImmutableList.toArrayList      100  thrpt   20  10764575.668 ±  245075.601  ops/s
ModifyingImmutableList.toArrayList    10000  thrpt   20    127696.434 ±    1421.778  ops/s
ModifyingImmutableList.toArrayList  1000000  thrpt   20       529.198 ±      43.391  ops/s
ModifyingImmutableList.toMutable        100  thrpt   20   5568213.233 ±  121134.416  ops/s
ModifyingImmutableList.toMutable      10000  thrpt   20     63647.776 ±     636.452  ops/s
ModifyingImmutableList.toMutable    1000000  thrpt   20       266.410 ±      22.832  ops/s
 */
@State(Scope.Thread)
open class ModifyingImmutableList {

    @Param("1", "100", "10000", "1000000")
    var size: Int = 0

    lateinit var players: List<Player>

    @Setup
    fun setup() {
        players = generatePlayers(size)
    }

    @Benchmark fun iterative(): List<Player> {
        return players.mapIndexed { i, player ->
            if (i == 2) player.copy(score = 100)
            else player
        }
    }

    @Benchmark fun toMutable(): List<Player> {
        val updatedPlayer = players[2].copy(score = 100)
        val mutable = players.toMutableList()
        mutable.set(2, updatedPlayer)
        return mutable.toList()
    }

    @Benchmark fun toArrayList(): List<Player> {
        val updatedPlayer = players[2].copy(score = 100)
        return players.set(2, updatedPlayer)
    }
}
