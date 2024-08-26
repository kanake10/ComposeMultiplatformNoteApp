package com.example.noteapp.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp.domain.model.NotesDomain
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: NotesViewModel = koinViewModel<NotesViewModel>(),
    navController: NavController = rememberNavController()
) {
    val noteUiState by viewModel.notesUiState.collectAsState()

    val backgroundColor = Color.White
    val buttonColor = Color(0xFF001F3F)

    Column(
        modifier = Modifier
            .background(backgroundColor)
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menu")
                    }
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "Test App",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        onClick = { navController.navigate("add_notes") },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(buttonColor),
                        modifier = Modifier.height(40.dp).width(100.dp)
                    ) {
                        Icon(
                            Icons.Filled.Add,
                            contentDescription = "Add Note",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            text = "New",
                            color = Color.White,
                        )
                    }
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor)
        )
        LazyColumn(
            modifier = Modifier
                .background(backgroundColor)
        ) {
            items(noteUiState.notes ?: emptyList()) { note ->
                NoteCard(
                    note = note,
                    onDeleteClick = { id ->
                        viewModel.deleteNote(id)
                    }
                )
            }
        }
    }
}



@Composable
fun NoteCard(
    note: NotesDomain,
    onDeleteClick: (Int) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = note.description,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onDeleteClick(note.id) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Note",
                    tint = Color.Red
                )
            }
        }
    }
}


@Composable
fun AddNoteScreen(
    navController: NavController = rememberNavController(),
    viewModel: NotesViewModel = koinViewModel<NotesViewModel>()
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val backgroundColor = Color.White
    val buttonColor = Color(0xFF001F3F)
    val isTitleValid = title.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = "Add Item",
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(Modifier.height(26.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "* Indicates required fields",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                Text(
                    text = "Item Title *",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Enter a title for the item") },
                    modifier = Modifier.fillMaxWidth(),
                    isError = title.isBlank()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Description",
                    modifier = Modifier.padding(bottom = 16.dp),
                    fontWeight = FontWeight.Bold,
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Add Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(buttonColor),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Cancel", color = Color.White)
            }
            Button(
                onClick = {
                    val note = NotesDomain(id = 0, title = title, description = description)
                    viewModel.saveNote(note)
                    navController.popBackStack()
                },
                enabled = isTitleValid,
                colors = ButtonDefaults.buttonColors(buttonColor),
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text("Save Item", color = Color.White)
            }
        }
    }
}


@Composable
fun MainNav(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {
        composable(
            route = Screen.NotesScreen.route
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.AddNotesScreen.route
        ) {
            AddNoteScreen(navController = navController)
        }
    }
}

sealed class Screen(val route: String) {
    object NotesScreen : Screen("all_notes")
    object AddNotesScreen : Screen("add_notes")
}
