import Main.stage
import scalafx.application.JFXApp
import scalafx.beans.property.StringProperty
import scalafx.scene.Scene
import scalafx.scene.layout.Pane

class NewScene[T <: Pane](name: StringProperty, content: T) {

  val trainingScene = new Scene(content, 300, 200)
  stage = new JFXApp.PrimaryStage {
    title = name.name
    width = 600
    height = 450
    scene = trainingScene
  }
  stage.show
}

object NewScene {
  def apply[T <: Pane](title: StringProperty, content: T): NewScene[T] = new NewScene(title, content)
}
