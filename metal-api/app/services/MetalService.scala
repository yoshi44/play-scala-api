package services

import javax.inject._

import play.api.Logger
import play.api.mvc.Result

import models.MetalPrice
import scrapes.MetalScrape
import models.MetalPrice

@Singleton
class MetalService @Inject() (metalScrape: MetalScrape) {

  val logger = Logger(this.getClass())
  import scalaz.\/

  def getMetalPrice(): Result \/ Option[MetalPrice] =
    metalScrape.getMetalPrice()
}
