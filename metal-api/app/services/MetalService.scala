package services

import javax.inject._
import models.MetalPrice
import scrapes.MetalScrape

@Singleton
class MetalService @Inject()(metalScrape: MetalScrape) {

  def getTodayPrices(): MetalPrice = {
    val a = metalScrape.getTodayPrices()
    println(s"a===${a}")
    a
  }
}
