import scalafx.Includes.handle
import scalafx.beans.property.StringProperty
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.VBox

object WordAdding {

  private val label = Label("Add new word to the dictionary:")

  private val word = new TextField {
    promptText = "New word"
    maxWidth = 200
  }

  private val meaning = new TextField {
    promptText = "Description"
    maxWidth = 200
  }

  private val add = new Button {
    text = "Add word"
    maxWidth = Double.MaxValue
    onAction = handle {
      Dictionary.addWord(word.getText, meaning.getText)
      word.clear()
      meaning.clear()
    }
  }

  private val back = new Button {
    text = "Go back"
    maxWidth = Double.MaxValue
    onAction = handle { NewScene(StringProperty("Vocabulary Builder"), Main.getContent) }
  }

  def getContent: VBox = new VBox {
    spacing = 10
    padding = Insets(150)
    children = List(label, word, meaning, add, back)
  }

}
