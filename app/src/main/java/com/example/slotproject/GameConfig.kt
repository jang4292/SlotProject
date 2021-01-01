package com.example.slotproject

class GameConfig {
    companion object {
        const val COLUMN = 5
        const val ROW = 3

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