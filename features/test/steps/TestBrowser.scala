package steps

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.{ChromeDriver, ChromeOptions}
import org.scalatestplus.selenium.WebBrowser

trait TestBrowser extends WebBrowser:
  implicit val webDriver: WebDriver = CftDriver.driver

object CftDriver:
  val driver: WebDriver =
    val co = ChromeOptions()
    co.setBrowserVersion("stable")
    new ChromeDriver(co)
  sys addShutdownHook driver.quit()
