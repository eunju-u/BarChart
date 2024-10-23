package com.example.barchart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.barchart.ui.theme.BarChartTheme
import com.example.barchart.ui.theme.Pink80
import com.example.barchart.ui.theme.Purple80

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // set BarChartType
        val barChartType = BarChartType.A
        val yMax = 100
        val yUnit = 5

        setContent {
            BarChartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        barChartType = barChartType,
                        yMax = yMax,
                        yUnit = yUnit
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, barChartType: BarChartType, yMax: Int, yUnit: Int) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.Center) {
        item {
            Column(
                modifier = modifier.padding(25.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (barChartType == BarChartType.A) {
                    BarCartWidget(
                        list = dummyList,
                        gridLineSpacing = 25.dp,
                        yMax = yMax,
                        yUnit = yUnit,
                        color = listOf(Purple80, Pink80),
                        barWidth = 25.dp,
                        shape = RoundedCornerShape(
                            topStart = 4.dp,
                            topEnd = 4.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        ),
                    )
                } else {
                    BarCartWidget(
                        barChartType = barChartType,
                        color = listOf(Purple80, Pink80),
                        gridLineSpacing = 25.dp,
                        yMax = yMax,
                        yUnit = yUnit,
                        list = dummyList,
                        yTextStyle = TextStyle(
                            fontSize = 8.sp,
                            color = Gray
                        ),
                        showYAxisUnit = true,
                        barWidth = 25.dp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BarChartTheme {
        Greeting(barChartType = BarChartType.A, yMax = 30, yUnit = 6)
    }
}

val dummyList = listOf(
    GraphItem(20),
    GraphItem(77),
    GraphItem(58),
    GraphItem(34),
    GraphItem(3),
    GraphItem(17),
    GraphItem(80),
    GraphItem(18),
    GraphItem(90),
)
