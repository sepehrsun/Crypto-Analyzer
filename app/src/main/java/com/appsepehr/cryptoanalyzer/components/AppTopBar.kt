package com.appsepehr.cryptoanalyzer.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appsepehr.cryptoanalyzer.R

@Composable
fun AppTopBar(
    title: String,
    icon: ImageVector? = null,
    isMain: Boolean = true,
    navController: NavController,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (isMain)
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher),
                        contentDescription = "app icon",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .size(48.dp)
                    )
                if (icon != null) {
                    Icon(imageVector = icon,
                        contentDescription = "arrow back",
                        tint = Color.Red.copy(0.8f),
                        modifier = Modifier.clickable {
                            onBackArrowClicked.invoke()
                        })
                }
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = title,
                    color = MaterialTheme.colors.primary,
                    style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp)
                )
//                Icon(imageVector = Icons.Default.Home, contentDescription ="app icon" )
            }
        }, backgroundColor = Color.Transparent, elevation = 0.dp
    )

}