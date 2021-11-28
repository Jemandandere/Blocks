package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureZ(
    override var xPos: Int,
    override var yPos: Int,
) : Figure( intArrayOf(0, 2, 3, 5)) {

    override val color = Colors.RED
}