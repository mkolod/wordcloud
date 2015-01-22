// @SOURCE:/Users/mkolodziej/src/wordcloud/playApp/conf/routes
// @HASH:2755c9ea73740bab57925fd2cfa03688743f47a6
// @DATE:Sun Jan 25 15:19:20 PST 2015


import play.core._
import play.core.Router._
import play.core.Router.HandlerInvokerFactory._
import play.core.j._

import play.api.mvc._
import _root_.controllers.Assets.Asset

import Router.queryString

object Routes extends Router.Routes {

import ReverseRouteContext.empty

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)](("auth",securesocial.Routes)).foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
private[this] lazy val controllers_Application_index0_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).index,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
        

// @LINE:7
private[this] lazy val controllers_Application_onlyTwitter1_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("only-twitter"))))
private[this] lazy val controllers_Application_onlyTwitter1_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).onlyTwitter,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "onlyTwitter", Nil,"GET", """""", Routes.prefix + """only-twitter"""))
        

// @LINE:8
private[this] lazy val controllers_Application_linkResult2_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("link-result"))))
private[this] lazy val controllers_Application_linkResult2_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).linkResult,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "linkResult", Nil,"GET", """""", Routes.prefix + """link-result"""))
        

// @LINE:9
private[this] lazy val controllers_Application_currentUser3_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("current-user"))))
private[this] lazy val controllers_Application_currentUser3_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).currentUser,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "currentUser", Nil,"GET", """""", Routes.prefix + """current-user"""))
        

// @LINE:10
private[this] lazy val controllers_CustomLoginController_login4_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("custom/login"))))
private[this] lazy val controllers_CustomLoginController_login4_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.CustomLoginController]).login,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CustomLoginController", "login", Nil,"GET", """""", Routes.prefix + """custom/login"""))
        

// @LINE:11
private[this] lazy val controllers_CustomLoginController_logout5_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("custom/logout"))))
private[this] lazy val controllers_CustomLoginController_logout5_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.CustomLoginController]).logout,
HandlerDef(this.getClass.getClassLoader, "", "controllers.CustomLoginController", "logout", Nil,"GET", """""", Routes.prefix + """custom/logout"""))
        

// @LINE:12
private[this] lazy val controllers_Application_twitterWS6_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("ws/twitter"))))
private[this] lazy val controllers_Application_twitterWS6_invoker = createInvoker(
play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).twitterWS,
HandlerDef(this.getClass.getClassLoader, "", "controllers.Application", "twitterWS", Nil,"GET", """""", Routes.prefix + """ws/twitter"""))
        

// @LINE:15
private[this] lazy val controllers_Assets_at7_route = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
controllers.Assets.at(fakeValue[String], fakeValue[String]),
HandlerDef(this.getClass.getClassLoader, "", "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
        

// @LINE:16
lazy val securesocial_Routes8 = Include(securesocial.Routes)
        
def documentation = List(("""GET""", prefix,"""@controllers.Application@.index"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """only-twitter""","""@controllers.Application@.onlyTwitter"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """link-result""","""@controllers.Application@.linkResult"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """current-user""","""@controllers.Application@.currentUser"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """custom/login""","""@controllers.CustomLoginController@.login"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """custom/logout""","""@controllers.CustomLoginController@.logout"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """ws/twitter""","""@controllers.Application@.twitterWS"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),securesocial.Routes.documentation).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]]
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0_route(params) => {
   call { 
        controllers_Application_index0_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).index)
   }
}
        

// @LINE:7
case controllers_Application_onlyTwitter1_route(params) => {
   call { 
        controllers_Application_onlyTwitter1_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).onlyTwitter)
   }
}
        

// @LINE:8
case controllers_Application_linkResult2_route(params) => {
   call { 
        controllers_Application_linkResult2_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).linkResult)
   }
}
        

// @LINE:9
case controllers_Application_currentUser3_route(params) => {
   call { 
        controllers_Application_currentUser3_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).currentUser)
   }
}
        

// @LINE:10
case controllers_CustomLoginController_login4_route(params) => {
   call { 
        controllers_CustomLoginController_login4_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.CustomLoginController]).login)
   }
}
        

// @LINE:11
case controllers_CustomLoginController_logout5_route(params) => {
   call { 
        controllers_CustomLoginController_logout5_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.CustomLoginController]).logout)
   }
}
        

// @LINE:12
case controllers_Application_twitterWS6_route(params) => {
   call { 
        controllers_Application_twitterWS6_invoker.call(play.api.Play.maybeApplication.map(_.global).getOrElse(play.api.DefaultGlobal).getControllerInstance(classOf[controllers.Application]).twitterWS)
   }
}
        

// @LINE:15
case controllers_Assets_at7_route(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(controllers.Assets.at(path, file))
   }
}
        

// @LINE:16
case securesocial_Routes8(handler) => handler
        
}

}
     