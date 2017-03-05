package scrapes

import javax.inject.Singleton

import net.ruippeixotog.scalascraper.browser.JsoupBrowser

import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._

import net.ruippeixotog.scalascraper.model.Element

import models.MetalPrice

@Singleton
class MetalScrape() {

  def getTodayPrices(): MetalPrice = {
    val browser = JsoupBrowser()
    val doc = browser.get("http://gold.tanaka.co.jp/commodity/souba/english/")

    val metalPrice = doc >> element("#metal_price")

    val gold = metalPrice >> text(".gold .retail_tax")
    val pt = metalPrice >> text(".pt .retail_tax")

    MetalPrice(gold = gold, platinum = pt)
  }
}