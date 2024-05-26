package com.infinitelearning.infiniteapp.presentation.screen.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.infinitelearning.infiniteapp.data.local.datastore.DataStore
import com.infinitelearning.infiniteapp.data.local.dummy.DummyData
import com.infinitelearning.infiniteapp.data.local.sharedpreferences.SharedPreferencesManager
import com.infinitelearning.infiniteapp.data.local.sqlite.DatabaseHelper
import com.infinitelearning.infiniteapp.domain.model.Mentee
import com.infinitelearning.infiniteapp.domain.model.Mentor
import com.infinitelearning.infiniteapp.navigation.Screen
import com.infinitelearning.infiniteapp.presentation.screen.home.component.CardTask
import com.infinitelearning.infiniteapp.presentation.screen.home.component.DialogAddMentee
import com.infinitelearning.infiniteapp.presentation.screen.home.component.DialogUpdateMentee
import com.infinitelearning.infiniteapp.presentation.screen.home.component.Header
import com.infinitelearning.infiniteapp.presentation.screen.home.component.MenteeItem
import com.infinitelearning.infiniteapp.presentation.screen.home.component.MentorItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {
    val mentors = DummyData.mobileMentors

    val sharedPreferencesManager = remember {
        SharedPreferencesManager(context)
    }
    val dataStore = DataStore(context)

    val name = sharedPreferencesManager.name ?: ""

    val databaseHelper = DatabaseHelper(context)
    val mentees = databaseHelper.readData()

    var isDialogShown by remember { mutableStateOf(false) }
    var isDetailShown by remember { mutableStateOf(false) }
    var idMentee by remember { mutableIntStateOf(0) }
    var nameMentee by remember { mutableStateOf("") }
    var programMentee by remember { mutableStateOf("") }
    var batchMentee by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    if (isDialogShown) {
        DialogAddMentee(
            onDismiss = { isDialogShown = false },
            name = nameMentee,
            onNameChange = { nameMentee = it },
            program = programMentee,
            onProgramChange = { programMentee = it },
            batch = batchMentee,
            onBatchChange = { batchMentee = it },
            imageUrl = imageUrl,
            onImageUrlChange = { imageUrl = it },
            onUploadClick = {
                if (nameMentee.isNotBlank() || programMentee.isNotBlank() || batchMentee.isNotBlank() || imageUrl.isNotBlank()) {
                    databaseHelper.insertData(nameMentee, programMentee, batchMentee, imageUrl)
                    isDialogShown = false
                    nameMentee = ""
                    programMentee = ""
                    batchMentee = ""
                    imageUrl = ""
                    Toast.makeText(context, "Berhasil Menambah Mentee", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Semua Field wajib diisi", Toast.LENGTH_SHORT).show()
                }
            }
        )
    }

    if (isDetailShown) {
        DialogUpdateMentee(
            id = idMentee,
            onDismiss = { isDetailShown = false },
            mentees = databaseHelper.readData().filter { mentee ->
                mentee.id == idMentee
            },
            databaseHelper = databaseHelper
        )
    }

    HomeContent(
        navController = navController,
        name = name,
        mentors = mentors,
        mentees = mentees,
        onLogoutClick = {
            sharedPreferencesManager.clear()
            coroutineScope.launch {
                dataStore.clearStatus()
            }
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Home.route) {
                    inclusive = true
                }
            }
        },
        onInsertClick = {
            isDialogShown = true
        },
        modifier = modifier,
        showDetail = { menteeId, isShown ->
            idMentee = menteeId
            isDetailShown = isShown
        }
    )
}

@Composable
fun HomeContent(
    navController: NavController,
    name: String,
    mentors: List<Mentor>,
    mentees: List<Mentee>,
    onLogoutClick: () -> Unit,
    onInsertClick: () -> Unit,
    showDetail: (Int, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onInsertClick) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Icon")
            }
        },
        modifier = modifier
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(it)
        ) {
            item {
                Header(
                    name = name,
                    onLogoutClick = onLogoutClick,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                CardTask(onTaskClick = { navController.navigate(Screen.Task.route) })
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier
                ) {
                    items(mentors, key = { it.id }) {
                        MentorItem(mentor = it) { mentorId ->
                            navController.navigate(Screen.Detail.route + "/$mentorId")
                        }
                    }
                }
            }
            items(mentees, key = { it.id }) {
                MenteeItem(
                    mentee = it,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) { menteeId ->
                    showDetail(menteeId, true)
                }
            }
        }
    }
}