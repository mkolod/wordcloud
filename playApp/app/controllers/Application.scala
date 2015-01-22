/**
 * Copyright 2012 Jorge Aliss (jaliss at gmail dot com) - twitter: @jaliss
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package controllers

import akka.actor._
import securesocial.core._
import service.DemoUser
import play.api.libs.iteratee._
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{ Action, RequestHeader, WebSocket }
import play.api.Play
import twitter4j.{Status => StreamStatus, _}
import twitter4j.auth.AccessToken
import twitter4j.conf.ConfigurationBuilder
import securesocial.core.SecureSocial

import scala.concurrent.duration._
import scala.concurrent.Await


class Application(override implicit val env: RuntimeEnvironment[DemoUser]) extends securesocial.core.SecureSocial[DemoUser] {
  def index = SecuredAction { implicit request =>
    Ok(views.html.index(request.user.main))
  }

  // a sample action using an authorization implementation
  def onlyTwitter = SecuredAction(WithProvider("twitter")) { implicit request =>
    Ok("You can see this because you logged in using Twitter")
  }

  def linkResult = SecuredAction { implicit request =>
    Ok(views.html.linkResult(request.user))
  }

  /**
   * Sample use of SecureSocial.currentUser. Access the /current-user to test it
   */
  def currentUser = Action.async { implicit request =>
    import play.api.libs.concurrent.Execution.Implicits._
    SecureSocial.currentUser[DemoUser].map { maybeUser =>
      val userId = maybeUser.map(_.main.userId).getOrElse("unknown")
      Ok(s"Your id is $userId")
    }
  }

  private def socket(user: BasicProfile) = WebSocket.using[String] { request =>

    // Log events to the console
    val in = Iteratee.foreach[String](println).map { _ =>
      println("Disconnected")
    }

    // Send a single 'Hello!' message
    val out = Enumerator("Hello!")

    (in, out)
  }

  import play.api.Play.current
  def twitterWS = WebSocket.acceptWithActor[String, String] { implicit request =>

    import play.api.libs.concurrent.Execution.Implicits._

    // clean this up in a monadic way
    val user = Await.result(SecureSocial.currentUser[DemoUser], 10 seconds) match {
      case Some(user) => user
      case _ => throw new Exception("foo")
    }

    out =>

      // head can fail - use headOption and pattern match
      val twitterProvider = user.identities.filter(_.providerId == "twitter").head

      // log instead of printing
      val in = Iteratee.foreach[String](println).map { _ =>

        println("Disconnected")
      }

      val conf = Play.current.configuration
      val oAuthInfo = twitterProvider.oAuth1Info.get
      val token = oAuthInfo.token
      val secret = oAuthInfo.secret
      val twitterConf = new ConfigurationBuilder()
        .setOAuthConsumerKey(conf.getString("securesocial.twitter.consumerKey").get)
        .setOAuthConsumerSecret(conf.getString("securesocial.twitter.consumerSecret").get)
        .setOAuthAccessToken(token)
        .setOAuthAccessTokenSecret(secret)
        .build()

      val twitterStream = new TwitterStreamFactory(twitterConf).getInstance()

      MyWebSocketActor.props(out, twitterStream)
  }

  object MyWebSocketActor {

    def props(out: ActorRef, ts: TwitterStream) = Props(new MyWebSocketActor(out, ts))
  }

  class MyWebSocketActor(out: ActorRef, ts: TwitterStream) extends Actor {

    override def preStart = {

      val listener = new StatusListener() {

        def onStatus(status: StreamStatus): Unit = {
          val lang = status.getLang
          // TODO: do some more text processing here - remove stop words, look up against dictionary, etc.
          val text = status
            .getText
            .toLowerCase
            .replaceAll("[^A-Za-z ]", "") // alphas only
            .replaceAll("\\s+", " ") // single space replaces multiple whitespaces, add line feed
          if (lang.toLowerCase == "en" && text.replaceAll(" ", "").length > 0) out ! text
        }

        def onStallWarning(warning: StallWarning): Unit = out ! warning

        def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice): Unit = out ! statusDeletionNotice

        def onScrubGeo(userId: Long, upToStatusId: Long): Unit = {}

        def onTrackLimitationNotice(numberOfLimitedStatuses: Int): Unit = {}

        def onException(ex: Exception): Unit = throw ex
      }

      ts.addListener(listener)
      ts.sample()
    }

    def receive = {

      case msg: String => println(msg)
    }
  }


  // Add SecuredAction
//  def twitterWS = UserAwareAction { implicit request =>
//    request.user match {
//      case Some(user) =>
//        socket(user.main)
////        Ok("OK")
////        Ok()
//      case _ => Unauthorized
//    }
//  }

//    val user = SecureSocial.currentUser(request, env, defaultContext).mapTo[Option[String]]
//
//
//
//    val (in, out) = user match {
//
//      case Some(user) =>
//        val twitter = new TwitterFactory().getInstance()
//        val conf = Play.current.configuration
//        // fix the get part since Option[String] may fail
//        twitter.setOAuthConsumer(
//          conf.getString("securesocial.twitter.consumerKey").get,
//          conf.getString("securesocial.twitter.consumerSecret").get
//        )
//
//
////        val accessToken = securesocial.
//
//
//      // change this to simply a different HTTP status for a rejected request
//      case None =>
//        (Iteratee.ignore[String], Enumerator("User not authenticated and authorized.").andThen(Enumerator.eof))
//
//    }
//    // change to logger
////    val in = Iteratee.foreach[String](println).map { _ => println("Disconnected")}
////
////    val out = Enumerator("Hello!")
//
//    (in, out)
//  }

}

// An Authorization implementation that only authorizes uses that logged in using twitter
case class WithProvider(provider: String) extends Authorization[DemoUser] {
  def isAuthorized(user: DemoUser, request: RequestHeader) = {
    user.main.providerId == provider
  }
}

//object TwitterHandler {
//
//  private def token: String = {
//
//    Play.current.
////    val iterator = SecureSocial.
//  }
//
//  val twitter = new TwitterFactory().getInstance()
//  val conf = Play.current.configuration
//  // fix the get part since Option[String] may fail
//  twitter.setOAuthConsumer(
//    conf.getString("securesocial.twitter.consumerKey").get,
//    conf.getString("securesocial.twitter.consumerSecret").get
//  )
//  /* This is probably not necessary and can be obtained from SecureSocial instead,
//     since the user had already been authenticated and authorized */
//  val accessToken = new AccessToken(token, tokenSecret)
//
//  /*
//  public static String token() {
//String retval = "";
//scala.collection.Iterator iterator = Application.getCurrentUser().oAuth1Info().iterator();
//while (iterator.hasNext()) {
//OAuth1Info oAuth1Info = iterator.next();
//retval = oAuth1Info.token();
//}
//return retval;
//}
//   */
//}