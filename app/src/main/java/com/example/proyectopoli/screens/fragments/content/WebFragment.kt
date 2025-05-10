package com.example.proyectopoli.screens.fragments.content

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.proyectopoli.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


// Modelo de datos
data class ItemInfo(
    val nombre: String,
    val lugar: String,
    val fecha: String,
    val url: String
)

// Tarjeta reutilizable
@Composable
fun ItemCard(item: ItemInfo, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.nombre, style = MaterialTheme.typography.titleMedium)
            Text(text = item.lugar)
            Text(text = item.fecha, color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { onClick(item.url) }) {
                Text("Ver más")
            }
        }
    }
}

// Pantalla principal con listas
@Composable
fun WebFragment() {
    val context = LocalContext.current
    var selectedOption by remember { mutableStateOf("perfil") }

    val hoteles = listOf(
        ItemInfo("Hotel Estelar", "Bogotá", "12/04/2025", "https://www.booking.com/index.es.html"),
        ItemInfo("Hilton", "Cali", "15/04/2025", "https://www.booking.com/index.es.html")
    )

    val vuelos = listOf(
        ItemInfo("Avianca", "Bogotá → Medellín", "13/04/2025", "https://www.latamairlines.com/co/es/ofertas/ofertas-latam?origin=ALL&destination=ALL&&msclkid=b76846e19b8a1837280f2115c53b564f&utm_source=bing&utm_medium=cpc&utm_campaign=co_ltm_bing_sem_brand_aon_tkt&utm_term=latam&utm_content=brand&gclid=b76846e19b8a1837280f2115c53b564f&gclsrc=3p.ds&gad_source=7"),
        ItemInfo("LATAM", "Medellín → Cali", "16/04/2025", "hhttps://www.latamairlines.com/co/es/ofertas/ofertas-latam?origin=ALL&destination=ALL&&msclkid=b76846e19b8a1837280f2115c53b564f&utm_source=bing&utm_medium=cpc&utm_campaign=co_ltm_bing_sem_brand_aon_tkt&utm_term=latam&utm_content=brand&gclid=b76846e19b8a1837280f2115c53b564f&gclsrc=3p.ds&gad_source=7")
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo con imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_web),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Contenido encima de la imagen
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                // .background(Color.White.copy(alpha = 0.7f)) // Fondo blanco translúcido
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Reservas",
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    color = Color(0xFFAAB8C2) , fontWeight = FontWeight.Bold
                )
            }
            item {
                Button(
                    onClick = { selectedOption = "formulario_reserva" },
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Agregar Reserva")
                }


                when (selectedOption) {
                    "formulario_reserva" -> {
                        FormularioReserva()
                    }
                }
            }


            item {
                Text("🏨 Hoteles", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFAAB8C2) , fontWeight = FontWeight.Bold)
            }
            items(hoteles) { hotel ->
                ItemCard(hotel) { url -> openUrl(context, url) }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text("✈️ Vuelos", style = MaterialTheme.typography.headlineSmall, color = Color(0xFFAAB8C2) , fontWeight = FontWeight.Bold)
            }
            items(vuelos) { vuelo ->
                ItemCard(vuelo) { url -> openUrl(context, url) }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.btn_home),
            contentDescription = "Inicio",
            modifier = Modifier
                .align(Alignment.BottomCenter) // Ahora funciona porque está dentro del Box
                .padding(bottom = 0.dp)
                .size(100.dp)
                .clickable {
                    // Acción al presionar Home
                }
        )
    }

}

// Función para abrir URLs
fun openUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
