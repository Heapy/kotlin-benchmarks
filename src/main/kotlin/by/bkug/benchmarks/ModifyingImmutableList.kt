package by.bkug.benchmarks

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State

@State(Scope.Thread)
open class ModifyingImmutableList {

    @Param("10", "100", "10000", "1000000")
    var size: Int = 0

    lateinit var players: List<Player>
    lateinit var mutable: MutableList<Player>

    @Setup
    fun setup() {
        players = generatePlayers(size)
        mutable = players.toMutableList()
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

    @Benchmark fun baseline(): List<Player> {
        val updatedPlayer = players[2].copy(score = 100)
        mutable[2] = updatedPlayer;
        return mutable
    }
}
