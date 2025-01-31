# BarCart
   A Type    |       B Type        |
:-------------------------:|:-------------------------:
 ![image](https://github.com/user-attachments/assets/f474fcef-142a-4ff7-a647-c8f8c91b5527)  |  ![image](https://github.com/user-attachments/assets/3b083eda-5c08-4af9-a58b-b0ededa0181a)

   A Type - showYAxisUnit is true    |       B Type - showYAxisUnit is true        |
:-------------------------:|:-------------------------:
https://github.com/user-attachments/assets/9ec295cd-6ead-4967-8308-dc04c5183010  |  https://github.com/user-attachments/assets/829610b8-c55d-4b65-8792-b5fa7dab1f91




  
# Getting Started / Installation

        dependencies {
                implementation("io.github.eunju-u/compose-bar-chart:1.0.1”)
        }




# Usage
+ BarCartWidget parameters
  
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
        }



    
# maven central repositot

https://central.sonatype.com/artifact/io.github.eunju-u/compose-bar-chart/1.0.1/dependencies

