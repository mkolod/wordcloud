
package views.html

import play.twirl.api._
import play.twirl.api.TemplateMagic._

import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._

/**/
object linkResult extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template2[service.DemoUser,RequestHeader,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(current: service.DemoUser)(implicit request: RequestHeader):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {

Seq[Any](format.raw/*1.62*/("""

    """),_display_(/*3.6*/main("Welcome to Nitro")/*3.30*/ {_display_(Seq[Any](format.raw/*3.32*/("""


"""),format.raw/*6.1*/("""<!-- TODO: move the CSS out of here -->
<style>
  #overlay """),format.raw/*8.12*/("""{"""),format.raw/*8.13*/("""
    """),format.raw/*9.5*/("""background:rgba(0,0,0,0.7);
    width:100%;
    height:100%;
    position:absolute;
    top:0;
    left:0;
    display:none;
    margin: auto;
    text-align:center;
  """),format.raw/*18.3*/("""}"""),format.raw/*18.4*/("""

  """),format.raw/*20.3*/("""#d3_box """),format.raw/*20.11*/("""{"""),format.raw/*20.12*/("""
      """),format.raw/*21.7*/("""background:rgba(255, 255, 255, 1.0);
      width: 75%;
      height: 75%;
      position: relative;
      display: none;
      top: 50%;
      transform: translateY(-50%);
      margin: auto;
      text-align: center;
  """),format.raw/*30.3*/("""}"""),format.raw/*30.4*/("""
"""),format.raw/*31.1*/("""</style>

<div class="page-header">
    <h2><img src=""""),_display_(/*34.20*/current/*34.27*/.main.avatarUrl.getOrElse("")),format.raw/*34.56*/("""" alt=""""),_display_(/*34.64*/current/*34.71*/.main.firstName.getOrElse("")),format.raw/*34.100*/("""" width="40px" height="40px"/> Hey """),_display_(/*34.136*/current/*34.143*/.main.firstName.getOrElse("")),format.raw/*34.172*/("""</h2>
</div>

<div class="clearfix">
    <h2>You are linked to the following accounts:</h2>

    """),_display_(/*40.6*/current/*40.13*/.identities.map/*40.28*/ { linked =>_display_(Seq[Any](format.raw/*40.40*/("""
        """),format.raw/*41.9*/("""<h2>"""),_display_(/*41.14*/linked/*41.20*/.providerId),format.raw/*41.31*/("""
        """),format.raw/*42.9*/("""</h2>
        <ul>
            <li>User Id: """),_display_(/*44.27*/linked/*44.33*/.userId),format.raw/*44.40*/("""</li>
            <li>Fist name: """),_display_(/*45.29*/linked/*45.35*/.firstName.getOrElse("")),format.raw/*45.59*/("""</li>
            <li>Last name: """),_display_(/*46.29*/linked/*46.35*/.lastName.getOrElse("")),format.raw/*46.58*/("""</li>
            <li>Full name: """),_display_(/*47.29*/linked/*47.35*/.fullName.getOrElse("")),format.raw/*47.58*/("""</li>
            <li>Logged in from: """),_display_(/*48.34*/linked/*48.40*/.providerId),format.raw/*48.51*/("""</li>
            <li>Email: """),_display_(/*49.25*/linked/*49.31*/.email.map/*49.41*/ { email =>_display_(Seq[Any](format.raw/*49.52*/(""" """),_display_(/*49.54*/email),format.raw/*49.59*/(""" """)))}/*49.61*/.getOrElse("Not Available")),format.raw/*49.88*/("""</li>
            <li>Authentication method: """),_display_(/*50.41*/linked/*50.47*/.authMethod),format.raw/*50.58*/("""</li>
        </ul>

        """),_display_(/*53.10*/linked/*53.16*/.oAuth1Info.map/*53.31*/ { info =>_display_(Seq[Any](format.raw/*53.41*/("""
        """),format.raw/*54.9*/("""<h4>OAuth1 Info</h4>

        <ul>
            <li>First 4 letters of token: """),_display_(/*57.44*/info/*57.48*/.token.take(4)),format.raw/*57.62*/("""</li>
            <li>First 4 letters of secret: """),_display_(/*58.45*/info/*58.49*/.secret.take(4)),format.raw/*58.64*/("""</li>
        </ul>
        """)))}),format.raw/*60.10*/("""

        """),_display_(/*62.10*/linked/*62.16*/.oAuth2Info.map/*62.31*/ { info =>_display_(Seq[Any](format.raw/*62.41*/("""
        """),format.raw/*63.9*/("""<h4>OAuth2 Info</h4>

        <ul>
            <li>Access Token: """),_display_(/*66.32*/info/*66.36*/.accessToken),format.raw/*66.48*/("""</li>
            """),_display_(/*67.14*/info/*67.18*/.tokenType.map/*67.32*/ { t =>_display_(Seq[Any](format.raw/*67.39*/(""" """),format.raw/*67.40*/("""<li>Token Type: """),_display_(/*67.57*/t),format.raw/*67.58*/("""</li> """)))}),format.raw/*67.65*/("""
            """),_display_(/*68.14*/info/*68.18*/.expiresIn.map/*68.32*/ { exp =>_display_(Seq[Any](format.raw/*68.41*/(""" """),format.raw/*68.42*/("""<li>Expires in: """),_display_(/*68.59*/exp),format.raw/*68.62*/(""" """),format.raw/*68.63*/("""seconds</li>""")))}),format.raw/*68.76*/("""
            """),_display_(/*69.14*/info/*69.18*/.refreshToken.map/*69.35*/ { rt =>_display_(Seq[Any](format.raw/*69.43*/(""" """),format.raw/*69.44*/("""<li>Refresh Token: """),_display_(/*69.64*/rt),format.raw/*69.66*/("""</li>""")))}),format.raw/*69.72*/("""
        """),format.raw/*70.9*/("""</ul>
        """)))}),format.raw/*71.10*/("""
        """),format.raw/*72.9*/("""<br>
        """),_display_(/*73.10*/if(linked.providerId == "twitter")/*73.44*/ {_display_(Seq[Any](format.raw/*73.46*/("""
          """),format.raw/*74.11*/("""<button type="button" class="btn" id="twitter_button">Click for """),_display_(/*74.76*/linked/*74.82*/.providerId),format.raw/*74.93*/(""" """),format.raw/*74.94*/("""visualization</button>
          <!-- TODO: move JS out of here, rewrite using Scala.js (also, depend on WebJars) -->
          <script src="http://code.jquery.com/jquery-1.11.0.min.js" type="text/javascript" charset="utf-8"></script>
          <!-- TODO: move this out of here, depend on WebJars -->
          <script src="http://www.jasondavies.com/d3.min.js" type="text/javascript"></script>
          <!-- TODO: move this out of here, depend on WebJars -->
          <script src="http://www.jasondavies.com/wordcloud/d3.layout.cloud.js" type="text/javascript"></script>
          <script>
              var $overlay = $('<div id="overlay"></div>');
              var $d3Box = $('<div id="d3_box"></div>');
              var wordBuffer = [];

              $overlay.append($d3Box);
              $("body").append($overlay);

              var showWordCloud = function() """),format.raw/*89.46*/("""{"""),format.raw/*89.47*/("""

                  """),format.raw/*91.19*/("""var fill = d3.scale.category20 ( ) ;
                  d3.layout.cloud ( ).size ([ $("#d3_box" ).width(), $("#d3_box" ).height() ])   //$("#d3_box" ).width(), $("#d3_box" ).height() ] )
                  .words ( wordBuffer.map ( function ( d ) """),format.raw/*93.60*/("""{"""),format.raw/*93.61*/("""
                  """),format.raw/*94.19*/("""return """),format.raw/*94.26*/("""{"""),format.raw/*94.27*/(""" """),format.raw/*94.28*/("""text : d, size : 10 + Math.random ( ) * 90 """),format.raw/*94.71*/("""}"""),format.raw/*94.72*/(""" """),format.raw/*94.73*/(""";
                  """),format.raw/*95.19*/("""}"""),format.raw/*95.20*/(""" """),format.raw/*95.21*/(""") )
                  .padding ( 5 )
                  .rotate ( function ( ) """),format.raw/*97.42*/("""{"""),format.raw/*97.43*/(""" """),format.raw/*97.44*/("""return ~ ~ ( Math.random ( ) * 2 ) * 90 ; """),format.raw/*97.86*/("""}"""),format.raw/*97.87*/(""" """),format.raw/*97.88*/(""")
                  .font ( "Impact" )
                  .fontSize ( function ( d ) """),format.raw/*99.46*/("""{"""),format.raw/*99.47*/(""" """),format.raw/*99.48*/("""return d.size ; """),format.raw/*99.64*/("""}"""),format.raw/*99.65*/(""" """),format.raw/*99.66*/(""")
                  .on ( "end", draw )
                  .start ( ) ;

                  function draw ( words ) """),format.raw/*103.43*/("""{"""),format.raw/*103.44*/("""

                    """),format.raw/*105.21*/("""d3.select("#d3_box" ).selectAll("*").remove();


                      var width = $("#d3_box").width();
                      var height = $("#d3_box").height();

                      d3.select ( "#d3_box" ).append ( "svg" )
                      .attr ( "width", '100%')
                      .attr ( "height",'100%')
                      .attr("viewBox", '0 0 ' + Math.min(width, height) + " " + Math.min(width, height))
                      .attr("preserveAspectRatio", "xMidYMid") // not xMinYMin
                      .append ( "g" )
                      // translate(600, 300)
                      .attr ( "transform", "translate(" + Math.min(width,height) / 2 + "," + Math.min(width,height) / 2 + ")")
                      .selectAll ( "text" )
                      .data ( words )
                      .enter ( ).append ( "text" )
                      .style ( "font-size", function ( d ) """),format.raw/*122.60*/("""{"""),format.raw/*122.61*/(""" """),format.raw/*122.62*/("""return d.size + "px" ; """),format.raw/*122.85*/("""}"""),format.raw/*122.86*/(""" """),format.raw/*122.87*/(""")
                      .style ( "font-family", "Impact" )
                      .style ( "fill", function ( d, i ) """),format.raw/*124.58*/("""{"""),format.raw/*124.59*/(""" """),format.raw/*124.60*/("""return fill ( i ) ; """),format.raw/*124.80*/("""}"""),format.raw/*124.81*/(""" """),format.raw/*124.82*/(""")
                      .attr ( "text-anchor", "middle" )
                      .attr ( "transform", function ( d ) """),format.raw/*126.59*/("""{"""),format.raw/*126.60*/("""
                      """),format.raw/*127.23*/("""return "translate(" +[ d.x, d.y ] + ")rotate(" + d.rotate + ")" ;
                      """),format.raw/*128.23*/("""}"""),format.raw/*128.24*/(""" """),format.raw/*128.25*/(""")
                      .text ( function ( d ) """),format.raw/*129.46*/("""{"""),format.raw/*129.47*/(""" """),format.raw/*129.48*/("""return d.text ; """),format.raw/*129.64*/("""}"""),format.raw/*129.65*/(""" """),format.raw/*129.66*/(""") ;
                  """),format.raw/*130.19*/("""}"""),format.raw/*130.20*/("""
              """),format.raw/*131.15*/("""}"""),format.raw/*131.16*/(""";

                  /*
                  var svg = d3.select('.chart-container').append("svg")
    .attr("width", '100%')
    .attr("height", '100%')
    .attr('viewBox','0 0 '+Math.min(width,height)+' '+Math.min(width,height))
    .attr('preserveAspectRatio','xMinYMin')
    .append("g")
    .attr("transform", "translate(" + Math.min(width,height) / 2 + "," + Math.min(width,height) / 2 + ")");
                   */

              var ws = null;
              $("#twitter_button").click(function(event) """),format.raw/*144.58*/("""{"""),format.raw/*144.59*/("""
                """),format.raw/*145.17*/("""event.preventDefault();
                ws = new WebSocket("ws://localhost:9000/ws/twitter");
                ws.onmessage = function (event) """),format.raw/*147.49*/("""{"""),format.raw/*147.50*/("""

                """),format.raw/*149.17*/("""wordBuffer = wordBuffer.concat(event.data.split(" "));
                    if (wordBuffer.length >= 300) """),format.raw/*150.51*/("""{"""),format.raw/*150.52*/("""
                      """),format.raw/*151.23*/("""showWordCloud();
                      wordBuffer = [];
                    """),format.raw/*153.21*/("""}"""),format.raw/*153.22*/("""
                    """),format.raw/*154.21*/("""console.log(event.data + "\n");
                """),format.raw/*155.17*/("""}"""),format.raw/*155.18*/(""";
                $overlay.show();
                $d3Box.css('display','block');  //.show();

              """),format.raw/*159.15*/("""}"""),format.raw/*159.16*/(""");

              $overlay.click(function()"""),format.raw/*161.40*/("""{"""),format.raw/*161.41*/("""
                """),format.raw/*162.17*/("""ws.close();
                ws = null;
                $d3Box.hide();
                $overlay.hide();
              """),format.raw/*166.15*/("""}"""),format.raw/*166.16*/(""");



          </script>
        """)))}),format.raw/*171.10*/("""
        """),format.raw/*172.9*/("""<hr>
    """)))}),format.raw/*173.6*/("""
"""),format.raw/*174.1*/("""</div>

<a class="btn" href="/">Ok</a>
""")))}))}
  }

  def render(current:service.DemoUser,request:RequestHeader): play.twirl.api.HtmlFormat.Appendable = apply(current)(request)

  def f:((service.DemoUser) => (RequestHeader) => play.twirl.api.HtmlFormat.Appendable) = (current) => (request) => apply(current)(request)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Tue Jan 27 06:51:57 PST 2015
                  SOURCE: /Users/mkolodziej/src/wordcloud/playApp/app/views/linkResult.scala.html
                  HASH: c4e0b2961d496461eee174234d84efede03e2423
                  MATRIX: 534->1|682->61|714->68|746->92|785->94|814->97|900->156|928->157|959->162|1154->330|1182->331|1213->335|1249->343|1278->344|1312->351|1559->571|1587->572|1615->573|1697->628|1713->635|1763->664|1798->672|1814->679|1865->708|1929->744|1946->751|1997->780|2121->878|2137->885|2161->900|2211->912|2247->921|2279->926|2294->932|2326->943|2362->952|2434->997|2449->1003|2477->1010|2538->1044|2553->1050|2598->1074|2659->1108|2674->1114|2718->1137|2779->1171|2794->1177|2838->1200|2904->1239|2919->1245|2951->1256|3008->1286|3023->1292|3042->1302|3091->1313|3120->1315|3146->1320|3167->1322|3215->1349|3288->1395|3303->1401|3335->1412|3392->1442|3407->1448|3431->1463|3479->1473|3515->1482|3620->1560|3633->1564|3668->1578|3745->1628|3758->1632|3794->1647|3854->1676|3892->1687|3907->1693|3931->1708|3979->1718|4015->1727|4108->1793|4121->1797|4154->1809|4200->1828|4213->1832|4236->1846|4281->1853|4310->1854|4354->1871|4376->1872|4414->1879|4455->1893|4468->1897|4491->1911|4538->1920|4567->1921|4611->1938|4635->1941|4664->1942|4708->1955|4749->1969|4762->1973|4788->1990|4834->1998|4863->1999|4910->2019|4933->2021|4970->2027|5006->2036|5052->2051|5088->2060|5129->2074|5172->2108|5212->2110|5251->2121|5343->2186|5358->2192|5390->2203|5419->2204|6320->3077|6349->3078|6397->3098|6670->3343|6699->3344|6746->3363|6781->3370|6810->3371|6839->3372|6910->3415|6939->3416|6968->3417|7016->3437|7045->3438|7074->3439|7180->3517|7209->3518|7238->3519|7308->3561|7337->3562|7366->3563|7478->3647|7507->3648|7536->3649|7580->3665|7609->3666|7638->3667|7781->3781|7811->3782|7862->3804|8798->4711|8828->4712|8858->4713|8910->4736|8940->4737|8970->4738|9115->4854|9145->4855|9175->4856|9224->4876|9254->4877|9284->4878|9429->4994|9459->4995|9511->5018|9628->5106|9658->5107|9688->5108|9764->5155|9794->5156|9824->5157|9869->5173|9899->5174|9929->5175|9980->5197|10010->5198|10054->5213|10084->5214|10620->5721|10650->5722|10696->5739|10867->5881|10897->5882|10944->5900|11078->6005|11108->6006|11160->6029|11265->6105|11295->6106|11345->6127|11422->6175|11452->6176|11590->6285|11620->6286|11692->6329|11722->6330|11768->6347|11914->6464|11944->6465|12011->6500|12048->6509|12089->6519|12118->6520
                  LINES: 19->1|22->1|24->3|24->3|24->3|27->6|29->8|29->8|30->9|39->18|39->18|41->20|41->20|41->20|42->21|51->30|51->30|52->31|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|55->34|61->40|61->40|61->40|61->40|62->41|62->41|62->41|62->41|63->42|65->44|65->44|65->44|66->45|66->45|66->45|67->46|67->46|67->46|68->47|68->47|68->47|69->48|69->48|69->48|70->49|70->49|70->49|70->49|70->49|70->49|70->49|70->49|71->50|71->50|71->50|74->53|74->53|74->53|74->53|75->54|78->57|78->57|78->57|79->58|79->58|79->58|81->60|83->62|83->62|83->62|83->62|84->63|87->66|87->66|87->66|88->67|88->67|88->67|88->67|88->67|88->67|88->67|88->67|89->68|89->68|89->68|89->68|89->68|89->68|89->68|89->68|89->68|90->69|90->69|90->69|90->69|90->69|90->69|90->69|90->69|91->70|92->71|93->72|94->73|94->73|94->73|95->74|95->74|95->74|95->74|95->74|110->89|110->89|112->91|114->93|114->93|115->94|115->94|115->94|115->94|115->94|115->94|115->94|116->95|116->95|116->95|118->97|118->97|118->97|118->97|118->97|118->97|120->99|120->99|120->99|120->99|120->99|120->99|124->103|124->103|126->105|143->122|143->122|143->122|143->122|143->122|143->122|145->124|145->124|145->124|145->124|145->124|145->124|147->126|147->126|148->127|149->128|149->128|149->128|150->129|150->129|150->129|150->129|150->129|150->129|151->130|151->130|152->131|152->131|165->144|165->144|166->145|168->147|168->147|170->149|171->150|171->150|172->151|174->153|174->153|175->154|176->155|176->155|180->159|180->159|182->161|182->161|183->162|187->166|187->166|192->171|193->172|194->173|195->174
                  -- GENERATED --
              */
          