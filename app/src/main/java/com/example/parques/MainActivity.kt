package com.example.parques

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parques.data.Comunidades
import com.example.parques.data.Parques
import com.example.parques.ui.theme.ParquesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ParquesTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cuadroBuscar()
                    filaElementos()
                    listadoParques()
                }
            }
        }
    }
}

@Preview
@Composable
fun cuadroBuscar() {
    var textoCuadro by rememberSaveable { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = textoCuadro,
        onValueChange = { textoCuadro = it },
        placeholder = { Text(text = "Buscar") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Buscar",
                tint = Color(0xFF694646) // Color corregido
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

val comunidades = listOf(
    Comunidades("Andalucía", R.drawable.marialuisa),
    Comunidades("Madrid", R.drawable.retiro),
    Comunidades("Valencia", R.drawable.gulliver),
    Comunidades("Barcelona", R.drawable.guell),
    Comunidades("Galicia", R.drawable.rosaliadecastro),
)

@Composable
fun elemento(comunidad: Comunidades) { // pinto un elemento con su imagen y el nombre de la comunidad debajo
    Column(
        modifier = Modifier.padding(8.dp),
    ){
        Image(
            painter = painterResource(id = comunidad.imagenComunidad),
            contentDescription = "Imagen comunidad",
            modifier = Modifier.clip(CircleShape).size(90.dp) // poner la imagen circular
        )
        Text(text = comunidad.nombreComunidad)
    }
}

@Preview
@Composable
fun filaElementos() {
    Column() {
        Text(
            text = "Comunidades",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ){
            comunidades.forEach() { comunidades ->
                item {
                    elemento(comunidad = comunidades)
                }
            }
        }
    }
}

val parques = listOf(
    Parques("Parque de Maria Luisa", R.drawable.marialuisa, "Andalucía"),
    Parques("Parque del Retiro", R.drawable.retiro, "Madrid"),
    Parques("Parque del Gulliver", R.drawable.gulliver, "Valencia"),
    Parques("Parque guell", R.drawable.guell, "Barcelona"),
    Parques("Parque de la Rosalia de Castro", R.drawable.rosaliadecastro, "Galicia"),
    Parques("Parque de Campo del Moro", R.drawable.campodelmoro, "Madrid"),
    Parques("Parque de la Boqueria", R.drawable.madridrio, "Madrid"),
    Parques("Parque del Oeste", R.drawable.parquedeloeste, "Madrid")
)

@Preview
@Composable
fun listadoParques() {
    var contexto = LocalContext.current
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        parques.forEach() { parque ->
            item {
                ElevatedCard(
                    onClick = {
                        Toast.makeText(contexto,"Has pulsado el parque ${parque.nombreParque}", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ){
                    Row(
                        modifier = Modifier.padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = parque.imagenParque),
                            contentDescription = "Imagen parque",
                            modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(100.dp) // redonde los bordes de la imagen
                        )
                            Text(
                                text = parque.nombreParque,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                    }
                }
            }
        }
    }
}