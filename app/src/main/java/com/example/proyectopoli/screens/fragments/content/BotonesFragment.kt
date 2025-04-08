package com.example.proyectopoli.screens.fragments.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BotonesFragment() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB3E5FC)) // Fondo azul claro
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Acción guardar favorito */ }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Guardar favorito",
                        tint = Color.Red,
                        modifier = Modifier.size(40.dp)
                    )
                    Text("Favoritos", fontSize = 14.sp)
                }
            }

            Spacer(modifier = Modifier.width(40.dp))

            IconButton(onClick = { /* Acción compartir */ }) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartir",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(40.dp)
                    )
                    Text("Compartir", fontSize = 14.sp)
                }
            }
        }
    }
}