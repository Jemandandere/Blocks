package com.jemandandere.app

import com.jemandandere.app.screens.GameScreen
import ktx.app.KtxGame
import ktx.app.KtxScreen

class MyGame : KtxGame<KtxScreen>() {
    override fun create() {
        addScreen(GameScreen())
        setScreen<GameScreen>()
    }
}