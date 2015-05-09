package common.domain

import common.models.Food
import io.sphere.sdk.carts.{LineItem => SphereLineItem}

case class LineItem(food: Food, quantity: Int) {

  def toSphereLineItem: Option[SphereLineItem] = {
    None
  }
}
