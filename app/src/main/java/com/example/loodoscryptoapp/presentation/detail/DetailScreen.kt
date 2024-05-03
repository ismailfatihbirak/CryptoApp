package com.example.loodoscryptoapp.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.loodoscryptoapp.R
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun DetailScreen(asset_id:String,id_icon:String,name:String,price_usd:String,viewModel: DetailViewModel= hiltViewModel()) {
    lateinit var auth: FirebaseAuth
    auth = Firebase.auth
    val currentUser = auth.currentUser!!.uid
    val context = LocalContext.current
    Box(modifier = Modifier.fillMaxSize()) {
        IconButton(
            onClick = { viewModel.saveFav(currentUser,asset_id, id_icon, name, price_usd)
                Toast.makeText(
                    context,
                    "save fav successful",
                    Toast.LENGTH_SHORT
                ).show()},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp, top = 16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                tint = colorResource(id = R.color.welcome_color),
                contentDescription = "",
                modifier = Modifier.size(60.dp)
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        AsyncImage(
            model = "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_256/${id_icon}.png",
            contentDescription = "",
            modifier = Modifier
                .size(200.dp)
                .clip(shape = RoundedCornerShape(8.dp))
        )
        Text(
            text = asset_id.uppercase(),
            style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = name,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "$price_usd".take(7)+" USD",
            style = TextStyle(fontSize = 30.sp),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

    }


}