package com.jemandandere.logic

sealed class Action {

    object Down: Action()

    object Left: Action()

    object Right: Action()
}