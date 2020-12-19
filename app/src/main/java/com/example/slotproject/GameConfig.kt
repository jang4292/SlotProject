package com.example.slotproject

class GameConfig {
    companion object {
        var isAuto = true

        var gameStatus = GameStatus.READY

//        const val READY = "READY";


    }
}

enum class GameStatus {
    READY, SPINNING
}