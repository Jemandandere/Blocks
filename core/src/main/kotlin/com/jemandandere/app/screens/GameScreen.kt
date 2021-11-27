package com.jemandandere.app.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jemandandere.app.consts.Colors
import com.jemandandere.app.objects.Block
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val block = Block()
    private val batch = SpriteBatch()

    override fun render(delta: Float) {
        clearScreen()
        renderGameField()
    }

    private fun clearScreen() {
        ScreenUtils.clear(Colors.BACKGROUND)
    }

    private fun renderGameField() {
        batch.use {
            block.draw(it, 0f, 0f)
        }
    }

    override fun dispose() {
        block.disposeSafely()
        batch.disposeSafely()
    }
}
