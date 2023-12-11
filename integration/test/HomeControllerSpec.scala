import controllers.HomeController
import org.scalatestplus.play.*
import org.scalatestplus.play.guice.*
import play.api.test.*
import play.api.test.Helpers.*

import scala.language.postfixOps

class HomeControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting:

  "IT test" should {
    "access root application" in {
      val controller = new HomeController(stubControllerComponents())
      val home = controller.index().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("text/html")
      contentAsString(home) must include("Welcome to Play")

  }
}
