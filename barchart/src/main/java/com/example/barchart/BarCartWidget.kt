package com.example.barchart

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

data class GraphItem(
    val size: Int = 0
)

enum class BarChartType {
    A, B
}
/**
 * @param barChartType There are two types of barChart Type A and Type B.
 * In Type A, numbers are displayed on the bars. In Type B, they are not.
 * @param gridLineSpacing sets the spacing between the horizontal lines drawn in the background of the chart.
 * @param gridLineStrokeWidth sets the thickness of the horizontal lines drawn in the background of the chart.
 * @param showYAxisUnit sets the visibility of the unit numbers on the Y-axis.
 * @param yMax sets the maximum value of the Y-axis. It is only available for Type B of the bar chart.
 * @param yUnit sets the unit of the Y-axis.
 * @param barWidth sets the width of the bars.
 * @param yTextStyle sets the text style for the Y-axis, including properties like text size, color, and font.
 * @param barTextStyle sets the text style for the text displayed on the bars, including properties like text size, color, and font.
 * It is only available for Type A of the bar chart.
 * @param color sets the color of the bars. If there are two or more colors, a gradient will be applied.
 * @param shape sets the corner shape of the top of the bars.
 * @param list sets the values that determine the height of the bars.
 *
 * **/
@Composable
fun BarCartWidget(
    barChartType: BarChartType = BarChartType.A,
    gridLineSpacing: Dp = 30.dp,
    gridLineStrokeWidth: Dp = 1.dp,
    showYAxisUnit: Boolean = false,
    yMax: Int = 25, // Y-axis maximum (Only need B type)
    yUnit: Int = 5, // Y-axis unit
    barWidth: Dp = 20.dp,
    yTextStyle: TextStyle = TextStyle(),
    barTextStyle: TextStyle = TextStyle(), //(Only need A type)
    color: List<Color> = listOf(Color.Yellow, Color.Cyan),
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    list: List<GraphItem>,
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        /** Dashed line **/
        Box(Modifier.align(Alignment.BottomEnd)) {
            Column(
                horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Bottom
            ) {
                val pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 2f), 0f)
                val drawLineColor = Color(0x1A000000)
                val range = yMax / yUnit
                var tempCount = yMax
                for (i in 0 until range) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(gridLineSpacing), verticalArrangement = Arrangement.Bottom
                    ) {
                        if (showYAxisUnit) {
                            Text(
                                text = tempCount.toString(),
                                style = yTextStyle,
                                textAlign = TextAlign.End,
                            )
                            tempCount -= yUnit
                        }

                        Canvas(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            drawLine(
                                color = drawLineColor,
                                start = Offset(0f, 0f),
                                end = Offset(size.width, 0f),
                                pathEffect = pathEffect,
                                strokeWidth = gridLineStrokeWidth.toPx()
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(gridLineSpacing), verticalArrangement = Arrangement.Bottom
                ) {
                    if (showYAxisUnit) {
                        Text(
                            modifier = Modifier.height(14.dp),
                            text = "0",
                            style = yTextStyle,
                            textAlign = TextAlign.End,
                        )
                    }
                    // Solid line
                    HorizontalDivider(thickness = gridLineStrokeWidth, color = Color(0x33000000))
                }
                Spacer(modifier = Modifier.height(gridLineSpacing))
            }
        }

        /**
         * graph
         */
        val graphScreenWidth = screenWidth - 120.dp
        val graphAllWidth =
            if (showYAxisUnit) Modifier.width(graphScreenWidth) else Modifier.fillMaxWidth()
        val graphHeight = Modifier.fillMaxHeight()
        val graphPadding = PaddingValues(start = 5.dp, end = 5.dp)

        Column(
            Modifier
                .then(graphAllWidth)
                .fillMaxHeight()
                .align(if (barChartType == BarChartType.A) Alignment.BottomCenter else Alignment.BottomEnd),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                Modifier
                    .then(graphAllWidth)
                    .then(graphHeight)
                    .padding(graphPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                if (barChartType == BarChartType.A) {
                    for (item in list) {
                        TypeAGraph(
                            item,
                            yUnit,
                            barWidth,
                            gridLineSpacing,
                            gridLineStrokeWidth,
                            color,
                            shape,
                            barTextStyle
                        )
                    }
                } else {
                    for (item in list) {
                        TypeBGraph(
                            item,
                            yUnit,
                            barWidth,
                            gridLineSpacing,
                            gridLineStrokeWidth,
                            color
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TypeAGraph(
    item: GraphItem,
    unit: Int,
    width: Dp,
    lineSpacing: Dp,
    lineDp: Dp = 1.dp,
    color: List<Color>,
    shape: RoundedCornerShape,
    barTextStyle: TextStyle = TextStyle(),
) {
    val size = item.size
    val heightGraph = ((size / unit) * lineSpacing) + ((size % unit) * 4.5.dp)
    Log.d("ejlee5", "heightGraph : $heightGraph")
    Log.d("ejlee5", "heightGraph : $lineSpacing")

    val gradient = Brush.verticalGradient(
        colors = color,
        startY = 0f,
        endY = (heightGraph.value / 2) * 5
    )

    Box(
        modifier = Modifier
            .width(width)
            .fillMaxHeight(),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(bottom = lineSpacing + lineDp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(heightGraph)
                        .background(gradient, shape)
                )
            }
        }

        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = lineSpacing + 11.dp),
                    text = size.toString(),
                    style = barTextStyle,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
private fun TypeBGraph(
    item: GraphItem,
    unit: Int,
    width: Dp,
    lineSpacing: Dp,
    lineDp: Dp = 1.dp,
    color: List<Color>,
) {
    val size = item.size
    val heightGraph = ((size / unit) * lineSpacing) + ((size % unit) * 4.5.dp)
    val gradient = Brush.verticalGradient(
        colors = color,
        startY = 0f,
        endY = 200f
    )
    Box(
        Modifier
            .width(width)
            .fillMaxHeight()
    ) {
        Column(
            Modifier
                .width(width)
                .fillMaxHeight()
                .padding(bottom = lineSpacing + lineDp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(heightGraph)
                    .background(gradient)
            )
        }
    }
}

@Preview
@Composable
fun PreviewStudyResultDetailGraph() {
    BarCartWidget(
        list = listOf(
            GraphItem(25),
            GraphItem(3),
            GraphItem(13),
            GraphItem(30),
            GraphItem(20)
        )
    )
}