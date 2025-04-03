package com.example.proyectopoli.navigation

import android.content.Context // ✅ Agrega esta importación
import androidx.compose.runtime.Composable
import com.example.proyectopoli.screens.fragments.content.BotonesFragment
import com.example.proyectopoli.screens.fragments.content.FotosFragment
import com.example.proyectopoli.screens.fragments.content.PerfilFragment
import com.example.proyectopoli.screens.fragments.content.VideosFragment
import com.example.proyectopoli.screens.fragments.content.WebFragment

@Composable
fun ContentNavigation(selectedOption: String, userName: String, userEmail: String, context: Context) {
    when (selectedOption) {
        "perfil" -> PerfilFragment(context = context) // ✅ Pasamos el contexto
        "fotos" -> FotosFragment()
        "videos" -> VideosFragment()
        "web" -> WebFragment()
        "botones" -> BotonesFragment()

    }
}
