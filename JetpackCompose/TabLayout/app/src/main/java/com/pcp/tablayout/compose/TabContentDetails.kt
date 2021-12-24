package com.pcp.tablayout.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pcp.tablayout.compose.ui.theme.JetpackColorTwo

@Composable
fun TabScreenOne(data : String, info : List<String>) {
    val buttonString = remember {
        mutableStateListOf("Start scan probe", "Connect probe", "Write\nmodel number\nproduction date", "Read\nmodel number\nproduction date", "Get rssi", "Set filter rssi value")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        //modifier = Modifier.fillMaxWidth().fillMaxHeight(0.5f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            for (buttonNumber in 0..buttonString.size) {
                if (buttonNumber % 2 == 1) {
                    rowShow(
                        buttonString, buttonNumber
                    ) { rowId ->
                        when (rowId) {
                            0 -> if (buttonString[0] == "Start scan probe")
                                buttonString[0] = "Stop scan probe"
                        }
                    }
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.15f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Text(text = "Hello Joey")
            DropdownMenuShow()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(1.0f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // LazyColumn 與 info.forEach的不同,還要研究
//            LazyColumn(Modifier.fillMaxWidth()) {
//                items(info) { content ->
//                    showText(content)
//                }
//            }
            info.forEach { content ->
                showText(content)
            }
        }
    }
}

@Composable
fun rowShow(info: List<String>, number: Int, textUpdate: (stringId: Int) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(), //使用上層的最大寬度
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(modifier = Modifier.weight(0.5f)
            .padding(5.dp),
            onClick = { textUpdate(number - 1) }) {
            Text(info[number - 1],
                textAlign = TextAlign.Center)
        }
        Button(modifier = Modifier.weight(0.5f)
            .padding(5.dp),
            onClick = { textUpdate(number) }) {
            Text(info[number],
                textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun DropdownMenuShow() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1", "Item2", "Item3")
    var buttonString = remember {
        mutableStateOf("Empty")
    }

    Button(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        onClick = { expanded = !expanded}) {
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

@Composable
fun showText(content: String) {
    Text(content)
}

@Composable
fun TabScreenTwo(data : String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = JetpackColorTwo,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun TabScreenThree(data : String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.h5,
            color = JetpackColorTwo,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold
        )
    }
}