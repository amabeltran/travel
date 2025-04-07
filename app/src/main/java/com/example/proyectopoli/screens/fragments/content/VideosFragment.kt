package com.example.proyectopoli.screens.fragments.content

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.proyectopoli.R
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



@Composable
fun VideosFragment(context: Context) {
    val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.palawan}")


    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.img_fondo_video),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Título
        Text(
            text = "Videos",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        )

        // Reproductor de video
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .align(Alignment.Center)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xAAFFFFFF),
                            Color(0x88FFFFFF)
                        )
                    ),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AndroidView(
                factory = {
                    VideoView(it).apply {
                        setVideoURI(videoUri)
                        setMediaController(MediaController(it).apply {
                            setAnchorView(this@apply)
                        })
                        start()
                    }
                },
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "País: Perú", color = Color.Black,fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Text(text = "Fecha: 06/04/2025", color = Color.Black,fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "Recuerdo: Paseo en bote", color = Color.Black,fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }

        // Botón Home
        Image(
            painter = painterResource(id = R.drawable.btn_home),
            contentDescription = "Inicio",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .size(100.dp)
                .clickable {
                    // Acción al presionar Home
                }
        )
    }
}