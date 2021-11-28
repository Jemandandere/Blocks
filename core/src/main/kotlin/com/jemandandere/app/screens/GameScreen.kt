package com.jemandandere.app.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.jemandandere.app.consts.Colors
import com.jemandandere.app.consts.Sizes
import com.jemandandere.app.draws.BlockGreen
import com.jemandandere.app.draws.BlockGrey
import com.jemandandere.app.utils.draw
import com.jemandandere.logic.Action
import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.Field
import ktx.app.KtxScreen
import ktx.assets.disposeSafely
import ktx.graphics.use

class GameScreen : KtxScreen {

    private val batch = SpriteBatch()
    private val blockActive = BlockGreen()
    private val blockInactive = BlockGrey()

    private val field = Field()

    override fun render(delta: Float) {
        clearScreen()
        handleInput()
        updateGameField(delta)
        renderGameField()
    }

    private fun clearScreen() {
        ScreenUtils.clear(Colors.BACKGROUND)
    }

    private fun handleInput() {
        val action = when {
            Gdx.input.isKeyPressed(Input.Keys.DOWN) -> Action.Down
            Gdx.input.isKeyJustPressed(Input.Keys.LEFT) -> Action.Left
            Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) -> Action.Right
            Gdx.input.isKeyJustPressed(Input.Keys.UP) -> Action.Rotate
            else -> return
        }
        field.handleAction(action)
    }

    private fun updateGameField(delta: Float) {
        field.update(delta)
    }

    private fun renderGameField() {
        batch.use {
            field.draw(
                it,
                blockActive,
                blockInactive,
                (Sizes.screenWidth - (Parameters.fieldWidth * Sizes.blockSize)) / 2,
                10f
            )
        }
    }

    override fun dispose() {
        blockActive.disposeSafely()
        blockInactive.disposeSafely()
        batch.disposeSafely()
    }
}
