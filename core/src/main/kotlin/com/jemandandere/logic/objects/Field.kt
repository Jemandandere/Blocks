package com.jemandandere.logic.objects

import com.jemandandere.logic.Action
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

    fun handleAction(action: Action) = when (action) {
        Action.Down -> tryDown()
        Action.Left -> tryLeft()
        Action.Right -> tryRight()
    }

    fun update(delta: Float) {
        timer += delta
        if (timer < SECOND) return
        timer -= SECOND
        tryDown()
    }

    private fun getActualMap() = _map.toMutableList().apply {
        figure?.let { fig ->
            fig.getPosList().forEach { pos ->
                set(pos.y * width + pos.x, true)
            }
        }
    }

    private fun createFigure() {
        figure = Figure.getRandom(Parameters.figureStartXPos, Parameters.figureStartYPos)
    }

    private fun tryDown() {
        figure?.let { fig ->
            if (canDown(fig)) {
                timer = 0f
                figure?.moveDown()
            } else {
                fixFigure(fig)
                checkLines()
            }
        }
    }

    private fun tryLeft() {
        figure?.let { fig ->
            if (canLeft(fig)) {
                figure?.moveLeft()
            }
        }
    }

    private fun tryRight() {
        figure?.let { fig ->
            if (canRight(fig)) {
                figure?.moveRight()
            }
        }
    }

    private fun canDown(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.y * width + pos.x >= width * height - width || _map[(pos.y + 1) * width + pos.x]) {
                return false
            }
        }
        return true
    }

    private fun canLeft(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.x == 0 || _map[pos.y * width + pos.x - 1]) {
                return false
            }
        }
        return true
    }

    private fun canRight(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.x >= width - 1 || _map[pos.y * width + pos.x + 1]) {
                return false
            }
        }
        return true
    }

    private fun fixFigure(figure: Figure) {
        figure.getPosList().forEach { pos ->
            _map[pos.y * width + pos.x] = true
            createFigure()
        }
    }

    private fun checkLines() {
        var isFull = true
        _map.forEachIndexed { index, hasBlock ->
            if (!hasBlock) isFull = false
            if (index % width == width - 1) {
                if (isFull) removeLine(index)
                isFull = true
            }
        }
    }

    private fun removeLine(index: Int) {
        for (i in index downTo 0) {
            println(i)
            if (i < width) {
                _map[i] = false
            } else {
                _map[i] = _map[i - width]
            }
        }
    }

    companion object {

        private const val SECOND = 1.0f
    }
}