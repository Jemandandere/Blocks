package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Pos
import kotlin.random.Random

abstract class Figure(val pos: IntArray) {

    abstract var xPos: Int
    abstract var yPos: Int

    fun getPosList(): List<Pos> = pos.map { i ->
        Pos(
            x = (xPos + i / 2),
            y = (yPos + i % 2)
        )
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