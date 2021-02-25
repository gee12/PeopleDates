package com.gee12.peopledates.ui.group

/**
 * Data validation state of the group dialog.
 */
data class GroupDialogState(
    val nameError: Int? = null,
    val urlError: Int? = null,
    val isDataValid: Boolean = false
)