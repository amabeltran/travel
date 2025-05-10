package com.example.proyectopoli.navigation

import android.content.Context // ✅ Agrega esta importación
import androidx.compose.runtime.Composable
import com.example.proyectopoli.screens.fragments.content.*

@Composable
fun ContentNavigation(selectedOption: String, userName: String, userEmail: String, context: Context) {
    when (selectedOption) {
        "perfil" -> PerfilFragment(context = context) // ✅ Pasamos el contexto
        "fotos" -> FotosFragment()
        "videos" -> VideosFragment(context = context)
        "web" -> WebFragment()
        "botones" -> BotonesFragment()
        "formulario_reserva" -> FormularioReserva()

    }
}
