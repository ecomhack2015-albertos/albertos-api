package common.domain

import common.models.Food
import io.sphere.sdk.carts.{LineItem => SphereLineItem}

case class LineItem(food: Food, quantity: Int) {

  // Todo move to extension method SphereLineItem.fromLineItem
  def toSphereLineItem: Option[SphereLineItem] = {
    None
  }
}
