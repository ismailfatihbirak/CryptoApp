package com.example.loodoscryptoapp.presentation.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.loodoscryptoapp.R
import com.example.loodoscryptoapp.domain.model.Root

@Composable
fun FavoriteCryptoItem(crypto: Root,viewModel: FavoriteViewModel,authId:String) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            AsyncImage(
                model = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/${crypto.id_icon}.png",
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
            IconButton(
                onClick = {
                    viewModel.deleteFav(authId,crypto.asset_id.toString())
                }
            ) {
                Icon(Icons.Default.Delete,
                    contentDescription = "",
                    tint = colorResource(id = R.color.welcome_color))
            }
        }
    }


}