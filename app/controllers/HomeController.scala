package controllers

import play.api.*
import play.api.mvc.*

import javax.inject.*
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

@Singleton class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with Logging:

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

  def asyncPage(): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    Future(
      Ok("result of blocking call")
    )
  }
