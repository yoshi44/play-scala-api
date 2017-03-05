package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import services.MetalService

@Singleton
class MetalController @Inject()(service: MetalService) extends Controller {

  def todayPrices = Action {
    val test = service.getTodayPrices()
    Ok(s"Your new application is ready. metal-api===${test}")
  }

}
