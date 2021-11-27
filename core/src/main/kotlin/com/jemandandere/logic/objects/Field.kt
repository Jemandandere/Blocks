package com.jemandandere.logic.objects

import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.figures.Figure

class Field(val width: Int = Parameters.fieldWidth, val height: Int = Parameters.fieldHeight) {

    private val _map = mutableListOf<Boolean>()
    val map: List<Boolean>
        get() = getActualMap()

    private var figure: Figure? = null

    private var timer: Float = 0f

    init {
        repeat(width * height) { _map.add(false) }
        createFigure()
    }

    fun update(delta: Float) {
        timer += delta
        if (timer < SECOND) return
        timer -= SECOND
        figure?.let { fig ->
            if (canDown(fig)) {
                moveDown(fig)
            } else {
                fixFigure(fig)
            }
        }
    }

    private fun getActualMap() = _map.toMutableList().apply {
        figure?.let { fig ->
            fig.getPosList().forEach { pos ->
                set(pos.y * width + pos.x, true)
            }
        }
    }

    private fun createFigure() {
        figure = Figure.getRandom(START_X_POS, START_Y_POS)
    }

    private fun canDown(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.y * width + pos.x > width*height-width || _map[(pos.y+1) * width + pos.x]) {
                return false
            }
        }
        return true
    }

    private fun moveDown(figure: Figure) {
        this.figure?.yPos = figure.yPos + 1
    }

    private fun fixFigure(figure: Figure) {
        figure.getPosList().forEach { pos ->
            _map[pos.y * width + pos.x] = true
            createFigure()
        }
    }


    companion object {

        private const val START_X_POS = 3
        private const val START_Y_POS = 0

        private const val SECOND = 0.2f
    }
}