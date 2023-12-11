import io.gatling.core.Predef.*
import io.gatling.http.Predef.*

class HomepageSimulation extends Simulation:

  val httpProtocol = http
    .baseUrl("http://localhost:9000")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")

  val scn = scenario("HomepageSimulation")
    .exec(http("homepage").get("/"))
    .pause(5)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
