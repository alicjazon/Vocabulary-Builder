import scalafx.Includes.handle
import scalafx.beans.property.StringProperty
import scalafx.geometry.Insets
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control._
import scalafx.scene.layout.VBox

import scala.util.Random

object Session {

    private val label = Label("Choose the best definition:")

    private def getRandomSeq = Random.shuffle[Int, IndexedSeq](Dictionary.getKeys.indices)

    private val tog = new ToggleGroup()

    private def option(number: Int) = new RadioButton {
      maxWidth = 450
      maxHeight = 50
      val meaning: String = Dictionary.getMeaning(Dictionary.getKeys(number))
      userData = meaning
      text = meaning
      toggleGroup = tog
    }

    private var iter: Int = 1

    private def next(number: Int, word: String) = new Button {
      text = "Select"
      maxWidth = Double.MaxValue
      onAction = handle {
        if (tog.getSelectedToggle != null) {
          if (Dictionary.getMeaning(word).eq(tog.getSelectedToggle.getUserData.toString)) {
            val alert = new Alert(AlertType.Information) {
              contentText = "Correct!"
              buttonTypes = Seq(ButtonType.OK)
            }
            val result = alert.showAndWait()

            result match {
              case Some(ButtonType.OK) =>
                if (iter < number) {
                  iter += 1
                  NewScene(StringProperty("Training session"), getContent(number))
                }
                else NewScene(StringProperty("Vocabulary Builder"), Main.getContent)
              case _ =>
            }
          }
          else
            new Alert(AlertType.Error, "Incorrect answer").showAndWait()
        }
      }

    }

    private val cancel = new Button {
      text = "Cancel"
      maxWidth = Double.MaxValue
      onAction = handle { NewScene(StringProperty("Vocabulary Builder"), Main.getContent) }
    }

    def getContent(number: Int): VBox = new VBox {
      spacing = 10
      padding = Insets(100)
      val randomWords: IndexedSeq[Int] = getRandomSeq
      val wordLabel = Label(Dictionary.getKeys(randomWords(0)))
      val options: List[RadioButton] = Random.shuffle(List(option(randomWords(1)), option(randomWords(2)),
        option(randomWords(3)), option(randomWords(0))))
      children = List(label, wordLabel, options(0), options(1), options(2), options(3),
        next(number, Dictionary.getKeys(randomWords(0))), cancel)
    }

}
