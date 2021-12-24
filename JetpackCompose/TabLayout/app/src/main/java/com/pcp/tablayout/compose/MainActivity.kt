package com.pcp.tablayout.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.pcp.tablayout.compose.ui.theme.JetpackColorOne
import com.pcp.tablayout.compose.ui.theme.TabLayoutTheme
import kotlinx.coroutines.launch

@ExperimentalPagerApi
class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TabLayoutTheme {
                TabScreen()
            }
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabScreen() {
    //var listDetail = mutableListOf("none")    //這個不行,沒有重繪時記錄的問題
    //var listDetail = remember { mutableListOf("none") } //不行
    val listDetail = remember { mutableStateListOf<String>("none") }

    val pagerState = rememberPagerState(pageCount = 3)  //可學: PagerState.kt
    Log.v("TAG", "Print screen")
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState, listDetail
        ) { newName ->
            listDetail.add(newName)
        }
    }
}

@ExperimentalPagerApi   //因為 PagerState 需要
@Composable
fun TabsContent(pagerState: PagerState
                , info: List<String>
                , infoUpdate: (newInfo: String) -> Unit) {
    HorizontalPager(state = pagerState) { page ->   //Pager.kt
        infoUpdate( System.currentTimeMillis().toString())
        when(page) {
            0 -> TabScreenOne(data = "Make It Easy One", info)
            1 -> TabScreenTwo(data = "Make It Easy Two")
            2 -> TabScreenThree(data = "Make It Easy Three")
        }
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    var list = listOf("MIE1", "MIE2", "MIE3")
    var scope = rememberCoroutineScope() //可學: 寫法

    TabRow( //可學: TabRow.kt
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = JetpackColorOne,
        contentColor = Color.White,
        divider = {
            TabRowDefaults.Divider(
                thickness = 2.dp,
                color = Color.Green
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                //Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),   // Already no this function.
                height = 2.dp,
                color = Color.White
            )
        }
    ) {
        list.forEachIndexed { index, _->    //可學: _Collections.kt
            Tab(    //可學: Tab.kt
                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TabLayoutTheme {
        TabScreen()
    }
}