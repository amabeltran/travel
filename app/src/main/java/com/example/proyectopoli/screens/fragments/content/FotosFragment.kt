package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.proyectopoli.R

data class Foto(
    val imagenRes: Int,
    val pais: String,
    val fecha: String,
    val recuerdo: String
)

@Composable
fun FotosFragment() {
    val fotos = listOf(
        Foto(R.drawable.foto1, "Italia", "2022-03-15", "Viaje al desierto"),
        Foto(R.drawable.foto2, "Peru", "2023-06-10", "Templos y sakura"),
        Foto(R.drawable.foto3, "Islandia", "2021-12-25", "Auroras boreales")
    )

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.img_fondo_foto),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Contenido con scroll
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Fotos",
                fontSize = 30.sp,
                color = Color.White,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(0.dp))

            fotos.forEach { foto ->
                FotoCard(foto)
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(100.dp)) // Espacio para que no tape el botón fijo
        }

        // Botón de inicio fijo
        Image(
            painter = painterResource(id = R.drawable.btn_home),
            contentDescription = "Inicio",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 0.dp)
                .size(100.dp)
                .clickable {
                    // Acción al presionar Home
                }
        )
    }
}


@Composable
fun FotoCard(foto: Foto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.White.copy(alpha = 0.2f))
            .padding(0.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = foto.imagenRes),
                contentDescription = "Imagen de ${foto.pais}",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 0.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(text = "País: ${foto.pais}", color = Color.White, fontFamily = FontFamily.Cursive,
                fontSize = 30.sp)
            Text(text = "Fecha: ${foto.fecha}", color = Color.White)
            Text(text = "Recuerdo: ${foto.recuerdo}", color = Color.White)
        }
    }
}

