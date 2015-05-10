package common.domain

import javax.money.MonetaryAmount

import io.sphere.sdk.orders.{OrderState, OrderImportDraftBuilder}
import io.sphere.sdk.utils.MoneyImpl
import scala.collection.JavaConverters._

case class OrderDraft(userId: String, lineItems: List[LineItem]) {

  def toImportDraft = {
    OrderImportDraftBuilder.ofLineItems(total, OrderState.OPEN,
      lineItems.map(l => l.toImportDraft).asJava).customerId(userId)
      .build()
  }

  def total(): MonetaryAmount = {
    val total = lineItems.map(l => l.total()).foldLeft(0.0)(_ + _)
    MoneyImpl.of(BigDecimal(total).bigDecimal, "EUR")
  }
}

object OrderDraft {

}
