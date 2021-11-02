import jetbrains.letsPlot.Geom
import org.kotlinmath.*

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.letsPlot


fun <A,B,C> compose(f:(B)->C,g:(A)->B):(A)->C = {x -> f(g(x))}

fun apply(f:(Complex)->Complex,n:Int):(Complex)->Complex {
 if (n==0){ return f} else { return compose(apply(f,n-1),f)}
}

fun f(z:Complex):Complex{
 val z1 = -0.4 + 0.6* I
 return z*z + z1
}
 fun main()
 {


  var z:Complex
  var flag:Boolean = true
  var n = 1
  var sum:Complex
  var xs = arrayListOf<Int>()
  var ys = arrayListOf<Int>()
  var value = arrayListOf<Int>()
  var tempMap: Array<Array<Int>> = Array(400, { Array(400,){0} })
  var numMap: Array<Array<Int>> = Array(400, { Array(400,){0} })

  for (i in 0..399)
  {
   for (j in 0..399)
   {
    numMap[i][j] = j + 1
   }
  }

  for (i in 0..399)
  {
   for (j in 0..399)
   {
    z = complex((i-200)/100,(j-200)/100)
    n = 0
    flag = true
    while (flag==true)
    {
     sum = apply(::f,n)(z)
     if(n == 150){
      tempMap[i][j] = 150
      flag = false
     }
     if(sum.mod > 2.0){
      flag = false
      tempMap[i][j]=n-1
     }
     n++
    }
   }
  }

  for (i in 0..399)
  {
   for (j in 0..399){
    print(tempMap[i][j])
    print(" ")
   }
   println()
  }

  for (i in 0..399)
  {
   for (j in 0..399){
    xs.add(i)
    ys.add(j)
    value.add(tempMap[i][j])
   }
  }

  val data_3 = mapOf<String, Any>("xs" to xs,"ys" to ys , "zs" to value)
  val fig_3 = letsPlot(data_3) +
          // something wring with `geomRaster'?
          geomTile() {x = "xs"; y = "ys"; fill = "zs"}

  ggsave(fig_3, "plot_3.png")


 }
