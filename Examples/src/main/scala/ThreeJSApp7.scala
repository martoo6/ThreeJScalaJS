import main.lib._

import scala.scalajs.js.annotation.JSExport

/**
 * Antialias
 * Palette
 * Canvas Mode
 * No Clear
 * RectMode
 * Custom Perlin
 */

@JSExport
class ThreeJSApp7 extends BasicCanvas with DrawingUtils with PerlinNoise{
  Setup._2D.LeftBottom.asCanvas.noClear.withStats.antialiasing
  RectMode.leftBottom

  val perlin = Perlin(-15,15)

  val circles = (0 to 1000).map{ i=>
    circle(random2D, 5, Palette.iDemandPancake.getRandom)
  }

  def render():Unit = {
    circles.foreach{ c=>
      val pos = c.position
      //pos.add(new Vector3(0,5,0).applyAxisAngle((0,0,1),perlin.noise(c.position.x*0.01, c.position.y*0.01, frameCount*0.002)))
      pos.add(vecXYAngle(perlin.noise(c.position.x*0.01, c.position.y*0.01, frameCount*0.002)))
      if(pos.x < 0) pos.add((width,0,0))
      if(pos.x > width) pos.add((-width,0,0))
      if(pos.y < 0) pos.add((0,height,0))
      if(pos.y > height) pos.add((0,-height,0))
    }
  }
}
