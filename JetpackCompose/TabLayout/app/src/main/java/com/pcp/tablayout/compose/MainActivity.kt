package com.pcp.tablayout.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pcp.tablayout.compose.ui.theme.TabLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabLayoutTheme {
                TabScreen()
            }
        }
    }
}

@Composable
fun TabScreen() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TabScreen()
}