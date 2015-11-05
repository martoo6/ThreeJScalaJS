package main

import main.lib._

import scala.scalajs.js._
import scala.scalajs.js.annotation.JSExport

//Some IDEs will autmatically delete import java.lang.Math._   , try to keep it
//########################
//########################

@JSExport
class ThreeJSApp16 extends JSApp with BasicCanvas with DrawingUtils with SimplexNoise{
  Setup._2D.LeftBottom.asCanvas.noClear.withStats.antialiasing
  RectMode.leftBottom

  val perlin = Simplex(-15,15)


  val dots = (0 to 1000).map{ i=>
    (random2D ,Palette.iDemandPancake.getRandom)
  }

  val circles = point2(dots:_*).geometry

  def render():Unit = {
    circles.verticesNeedUpdate=true
    circles.vertices.foreach{ c=>
      //val pos = c.position
      //pos.add(new Vector3(0,5,0).applyAxisAngle((0,0,1),perlin.noise(c.position.x*0.01, c.position.y*0.01, frameCount*0.002)))

      c.add(vecXYAngle(perlin.noise(c.x*0.01, c.y*0.01, frameCount*0.002)))
      if(c.x < 0) c.add((width,0,0))
      if(c.x > width) c.add((-width,0,0))
      if(c.y < 0) c.add((0,height,0))
      if(c.y > height) c.add((0,-height,0))
    }
  }


}