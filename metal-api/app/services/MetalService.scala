package services

import javax.inject._
import models.MetalPrice

@Singleton
class MetalService @Inject() {

  def getTodayPrices(): MetalPrice = {
    MetalPrice(1000, 2000)
  }
}
