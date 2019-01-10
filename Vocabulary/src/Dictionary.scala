import scalafx.scene.control.{Alert, ButtonType}
import scalafx.scene.control.Alert.AlertType
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths, StandardOpenOption}

object Dictionary {

  private var dictionary = Map[String, String]()

  def saveDictionary(): Unit  = {
    Files.write(Paths.get("dictionary.txt"), "".getBytes(StandardCharsets.UTF_8))
    for ((k, v) <- dictionary)
      Files.write(Paths.get("dictionary.txt"), (k + ";" + v + "\n").getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND)
  }

  def readDictionary(): Unit  =
      Files.readAllLines(Paths.get("dictionary.txt")).forEach(addLine(_))

  def addLine(line: String): Unit = {
    val values = line.split(";")
    dictionary += (values(0) -> values(1))
  }

  def addWord(word: String, meaning: String): Unit =
    if (dictionary contains word)
      new Alert(AlertType.Error, "This word already exists in the dictionary").showAndWait()
    else {
      val alert = new Alert(AlertType.Information) {
      contentText = s"Do you want to add word to the dictionary? \n Word: $word \n Meaning: $meaning"
      buttonTypes = Seq(ButtonType.Apply, ButtonType.Cancel)
      }
      val result = alert.showAndWait()

      result match {
        case Some(ButtonType.Apply) => dictionary += (word -> meaning)
        case _ =>
      }
    }

  def removeWord(word: String): Unit = dictionary -= word

  def getDictionary: Map[String, String] = dictionary

  def getMeaning(word: String): String =
    if (dictionary contains word)
      dictionary(word)
    else
      "This word doesn't exist in the dictionary"

  def getKeys: Array[String] = dictionary.keySet.toArray

}
