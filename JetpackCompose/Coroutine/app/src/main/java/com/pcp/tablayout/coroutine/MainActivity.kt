package com.pcp.tablayout.coroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pcp.tablayout.coroutine.ui.theme.CoroutineTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()
                    //,color = MaterialTheme.colors.background
                ) {
                    appUI()
                }
            }
        }
    }
}

@Composable
fun appUI() {
    var processWork = remember { mutableStateOf("") }
    Column() {
        DropdownMenuShow()
        //DropdownMenuShow( info -> processWork = info )
        ButtonSelect()
    }

}

@Composable
fun ButtonSelect() {
    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        onClick = {}) {
        Text("Go")
    }
}

@Composable
fun DropdownMenuShow() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("First item", "Second item")
    var buttonString = remember { mutableStateOf("Empty") }

    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        onClick = { expanded = !expanded }) {
        Text(buttonString.value)
        Icon(
            imageVector = Icons.Filled.ArrowDropDown,
            contentDescription = null
        )
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        suggestions.forEach { label ->
            DropdownMenuItem(onClick = {
                expanded = false
                buttonString.value = label
            }) {
                Text(text = label)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoroutineTheme {
        Surface(modifier = Modifier.fillMaxSize()
            ,color = Color.White
        ) {
            appUI()
        }
    }
}