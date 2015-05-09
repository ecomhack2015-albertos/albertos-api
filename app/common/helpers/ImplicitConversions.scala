package common.helpers

import java.util.Currency

import org.javamoney.moneta.internal.JDKCurrencyAdapter

object ImplicitConversions {

  implicit class OptionConverter[T](x: java.util.Optional[T]) {
    def asScala: Option[T] = {
      if (x.isPresent) Some[T](x.get())
      else Option.empty[T]
    }
  }

}
