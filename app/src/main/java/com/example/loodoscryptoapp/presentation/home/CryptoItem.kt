package com.example.loodoscryptoapp.presentation.home

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.loodoscryptoapp.domain.model.Root

@Composable
fun CryptoItem(crypto: Root,navController: NavController) {
    val str = crypto.id_icon
    val result = str?.replace("-", "")
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp).clickable {
                navController.navigate("detail/" +
                        "${crypto.asset_id}/" +
                        "${result}/" +
                        "${crypto.name}/" +
                        "${crypto.price_usd}") },
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/${result}.png",
                contentDescription = "",
                modifier = Modifier.size(64.dp)
            )
            Column {
                Box(modifier = Modifier.width(140.dp)) {
                    Text(
                        text = crypto.asset_id.toString().uppercase(),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Text(
                    text = crypto.name.toString(),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
            Column {
                Box(modifier = Modifier.width(100.dp)) {
                    Text(
                        text = "$"+crypto.price_usd.toString().take(7),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }

}