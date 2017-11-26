package com.alexitc.coinalerts.models

import play.api.libs.functional.syntax._
import play.api.libs.json._

case class Alert(
    id: AlertId,
    userId: UserId,
    market: Market,
    book: Book,
    isGreaterThan: Boolean,
    price: BigDecimal,
    basePrice: Option[BigDecimal] = None
)
object Alert {
  implicit val writes: Writes[Alert] = (
      (JsPath \ "id").write[AlertId] and
          (JsPath \ "market").write[Market] and
          (JsPath \ "book").write[Book] and
          (JsPath \ "isGreaterThan").write[Boolean] and
          (JsPath \ "price").write[BigDecimal] and
          (JsPath \ "basePrice").writeNullable[BigDecimal]
      ) { alert =>

    (alert.id, alert.market, alert.book, alert.isGreaterThan, alert.price, alert.basePrice)
  }
}

case class BasePriceAlert(
    alertId: AlertId,
    basePrice: BigDecimal
)

case class AlertId(long: Long) extends AnyVal
object AlertId {

  implicit val writes: Writes[AlertId] = Writes[AlertId] { id => JsNumber(id.long) }
}

case class CreateAlertModel(
    market: Market,
    book: Book,
    isGreaterThan: Boolean,
    price: BigDecimal,
    basePrice: Option[BigDecimal]
)
object CreateAlertModel {

  implicit val reads: Reads[CreateAlertModel] = {
    val builder = (JsPath \ "market").read[Market] and
        (JsPath \ "book").read[Book] and
        (JsPath \ "isGreaterThan").read[Boolean] and
        (JsPath \ "price").read[BigDecimal] and
        (JsPath \ "basePrice").readNullable[BigDecimal]

    builder(CreateAlertModel.apply _)
  }
}
