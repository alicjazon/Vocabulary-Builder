import java.io.File
import java.nio.file.{Files, Paths}

import scalafx.application.JFXApp
import scalafx.geometry.Insets
import scalafx.scene.layout._
import scalafx.scene.control._
import scalafx.Includes._
import scalafx.beans.property.StringProperty

object Main extends JFXApp {

def getContent: AnchorPane = {
  val label = Label("VOCABULARY BUILDER")

  val add = new Button {
    text = "Add new word"
    maxWidth = Double.MaxValue
    onAction = handle { NewScene(StringProperty("Add new word"), WordAdding.getContent) }
  }

  val training = new Button {
    text = "Start training"
    maxWidth = Double.MaxValue
    onAction = handle { NewScene(StringProperty("Vocabulary training"), Training.getContent) }
  }

  val quit = new Button {
    text = "Quit"
    maxWidth = Double.MaxValue
    onAction = handle {
      new File("dictionary.txt").delete()
      Dictionary.saveDictionary()
      stage.close() }
  }

  AnchorPane.setTopAnchor(label, 20.0)
  AnchorPane.setLeftAnchor(label, 200.0)
  AnchorPane.setTopAnchor(add, 60.0)
  AnchorPane.setLeftAnchor(add, 200.0)
  AnchorPane.setTopAnchor(training, 100.0)
  AnchorPane.setLeftAnchor(training, 200.0)
  AnchorPane.setTopAnchor(quit, 140.0)
  AnchorPane.setLeftAnchor(quit, 200.0)

  new AnchorPane {
    padding = Insets(20)
    children = List(label, add, training, quit)
  }
}

  if(Files.exists(Paths.get("dictionary.txt"))) {
    Dictionary.readDictionary()
  }
  val mainScene = NewScene(StringProperty("Vocabulary Builder"), getContent)
}
