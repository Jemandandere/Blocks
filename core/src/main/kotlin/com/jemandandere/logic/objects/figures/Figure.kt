package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors
import com.jemandandere.logic.Pos
import kotlin.random.Random

abstract class Figure(val pos: IntArray) {

    abstract var xPos: Int
    abstract var yPos: Int

    abstract val color: Colors

    private var rotate = 0

    fun getPosList(): List<Pos> = pos.map { i ->
        // TODO VDY 211128 fix rotate
        when (rotate % 4) {
            0 -> Pos(
                x = (xPos + i / 2),
                y = (yPos + i % 2),
            )
            1 -> Pos(
                x = (xPos + 1 + i % 2),
                y = (yPos - 1 + i / 2),
            )
            2 -> Pos(
                x = (xPos + i / 2),
                y = (yPos - i % 2),
            )
            else -> Pos(
                x = (xPos + 1 - i % 2),
                y = (yPos - 1 + i / 2),
            )
        }
    }

    fun moveDown() {
        this.yPos++
    }

    fun moveRight() {
        this.xPos++
    }

    fun moveLeft() {
        this.xPos--
    }

    fun rotate() {
        rotate++
    }


    companion object {

        private const val FIGURES_COUNT = 7

        fun getRandom(xPos: Int, yPos: Int) = when (Random.nextInt(FIGURES_COUNT)) {
            0 -> FigureI(xPos, yPos)
            1 -> FigureJ(xPos, yPos)
            2 -> FigureL(xPos, yPos)
            3 -> FigureO(xPos, yPos)
            4 -> FigureS(xPos, yPos)
            5 -> FigureT(xPos, yPos)
            else -> FigureZ(xPos, yPos)
        }
    }
}