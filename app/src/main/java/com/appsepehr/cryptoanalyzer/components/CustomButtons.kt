package com.appsepehr.cryptoanalyzer.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VolumeButton(title: String, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =
            if (isSelected) MaterialTheme.colors.primary
            else MaterialTheme.colors.onPrimary
        ), border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
    ) {
        Text(
            text = title,
            color = if (isSelected) MaterialTheme.colors.onPrimary
            else MaterialTheme.colors.primary
        )
    }
}

@Composable
fun ApiButton(title: String, isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(corner = CornerSize(12.dp)),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =
            if (isSelected) Color.Red
            else MaterialTheme.colors.onPrimary
        ), border = BorderStroke(width = 1.dp, color = MaterialTheme.colors.primary)
    ) {
        Text(
            text = title,
            color = if (isSelected) MaterialTheme.colors.onPrimary
            else Color.Red
        )
    }
}