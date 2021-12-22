package com.pcp.tablayout.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
    val pagerState = rememberPagerState(pageCount = 3)  //可學: PagerState.kt
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}

@ExperimentalPagerApi   //因為 PagerState 需要
@Composable
fun TabsContent(pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->   //Pager.kt
        when(page) {
            0 -> TabScreenOne(data = "Make It Easy One")
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