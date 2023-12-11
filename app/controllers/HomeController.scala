package controllers

import play.api.*
import play.api.mvc.*

import javax.inject.*

@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController with Logging:

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }
