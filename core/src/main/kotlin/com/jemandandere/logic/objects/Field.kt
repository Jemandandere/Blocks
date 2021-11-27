package com.jemandandere.logic.objects

import com.jemandandere.logic.Parameters
import com.jemandandere.logic.objects.figures.Figure
import com.jemandandere.logic.objects.figures.FigureO

class Field(val width: Int = Parameters.fieldWidth, height: Int = Parameters.fieldHeight) {

    var figure: Figure? = null
    private val _map = mutableListOf<Boolean>()

    val map: List<Boolean>
        get() = _map.apply {
            figure?.let { fig ->
                fig.pos.forEach { i ->
                    val x = (fig.xPos + i / 2)
                    val y = (fig.yPos + i % 2)
                    set(x * width + y, true)
                }
            }
        }

    init {
        repeat(width * height) {
            _map.add(false)
        }

        figure = FigureO(0, 0)
    }
}