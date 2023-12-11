package steps

import io.cucumber.scala.{EN, ScalaDsl}
import org.scalatest.*
import org.scalatest.concurrent.Eventually.eventually
import org.scalatest.matchers.*

class Steps extends ScalaDsl with EN with should.Matchers with TestBrowser:

  val host = "http://localhost:9000/"

  When("""I go to the host url"""):
    go to host

  Then("""I should see a homepage"""):
    eventually {
      pageTitle shouldBe "Welcome to Play"
    }
