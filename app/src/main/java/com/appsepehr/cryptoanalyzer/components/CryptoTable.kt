package com.appsepehr.cryptoanalyzer.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.appsepehr.cryptoanalyzer.data.model.Crypto
import com.appsepehr.cryptoanalyzer.utils.*


@Composable
fun CryptoTable(cryptoList: MutableState<List<Crypto>>) {
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
    ) {
        item {
            CryptoTitleRow(
                onPriceDiffClick = {
                    cryptoList.value = cryptoList.value.sortedByDescending { it.priceDiff }
                },
                onVolumeClick = {
                    cryptoList.value = cryptoList.value.sortedByDescending { it.volume }
                })
        }

        items(items = cryptoList.value) { crypto ->
            CryptoRow(crypto)
        }

    }
}


@Composable
fun CryptoRow(crypto: Crypto) {
    val context = LocalContext.current
    Card(
        shape = RectangleShape, border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = crypto.logoLink,
                contentDescription = "book image",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(40.dp)
                    .padding(2.dp)
            )
            Text(
                modifier = Modifier
                    .width(80.dp)
                    .clickable { openUrl(context, crypto.link) },
                textAlign = TextAlign.Center,
                text = crypto.name!!,
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier
                    .width(60.dp)
                    .clickable {
                        context.copyToClipboard(crypto.token.toString())
                        showToast(context, "قرارداد در کلیپ بورد کپی شد")
                    },
                textAlign = TextAlign.Center,
                text = if (crypto.chain != null) crypto.chain else "",
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center,
                text = formatPrice(crypto.price!!),
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center,
                text = formatPriceDiff(crypto.priceDiff!!),
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF4CAF50)
            )
            Text(
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center,
                text = formatVolume(crypto.volume!!),
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.width(60.dp),
                textAlign = TextAlign.Center,
                text = if (crypto.score != null) crypto.score.toString() else "",
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun CryptoTitleRow(onPriceDiffClick: () -> Unit = {}, onVolumeClick: () -> Unit = {}) {

    Card(
        shape = RectangleShape,
        elevation = 2.dp,
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Row(
            modifier = Modifier
                .padding(2.dp)
                .height(40.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(40.dp)
                    .padding(2.dp),
                textAlign = TextAlign.Start,
                text = "لوگو"
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(80.dp),
                textAlign = TextAlign.Center,
                text = "نام",
                style = MaterialTheme.typography.subtitle1
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(60.dp),
                textAlign = TextAlign.Center,
                text = "شبکه",
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(60.dp),
                text = "قیمت",
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(60.dp)
                    .clickable { onPriceDiffClick.invoke() },
                text = "تغییرات",
                textAlign = TextAlign.Center,
                maxLines = 2,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(60.dp)
                    .clickable {
                        onVolumeClick.invoke()
                    },
                textAlign = TextAlign.Center,
                text = "حجم",
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 8.dp)
                    .width(60.dp),
                text = "امتیاز",
                textAlign = TextAlign.Center,
                maxLines = 1,
                style = MaterialTheme.typography.caption,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}