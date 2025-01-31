# BarCart
   A Type    |       B Type        |
:-------------------------:|:-------------------------:
 ![image](https://github.com/user-attachments/assets/af8c2ffc-bc34-4c79-b0d0-20e938959a99)  |  ![image](https://github.com/user-attachments/assets/fafaf335-9df2-4f90-b5f7-c0273bf4b22a)

   A Type - showYAxisUnit is true    |       B Type - showYAxisUnit is true        |
:-------------------------:|:-------------------------:
 ![image](https://github.com/user-attachments/assets/0d2f9151-ae5e-4574-b6ae-af66c0ea2654)  |   ![image](https://github.com/user-attachments/assets/ba7ce04d-bd06-4cad-91fb-487484c05de1)



  
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

