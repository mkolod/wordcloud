import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.jquery.{ jQuery => $ }
import scalatags.Text.TypedTag
import scalatags.Text.all._
import shared.SharedMessages
import org.scalajs.d3

object ScalaJSExample extends js.JSApp {

  implicit def typeTagToString(t: TypedTag[String]) = t.toString

  def main(): Unit = {

    val d3 = js.Dynamic.global.d3
    val body = "body"
    val overlay = $(div(id := "overlay"))
    val d3Box = $(div(id := "d3_box"))
    val wordBuffer = js.Array[String]()
    overlay.append(d3Box)
    $(body).append(overlay)

    def showWordCloud(): Unit = {

      val fill = js.Dynamic.global.d3.scale.category20()

      val boxId = "#d3_box"

      //      def randomize(arr: js.Array[String]): js.Object =
      //        arr.map[String, js.Object] {
      //          case d: String => js.Dynamic.literal(text = d, size = 10 + math.random * 90)
      //      }

      def zero90Or180(): Int = scala.util.Random.nextInt(3) * 90

      // update cloud size
      d3.layout.cloud()
        .size("cloudSize")(js.Array[Double]($(boxId).height(), $(boxId).height()))
        //        .words("randomizeWords")(randomize(wordBuffer))
        .padding("padding")(5)
        .rotate("rotateUpTo90Degrees")(zero90Or180)
        .font("font")("Impact")
        //        .fontSize("fontSize"){ case d: Any { def size: Double } => d.size}
        .on("drawAtTheEnd")("end", draw _)
        .start()

      def draw(words: js.Array[String]): Unit = {

        val width = $(boxId).width()
        val height = $(boxId).height()

        d3.select(boxId).append("svg")
          .attr("width")("width", "100%")
          .attr("height")("height", "100%")
          .attr("viewBox")("viewBox", s"0 0 ${math.min(width, height)} ${math.min(width, height)}")
          .attr("aspectRatio")("preserveAspectRatio", "xMidYMid") // not xMinYMin
          .append("g")("g")
          .attr("transform", s"translate(${math.min(width, height) / 2}, ${math.min(width, height) / 2}")
          .selectAll("text")("text")
          .data("words")(words)
          .enter("empty")()
          .append("text")("text")
          .style("fontSize")("font-size", "") //function ( d ) { return d.size + "px" ; } )
          .style("fontFamily")("font-family", "Impact")
          .style("fill")("fill", "") // function ( d, i ) { return fill ( i ) ; }
          .attr("textAnchor")("text-anchor", "middle")
          .attr("transform")("transform", "") /*  function ( d ) {return "translate(" +[ d.x, d.y ] + ")rotate(" + d.rotate + ")" ; */
          .text("text")("") /*  ( function ( d ) { return d.text ; } ) ; */

      }
    }
  }
}

