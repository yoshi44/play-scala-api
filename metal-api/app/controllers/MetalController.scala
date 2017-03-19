package controllers

import javax.inject._

import play.api.libs.json.Json
import play.api.mvc._
import play.api.Logger

import services.MetalService

@Singleton
class MetalController @Inject() (service: MetalService) extends Controller {

  val logger = Logger(this.getClass())

  import scalaz._, Scalaz._
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
