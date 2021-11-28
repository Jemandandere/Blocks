package com.jemandandere.logic.objects.figures

import com.jemandandere.logic.Colors

class FigureO(
    override var xPos: Int,
    override var yPos: Int,
) : Figure(intArrayOf(0, 1, 2, 3)) {

    override val color = Colors.YELLOW
}