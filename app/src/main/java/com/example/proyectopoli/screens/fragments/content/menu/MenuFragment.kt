package com.example.proyectopoli.screens.fragments.content.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.proyectopoli.model.MenuItem
import androidx.compose.foundation.background
@Composable
fun MenuFragment(
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    val menuItems = listOf(
        MenuItem(id = "perfil", title = "Perfil", icon = Icons.Default.Person),
        MenuItem(id = "fotos", title = "Fotos", icon = Icons.Default.Photo),
        MenuItem(id = "videos", title = "Video", icon = Icons.Default.Videocam),
        MenuItem(id = "web", title = "Web", icon = Icons.Default.Public),
        MenuItem(id = "botones", title = "Botones", icon = Icons.Default.RadioButtonChecked)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 32.dp)
    ) {
        // Encabezado
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Time",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Travel",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Divider(modifier = Modifier.padding(vertical = 8.dp))

        // Lista completa de items - MOSTRARÁ TODOS (4 ELEMENTOS)
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            items(menuItems) { item ->
                DrawerItem(
                    item = item,
                    selected = selectedOption == item.id,
                    onItemClick = { onOptionSelected(item.id) }
                )
            }
        }
    }
}

@Composable
fun DrawerItem(
    item: MenuItem,
    selected: Boolean,
    onItemClick: () -> Unit
) {
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
    } else {
        Color.Transparent
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick)
            .background(backgroundColor)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
    }
}

