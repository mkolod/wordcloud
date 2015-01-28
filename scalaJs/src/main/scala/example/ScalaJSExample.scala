import org.scalajs.dom.{MessageEvent, WebSocket}

import scala.scalajs.js
import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $, JQueryEventObject}
import scala.scalajs.js.Function1
import scala.scalajs.js.annotation.JSExport
import scalatags.Text.TypedTag
import scalatags.Text.all._
import shared.SharedMessages
import org.scalajs.d3


object ScalaJSExample extends js.JSApp {

  implicit def typeTagToString(t: TypedTag[String]) = t.toString

  def main(): Unit = {

    val d3Ref = js.Dynamic.global.d3
    val body = "body"
    val boxId = "#d3_box"
    val twitterButton = "#twitter_button"
    val overlay = $(div(id := "overlay"))
    val d3Box = $(div(id := "d3_box"))
    var wordBuffer = js.Array[String]()
    overlay.append(d3Box)
    $(body).append(overlay)

    val dynamicLiteral = new Function1[String, js.Dynamic] {

      def apply(d: String): js.Dynamic = js.Dynamic.literal(text = d, size = 10 + math.random * 90)
    }

    def randomize = new js.Function1[js.Array[String], js.Object] {
      def apply(arr: js.Array[String]): js.Object =
        arr.map { dynamicLiteral }
    }

    def zero90Or180(): js.Number = scala.util.Random.nextInt(3) * 90

    type WithSize = {
      def size: Double
    }

    type WithCoordinates = {
      def rotate: Double
      def x: Double
      def y: Double
    }

    type WithText = {
      def text: String
    }

    def size() = new Function1[WithSize, Double] {
      def apply(d: WithSize): Double = d.size
    }

    val trans = new js.Function1[WithCoordinates, String] {
      def apply(d: WithCoordinates): String =
        s"translate([ ${d.x}, ${d.y}])rotate(${d.rotate})"
    }

    val withTextFun = new Function1[WithText, String] {
      def apply(w: WithText): String = w.text
    }

    def draw = new js.Function1[js.Array[String], Unit] {

      def apply(words: js.Array[String]): Unit = {

        val width = $(boxId).width()
        val height = $(boxId).height()

//        val fill = js.Dynamic.global.d3.scale.category20()

        d3Ref.select(boxId).append("svg")
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
          .style("fontSize")("font-size",  "20px") // dynamic size
          .style("fontFamily")("font-family", "Impact")
//          .style("fill")("fill", "") // function ( d, i ) { return fill ( i ) ; }
          .attr("textAnchor")("text-anchor", "middle")
          .attr("transform")("transform", trans)
          .applyDynamic("text")(withTextFun)
      }
    }

    val showWordCloud: js.Function1[Unit, Unit] = new Function1[Unit, Unit] {

      override def apply(arg1: Unit): Unit = {


        // update cloud size
        val cloud = d3Ref.layout.cloud.apply()
        cloud.applyDynamic("size")(js.Array[Double]($(boxId).height(), $(boxId).height()))  //size("cloudSize")(js.Array[Double]($(boxId).height(), $(boxId).height()))
        cloud.applyDynamic("words")(randomize(wordBuffer))
        cloud.applyDynamic("padding")(5)
        cloud.applyDynamic("rotate")(zero90Or180)
        cloud.applyDynamic("font")("Impact")
        cloud.applyDynamic("fontSize")(20)
        cloud.applyDynamic("on")("end", draw)
        cloud.applyDynamic("start")()

      }
    }

    import js.JSConverters._
    var ws: dom.WebSocket = null

    val handleMessage: js.Function1[MessageEvent, Unit] = (msg: MessageEvent) => {
      wordBuffer = wordBuffer.concat(msg.data.asInstanceOf[String].split(" "))
      if (wordBuffer.length >= 300) {
        showWordCloud()
        wordBuffer = new js.Array[String]()
      }
    }

    $(twitterButton).click(new js.Function1[JQueryEventObject, Unit] {
      def apply(event: JQueryEventObject): Unit = {
        event.preventDefault()
        ws = new dom.WebSocket("ws://localhost:9000/ws/twitter")
        ws.onmessage = handleMessage
        overlay.show()
        d3Box.css("display", "block")
      }
    })

    $(overlay).click(new js.Function1[JQueryEventObject, Unit] {
      def apply(event: JQueryEventObject): Unit = {
        ws.close()
        ws = null
        d3Box.hide()
        overlay.hide()
      }
    })

  }
}