package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureT(
    override var xPos: Int,
    override var yPos: Int,
) : Figure(intArrayOf(0, 2, 3, 4)) {

    override val color = Colors.PURPLE
}