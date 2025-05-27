package com.ab.sclr.ui.compose

@FunctionalInterface
interface ControlsHandler {
    fun onSlideAddClick()
    fun onSlideRemoveClick()
    fun onSelectGridClick()
    fun onSelectOverlayClick()
    fun onPictureSelectClick()
    fun onNoOp()
}