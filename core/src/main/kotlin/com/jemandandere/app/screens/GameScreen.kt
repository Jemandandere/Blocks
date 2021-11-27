package com.jemandandere.app.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jemandandere.app.consts.Colors
import com.jemandandere.app.draws.Block
import com.jemandandere.app.utils.draw
import com.jemandandere.logic.objects.Field
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val batch = SpriteBatch()
    private val block = Block()

    private val field = Field()

    override fun render(delta: Float) {
        clearScreen()
        renderGameField()
    }

    private fun clearScreen() {
        ScreenUtils.clear(Colors.BACKGROUND)
    }

    private fun renderGameField() {
        batch.use {
            field.draw(it, block, 10f, 10f)
        }
    }

    override fun dispose() {
        block.disposeSafely()
        batch.disposeSafely()
    }
}
