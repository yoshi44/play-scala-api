package scrapes

import java.net.UnknownHostException
import javax.inject.Singleton

import play.api.libs.json.Json
import play.api.mvc.Result
import play.api.mvc.Results.InternalServerError
import play.api.Logger

import scalaz._
import Scalaz._ //:TODO

import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL.Parse._
import net.ruippeixotog.scalascraper.model.Document
import net.ruippeixotog.scalascraper.model.Element
import models.MetalPrice

@Singleton
class MetalScrape() {

  val logger = Logger(this.getClass())

  def getMetalPrice(): Result \/ Option[MetalPrice] = {
    getDocument() match {
      case \/-(doc) => {
        val metalPrice = doc.map(_ >> element("#metal_price"))
        val gold = metalPrice.map(_ >> text(".gold .retail_tax")) | ""
        val pt = metalPrice.map(_ >> text(".pt .retail_tax")) | ""
        MetalPrice(gold = gold, platinum = pt).some.right
      }
      case -\/(e) => e.left
    }
  }

  private def getDocument(): Result \/ Option[Document] =
    try {
      JsoupBrowser().get("http://gold.tanaka.co.jp/commodity/souba/english/").some.right
    } catch {
      case e: UnknownHostException => {
        logger.error(s"error message = ${e.getMessage}", e)
        InternalServerError(
          Json.obj("msg" -> e.getMessage)
        ).left
      }
    }
}