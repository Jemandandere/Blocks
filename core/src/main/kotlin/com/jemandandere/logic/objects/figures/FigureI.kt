package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureI(
    override var xPos: Int,
    override var yPos: Int,
) : Figure(intArrayOf(0, 2, 4, 6)) {

    override val color = Colors.CYAN
}