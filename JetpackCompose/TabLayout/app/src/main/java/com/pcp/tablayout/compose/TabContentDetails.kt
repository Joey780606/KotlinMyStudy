package com.pcp.tablayout.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pcp.tablayout.compose.ui.theme.JetpackColorTwo

@Composable
fun TabScreenOne(data : String) {
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
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Hello Joey")
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