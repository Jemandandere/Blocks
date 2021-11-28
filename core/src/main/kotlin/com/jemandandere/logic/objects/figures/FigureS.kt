package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureS(
    override var xPos: Int,
    override var yPos: Int,
) : Figure( intArrayOf(1, 2, 3, 4)) {

    override val color = Colors.GREEN
}