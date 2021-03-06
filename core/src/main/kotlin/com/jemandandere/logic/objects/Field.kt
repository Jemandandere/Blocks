package com.jemandandere.logic.objects

import com.jemandandere.logic.Action
import com.jemandandere.logic.Colors
import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.figures.Figure

class Field(val width: Int = Parameters.fieldWidth, val height: Int = Parameters.fieldHeight) {

    private val _map = mutableListOf<Int>()
    val map: List<Int>
        get() = getActualMap()

    private var figure: Figure? = null

    private var timer: Float = 0f

    init {
        repeat(width * height) { _map.add(Colors.GREY.value) }
        createFigure()
    }

    fun handleAction(action: Action) = when (action) {
        Action.Down -> tryDown()
        Action.Left -> tryLeft()
        Action.Right -> tryRight()
        Action.Rotate -> tryRotate()
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
                set(pos.y * width + pos.x, figure?.color?.value ?: 0)
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

    private fun tryRotate() {
        figure?.let { fig ->
            if (canRotate(fig)) {
                figure?.rotate()
            }
        }
    }

    private fun canRotate(figure: Figure): Boolean {
        // TODO VDY 211128 add rotate check
//        figure.getPosList().forEach { pos ->
//            if (pos.y * width + pos.x >= width * height - width || _map[(pos.y + 1) * width + pos.x]) {
//                return false
//            }
//        }
        return true
    }

    private fun canDown(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.y * width + pos.x >= width * height - width || _map[(pos.y + 1) * width + pos.x] > 0) {
                return false
            }
        }
        return true
    }

    private fun canLeft(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.x == 0 || _map[pos.y * width + pos.x - 1] > 0) {
                return false
            }
        }
        return true
    }

    private fun canRight(figure: Figure): Boolean {
        figure.getPosList().forEach { pos ->
            if (pos.x >= width - 1 || _map[pos.y * width + pos.x + 1] > 0) {
                return false
            }
        }
        return true
    }

    private fun fixFigure(figure: Figure) {
        figure.getPosList().forEach { pos ->
            _map[pos.y * width + pos.x] = figure.color.value
            createFigure()
        }
    }

    private fun checkLines() {
        var isFull = true
        _map.forEachIndexed { index, color ->
            if (color == 0) isFull = false
            if (index % width == width - 1) {
                if (isFull) removeLine(index)
                isFull = true
            }
        }
    }

    private fun removeLine(index: Int) {
        // TODO VDY 211128 fix top lines remove
        for (i in index downTo 0) {
            if (i < width) {
                _map[i] = 0
            } else {
                _map[i] = _map[i - width]
            }
        }
    }

    companion object {

        private const val SECOND = 1.0f
    }
}