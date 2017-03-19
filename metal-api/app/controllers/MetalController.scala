package controllers

import javax.inject.Inject
import javax.inject.Singleton

import play.api.libs.json.Json
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.Logger

import services.MetalService

@Singleton
class MetalController @Inject() (service: MetalService) extends Controller {

  val logger = Logger(this.getClass())

  import scalaz.\/-
  import scalaz.-\/
  def todayPrices = Action {
    service.getMetalPrice() match {
      case \/-(Some(ok)) => Ok(Json.toJson(ok))
      case \/-(None)     => Ok(Json.toJson("Ok none"))
      case -\/(error) => {
        logger.error(error.toString)
        error
      }
    }
  }
}
