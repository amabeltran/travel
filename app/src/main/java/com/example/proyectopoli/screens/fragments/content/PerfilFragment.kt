package com.example.proyectopoli.screens.fragments.content

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.proyectopoli.R
import kotlinx.coroutines.launch

@Composable
fun PerfilFragment(context: Context) {
    val sharedPreferences = context.getSharedPreferences("UserProfile", Context.MODE_PRIVATE)
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var selectedImageUri by remember {
        mutableStateOf(sharedPreferences.getString("profile_image_uri", null)?.let { Uri.parse(it) })
    }
    var userName by remember { mutableStateOf(TextFieldValue(sharedPreferences.getString("user_name", "") ?: "")) }
    var userEmail by remember { mutableStateOf(TextFieldValue(sharedPreferences.getString("user_email", "") ?: "")) }
    var userCity by remember { mutableStateOf(TextFieldValue(sharedPreferences.getString("user_city", "") ?: "")) }
    var userClimate by remember { mutableStateOf(sharedPreferences.getString("user_climate", "") ?: "") }
    var userLocation by remember { mutableStateOf(sharedPreferences.getString("user_location", "") ?: "") }
    var expandedClimate by remember { mutableStateOf(false) }
    var expandedLocation by remember { mutableStateOf(false) }
    var buttonColor by remember { mutableStateOf(Color.Gray) }

    val climatesList = listOf("Cálido", "Frío", "Templado", "Húmedo", "Seco", "Lluvioso")
    val locationsList = listOf("Playa", "Montaña", "Nieve", "Desierto", "Selva", "Ciudad", "Pueblo", "Isla", "Bosque")

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            selectedImageUri = uri
            sharedPreferences.edit().putString("profile_image_uri", uri.toString()).apply()
        }
    }

    // **Estructura principal con fondo**
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_fondo_perfil),
            contentDescription = "Fondo de perfil",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
//
        // **Hacer scroll con LazyColumn**
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                // Imagen de perfil en la parte superior derecha
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = selectedImageUri?.let { rememberAsyncImagePainter(it) } ?: painterResource(id = R.drawable.img_perfil),
                        contentDescription = "Foto de perfil",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.5f))
                            .padding(4.dp)
                            .clickable { imagePickerLauncher.launch("image/*") }
                    )
                }
            }

            // **Campos de entrada con transparencia**
            item {
                OutlinedTextField(
                    value = userName,
                    onValueChange = { userName = it },
                    label = { Text("Nombre") },
                    modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp))
                )
            }
            item {
                OutlinedTextField(
                    value = userEmail,
                    onValueChange = { userEmail = it },
                    label = { Text("Correo") },
                    modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp))
                )
            }
            item {
                OutlinedTextField(
                    value = userCity,
                    onValueChange = { userCity = it },
                    label = { Text("Ciudad") },
                    modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.5f), shape = RoundedCornerShape(5.dp))
                )
            }

            item {
                Text("Preferencias de Clima", color = Color.White)
                Box(modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.5f)).clickable { expandedClimate = true }) {
                    Text(userClimate.ifEmpty { "Seleccionar clima" }, modifier = Modifier.padding(8.dp), color = Color.Black)
                }
                DropdownMenu(expanded = expandedClimate, onDismissRequest = { expandedClimate = false }) {
                    climatesList.forEach { climate ->
                        DropdownMenuItem(
                            text = { Text(climate) },
                            onClick = {
                                userClimate = climate
                                expandedClimate = false
                            }
                        )
                    }
                }
            }

            item {
                Text("Preferencias de Lugar", color = Color.White)
                Box(modifier = Modifier.fillMaxWidth().background(Color.White.copy(alpha = 0.5f)).clickable { expandedLocation = true }) {
                    Text(userLocation.ifEmpty { "Seleccionar lugar" }, modifier = Modifier.padding(8.dp), color = Color.Black)
                }
                DropdownMenu(expanded = expandedLocation, onDismissRequest = { expandedLocation = false }) {
                    locationsList.forEach { location ->
                        DropdownMenuItem(
                            text = { Text(location) },
                            onClick = {
                                userLocation = location
                                expandedLocation = false
                            }
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            // **Botón guardar **
            item {
                Button(
                    onClick = {
                        sharedPreferences.edit()
                            .putString("user_name", userName.text)
                            .putString("user_email", userEmail.text)
                            .putString("user_city", userCity.text)
                            .putString("user_climate", userClimate)
                            .putString("user_location", userLocation)
                            .apply()

                        buttonColor = Color(0xFF6495ED)

                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Cambios guardados correctamente")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor)

                ) {

                    Text("Guardar")
                }
            }
        }

        // Snackbar para mostrar confirmación
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
