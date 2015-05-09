package common.models

import common.domain.LineItem

case class Cart(userId: Int, lineItems: List[LineItem])
