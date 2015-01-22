
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
object index extends BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with play.twirl.api.Template3[securesocial.core.BasicProfile,RequestHeader,securesocial.core.RuntimeEnvironment[service.DemoUser],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(user: securesocial.core.BasicProfile)(implicit request: RequestHeader, env: securesocial.core.RuntimeEnvironment[service.DemoUser]):play.twirl.api.HtmlFormat.Appendable = {
      _display_ {import securesocial.core.IdentityProvider
import securesocial.core.IdentityProvider
import securesocial.core.providers.UsernamePasswordProvider
import securesocial.core.AuthenticationMethod._
import play.api.{Logger, Play}
import helper._
import play.api.Play

Seq[Any](format.raw/*1.134*/("""
"""),format.raw/*3.1*/("""
"""),format.raw/*10.1*/("""
"""),_display_(/*11.2*/main("Welcome to Nitro")/*11.26*/ {_display_(Seq[Any](format.raw/*11.28*/("""
"""),format.raw/*12.1*/("""<div class="page-header">
    <h2><img src=""""),_display_(/*13.20*/user/*13.24*/.avatarUrl.getOrElse("")),format.raw/*13.48*/("""" alt=""""),_display_(/*13.56*/user/*13.60*/.firstName.getOrElse("")),format.raw/*13.84*/("""" width="40px" height="40px"/> Welcome """),_display_(/*13.124*/user/*13.128*/.firstName.getOrElse("")),format.raw/*13.152*/("""</h2>
</div>

<div class="clearfix">
    <h2>User Details</h2>

    <ul>
        <li>User Id: """),_display_(/*20.23*/user/*20.27*/.userId),format.raw/*20.34*/("""</li>
        <li>Fist name: """),_display_(/*21.25*/user/*21.29*/.firstName.getOrElse("")),format.raw/*21.53*/("""</li>
        <li>Last name: """),_display_(/*22.25*/user/*22.29*/.lastName),format.raw/*22.38*/(""".</li>
        <li>Full name: """),_display_(/*23.25*/user/*23.29*/.fullName.getOrElse("")),format.raw/*23.52*/("""</li>
        <li>Logged in from: """),_display_(/*24.30*/user/*24.34*/.providerId),format.raw/*24.45*/("""</li>
        <li>Email: """),_display_(/*25.21*/user/*25.25*/.email.map/*25.35*/ { email =>_display_(Seq[Any](format.raw/*25.46*/(""" """),_display_(/*25.48*/email),format.raw/*25.53*/(""" """)))}/*25.55*/.getOrElse("Not Available")),format.raw/*25.82*/("""</li>
        <li>Authentication method: """),_display_(/*26.37*/user/*26.41*/.authMethod),format.raw/*26.52*/("""</li>
    </ul>

    """),_display_(/*29.6*/user/*29.10*/.oAuth1Info.map/*29.25*/ { info =>_display_(Seq[Any](format.raw/*29.35*/("""
        """),format.raw/*30.9*/("""<h2>OAuth1 Info</h2>

        <ul>
            <li>Token: """),_display_(/*33.25*/info/*33.29*/.token),format.raw/*33.35*/("""</li>
            <li>Secret: """),_display_(/*34.26*/info/*34.30*/.secret),format.raw/*34.37*/("""</li>
        </ul>
    """)))}),format.raw/*36.6*/("""

    """),_display_(/*38.6*/user/*38.10*/.oAuth2Info.map/*38.25*/ { info =>_display_(Seq[Any](format.raw/*38.35*/("""
        """),format.raw/*39.9*/("""<h2>OAuth2 Info</h2>

        <ul>
            <li>Access Token: """),_display_(/*42.32*/info/*42.36*/.accessToken),format.raw/*42.48*/("""</li>
            """),_display_(/*43.14*/info/*43.18*/.tokenType.map/*43.32*/ { t =>_display_(Seq[Any](format.raw/*43.39*/(""" """),format.raw/*43.40*/("""<li>Token Type: """),_display_(/*43.57*/t),format.raw/*43.58*/("""</li> """)))}),format.raw/*43.65*/("""
            """),_display_(/*44.14*/info/*44.18*/.expiresIn.map/*44.32*/ { exp =>_display_(Seq[Any](format.raw/*44.41*/(""" """),format.raw/*44.42*/("""<li>Expires in: """),_display_(/*44.59*/exp),format.raw/*44.62*/(""" """),format.raw/*44.63*/("""seconds</li>""")))}),format.raw/*44.76*/("""
            """),_display_(/*45.14*/info/*45.18*/.refreshToken.map/*45.35*/ { rt =>_display_(Seq[Any](format.raw/*45.43*/(""" """),format.raw/*45.44*/("""<li>Refresh Token: """),_display_(/*45.64*/rt),format.raw/*45.66*/("""</li>""")))}),format.raw/*45.72*/("""
        """),format.raw/*46.9*/("""</ul>
    """)))}),format.raw/*47.6*/("""
    """),format.raw/*48.5*/("""<hr>
    """),_display_(/*49.6*/user/*49.10*/.passwordInfo.map/*49.27*/ { info =>_display_(Seq[Any](format.raw/*49.37*/("""
        """),format.raw/*50.9*/("""<a class="btn" href=""""),_display_(/*50.31*/securesocial/*50.43*/.controllers.routes.PasswordChange.page.absoluteURL(IdentityProvider.sslEnabled)),format.raw/*50.123*/("""">Change Password</a>
    """)))}),format.raw/*51.6*/("""

    """),format.raw/*53.5*/("""<span>Link this account to

    """),_display_(/*55.6*/env/*55.9*/.providers.values.map/*55.30*/ { provider =>_display_(Seq[Any](format.raw/*55.44*/("""
        """),_display_(/*56.10*/if( provider.authMethod == OAuth1 || provider.authMethod == OAuth2 )/*56.78*/ {_display_(Seq[Any](format.raw/*56.80*/("""
            """),_display_(/*57.14*/defining( "images/providers/%s.png".format(provider.id) )/*57.71*/ { imageUrl =>_display_(Seq[Any](format.raw/*57.85*/("""
            """),format.raw/*58.13*/("""<a href=""""),_display_(/*58.23*/env/*58.26*/.routes.authenticationUrl(provider.id, Some("/link-result"))),format.raw/*58.86*/(""""> <img src=""""),_display_(/*58.100*/securesocial/*58.112*/.controllers.routes.Assets.at(imageUrl)),format.raw/*58.151*/(""""/></a>
            """)))}),format.raw/*59.14*/("""
        """)))}),format.raw/*60.10*/("""
    """)))}),format.raw/*61.6*/("""
    """),format.raw/*62.5*/("""</span>
    <hr>
    <a class="btn" href=""""),_display_(/*64.27*/securesocial/*64.39*/.controllers.routes.LoginPage.logout()),format.raw/*64.77*/("""">Logout</a>
</div>
""")))}))}
  }

  def render(user:securesocial.core.BasicProfile,request:RequestHeader,env:securesocial.core.RuntimeEnvironment[service.DemoUser]): play.twirl.api.HtmlFormat.Appendable = apply(user)(request,env)

  def f:((securesocial.core.BasicProfile) => (RequestHeader,securesocial.core.RuntimeEnvironment[service.DemoUser]) => play.twirl.api.HtmlFormat.Appendable) = (user) => (request,env) => apply(user)(request,env)

  def ref: this.type = this

}
              /*
                  -- GENERATED --
                  DATE: Sun Jan 25 15:19:20 PST 2015
                  SOURCE: /Users/mkolodziej/src/wordcloud/playApp/app/views/index.scala.html
                  HASH: 09b83ba06e944a43a55c0cd720e92fb253113d98
                  MATRIX: 598->1|1078->133|1105->177|1133->402|1161->404|1194->428|1234->430|1262->431|1334->476|1347->480|1392->504|1427->512|1440->516|1485->540|1553->580|1567->584|1613->608|1735->703|1748->707|1776->714|1833->744|1846->748|1891->772|1948->802|1961->806|1991->815|2049->846|2062->850|2106->873|2168->908|2181->912|2213->923|2266->949|2279->953|2298->963|2347->974|2376->976|2402->981|2423->983|2471->1010|2540->1052|2553->1056|2585->1067|2633->1089|2646->1093|2670->1108|2718->1118|2754->1127|2840->1186|2853->1190|2880->1196|2938->1227|2951->1231|2979->1238|3034->1263|3067->1270|3080->1274|3104->1289|3152->1299|3188->1308|3281->1374|3294->1378|3327->1390|3373->1409|3386->1413|3409->1427|3454->1434|3483->1435|3527->1452|3549->1453|3587->1460|3628->1474|3641->1478|3664->1492|3711->1501|3740->1502|3784->1519|3808->1522|3837->1523|3881->1536|3922->1550|3935->1554|3961->1571|4007->1579|4036->1580|4083->1600|4106->1602|4143->1608|4179->1617|4220->1628|4252->1633|4288->1643|4301->1647|4327->1664|4375->1674|4411->1683|4460->1705|4481->1717|4583->1797|4640->1824|4673->1830|4732->1863|4743->1866|4773->1887|4825->1901|4862->1911|4939->1979|4979->1981|5020->1995|5086->2052|5138->2066|5179->2079|5216->2089|5228->2092|5309->2152|5351->2166|5373->2178|5434->2217|5486->2238|5527->2248|5563->2254|5595->2259|5665->2302|5686->2314|5745->2352
                  LINES: 19->1|28->1|29->3|30->10|31->11|31->11|31->11|32->12|33->13|33->13|33->13|33->13|33->13|33->13|33->13|33->13|33->13|40->20|40->20|40->20|41->21|41->21|41->21|42->22|42->22|42->22|43->23|43->23|43->23|44->24|44->24|44->24|45->25|45->25|45->25|45->25|45->25|45->25|45->25|45->25|46->26|46->26|46->26|49->29|49->29|49->29|49->29|50->30|53->33|53->33|53->33|54->34|54->34|54->34|56->36|58->38|58->38|58->38|58->38|59->39|62->42|62->42|62->42|63->43|63->43|63->43|63->43|63->43|63->43|63->43|63->43|64->44|64->44|64->44|64->44|64->44|64->44|64->44|64->44|64->44|65->45|65->45|65->45|65->45|65->45|65->45|65->45|65->45|66->46|67->47|68->48|69->49|69->49|69->49|69->49|70->50|70->50|70->50|70->50|71->51|73->53|75->55|75->55|75->55|75->55|76->56|76->56|76->56|77->57|77->57|77->57|78->58|78->58|78->58|78->58|78->58|78->58|78->58|79->59|80->60|81->61|82->62|84->64|84->64|84->64
                  -- GENERATED --
              */
          