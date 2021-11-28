package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureJ(
    override var xPos: Int,
    override var yPos: Int,
) : Figure(intArrayOf(0, 2, 4, 5)) {

    override val color = Colors.BLUE
}