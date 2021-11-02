import jetbrains.letsPlot.Geom
import org.kotlinmath.*

import jetbrains.letsPlot.export.ggsave
import jetbrains.letsPlot.geom.*
import jetbrains.letsPlot.label.labs
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
  var a: Double = -1.0
  var b: Double = 0.0

  var flag:Boolean = true
  var n = 0
  var sum:Complex
  var xs = arrayListOf<Double>()
  var ys = arrayListOf<Double>()
  var value = arrayListOf<Int>()
  




  while (a <=1)
  {


   while (b <=1)
   {
    xs.add(a)
    ys.add(b)
    z = complex(a,b)
    n = 0
    flag = true
    while (flag==true)
    {
     sum = apply(::f,n)(z)
     if (n==150) {
      value.add(150)
      flag = false
     }else
     {
      if(sum.mod > 2.0)
      {
       flag = false
       value.add(n)
      }
     }
     n++
    }
    b += 0.01
   }
   b = -1.0
   a += 0.01
  }




  val data_3 = mapOf<String, Any>("xs" to xs,"ys" to ys , "value" to value)
  val fig_3 = letsPlot(data_3) +

          geomTile( ) {x = "xs"; y = "ys"; fill = "value"; color = "value"} + labs(title = "Color map for Julia visualisation")

  ggsave(fig_3, "plot_3.png")


 }
