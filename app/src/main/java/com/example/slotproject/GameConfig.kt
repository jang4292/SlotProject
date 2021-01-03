package com.example.slotproject

class GameConfig {
    companion object {
        const val KEY_DEFAULT_BALANCE = "KEY_DEFAULT_BALANCE"
        const val KEY_BALANCE = "KEY_BALANCE"

        const val DEFAULT_BALANCE = 100000
        const val COLUMN = 5
        const val ROW = 3

        const val DEFAULT_SYMBOL_COUNT = 15
        const val ADD_SYMBOL_COUNT = 10
        const val DURATION_SPIN_TIME = 2000L

        val TOTAL_BET_LIST = listOf(1000, 5000, 10000, 30000, 50000, 100000)

        var balance = 0
        var totalBet = TOTAL_BET_LIST[0]

        var SYMBOL_WIDTH: Int = -1
        var SYMBOL_HEIGHT: Int = -1

        var isAuto = true
        var gameStatus = GameStatus.READY

        val resSymbols = listOf(
            R.drawable.symbol_10,
            R.drawable.symbol_a,
            R.drawable.symbol_cap,
            R.drawable.symbol_coin,
            R.drawable.symbol_fish,
            R.drawable.symbol_frog,
            R.drawable.symbol_j,
            R.drawable.symbol_k,
            R.drawable.symbol_q,
            R.drawable.symbol_scatter,
            R.drawable.symbol_turtle
        )
    }
}

enum class GameStatus {
    READY,
    SPINNING;
}