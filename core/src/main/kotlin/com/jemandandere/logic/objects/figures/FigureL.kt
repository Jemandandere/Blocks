package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureL(
    override var xPos: Int,
    override var yPos: Int,
) : Figure(intArrayOf(1, 3, 4, 5)) {

    override val color = Colors.ORANGE
}