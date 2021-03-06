import main.lib._

import scala.scalajs.js.annotation.JSExport

//Some IDEs will autmatically delete import java.lang.Math._   , try to keep it
//########################

@JSExport
class ThreeJSApp11 extends BasicCanvas with PerlinNoise with DrawingUtils{

  Setup._3D.Center.asScene.noClear.withStats.withControls

  addHemisphereLight(0xFFFFFF, 0x05F5F5, 1.0)
  addDirectionalLight(0xFFFFFF, 0.4, (0,1,0)).target.lookAt(center)

  val step = 2
  val cant = 45

  val materials = Palette.mellonBallSurprise.colors.map(_.materializeP(THREE.FrontSide))

  val cubes = for{
    x <- -cant/2 to cant/2
    y <- -cant/2 to cant/2
  }
    yield{
      cube((x*step,-250,y*step),step, materials(rand(materials.size).toInt))
    }



  def render():Unit = {
    cubes.foreach { c =>
      val pos = c.position
      val n = Simplex.noise(pos.x * 0.01, pos.z * 0.01, frameCount * 0.01)
      pos.setY(map(n, -1, 1, 0, 100))
    }
  }


}
