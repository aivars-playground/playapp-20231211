package controllers

import play.api.*
import play.api.libs.json.Json
import play.api.mvc.*

import javax.inject.*
import scala.concurrent.Future

@Singleton class JsonController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with Logging:

  def returnHello(name: String): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    val json = Json.toJson(Hello(name))
    Future.successful(Ok(json))
  }

case class Hello(name: String)
object Hello:
  import play.api.libs.json.*
  implicit val residentWrites: OWrites[Hello] = Json.writes[Hello]