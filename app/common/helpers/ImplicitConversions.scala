package common.helpers

import java.util.Currency

import io.sphere.sdk.carts.{Cart => SphereCart, CartBuilder}
import org.javamoney.moneta.internal.JDKCurrencyAdapter

object ImplicitConversions {

  implicit class OptionConverter[T](x: java.util.Optional[T]) {
    def asScala: Option[T] = {
      if (x.isPresent) Some[T](x.get())
      else Option.empty[T]
    }
  }

  implicit class SphereCartConverter(c: SphereCart.type) {
    def fromCart: Option[SphereCart] = {
      val cartBuilder = CartBuilder.of(new JDKCurrencyAdapter(Currency.getInstance("EUR")))

      Some(cartBuilder.build())
    }
  }

}
