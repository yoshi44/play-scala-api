package models

import play.api.libs.json.Json

case class MetalPrice(
  platinum: String,
  gold: String)
object MetalPrice {
  implicit val format = Json.format[MetalPrice]
}
