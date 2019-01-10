import scalafx.Includes.handle
import scalafx.beans.property.StringProperty
import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox

object Training {

  private val label = Label("Select training: ")
  private val label2 = Label("(if training is disabled, add more words to the dictionary)")

  private def trainButton(number: Int) = new Button {
    text = number + " words"
    maxWidth = Double.MaxValue
    onAction = handle { NewScene(StringProperty("Training session"), Session.getContent(number)) }
    if(Dictionary.getDictionary.size < number) disable = true
  }

  private val back = new Button {
    text = "Go back"
    maxWidth = Double.MaxValue
    onAction = handle { NewScene(StringProperty("Vocabulary Builder"), Main.getContent) }
  }

  def getContent: VBox = new VBox {
    spacing = 15
    padding = Insets(100)
    children = List(label, label2, trainButton(5), trainButton(10), trainButton(20), back)
  }

}
