package common.models

import java.util.Currency

import common.domain.LineItem
import io.sphere.sdk.carts.{Cart => SphereCart, CartBuilder}
import org.javamoney.moneta.internal.JDKCurrencyAdapter
import scala.collection.JavaConverters._

case class Cart(userId: String, lineItems: List[LineItem]) {

  // Todo move to extension method SphereCart.fromCard
  def toSphereCart: Option[SphereCart] = {
    val cartBuilder = CartBuilder.of(new JDKCurrencyAdapter(Currency.getInstance("EUR")))
      .customerId(this.userId)
      .lineItems(this.lineItems.flatMap(l => l.toSphereLineItem).asJava)
    Some(cartBuilder.build())
  }
}
