package controllers

import play.api.*
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest, WSResponse}
import play.api.mvc.*

import javax.inject.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.duration.{FiniteDuration, SECONDS}
import scala.language.postfixOps

case class Hello(name: String)
object Hello:
  import play.api.libs.json.*
  implicit val residentWrites: OWrites[Hello] = Json.writes[Hello]
  implicit val residentReads: Reads[Hello]    = Json.reads[Hello]

@Singleton class JsonController @Inject() (
    ws: WSClient,
    val controllerComponents: ControllerComponents
) extends BaseController
    with Logging:

  def returnHello(name: String): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      logger.info("calling returnHello")
      val greeting     = Hello(name)
      val jsonGreeting = Json.toJson(greeting)
      Future.successful(Ok(jsonGreeting))
  }

  def callHello(): Action[AnyContent] = Action.async {
    implicit request: Request[AnyContent] =>
      def greetingUrl(name: String): String =
        s"""http://localhost:9000/return_hello1/$name"""
      val request: WSRequest = ws.url(greetingUrl("app"))
      val complexRequest: WSRequest = request
        .addHttpHeaders("Accept" -> "application/json")
        .withRequestTimeout(FiniteDuration(5, SECONDS))
      val futureResponse: Future[WSResponse] = complexRequest.get()
      futureResponse.transform(
        r =>
          r match
            case r if (r.status == 200) => Ok("got response:" + r.json)
            case r                      => NotFound
        ,
        err =>
          logger.error("err", err)
          err
      )
  }
