package common.sphere

import java.util.Locale

import common.exceptions.ConfigKeyNotFoundException
import common.helpers.Configloader
import common.models.Food
import io.sphere.sdk.attributes._
import io.sphere.sdk.models.LocalizedStrings
import io.sphere.sdk.producttypes.ProductTypeDraft
import io.sphere.sdk.utils.MoneyImpl

import scala.collection.JavaConverters._

object ProductTypeDrafts {

  val foodConfKey = "food.typeName"
  val foodTypeName = Configloader.getStringOpt(foodConfKey)
    .getOrElse(throw new ConfigKeyNotFoundException(foodConfKey))

  val food = ProductTypeDraft.of(foodTypeName, "desc", foodAttributes)

  def buildDemandAttributes(food: Food) = List(
    Attribute.of("name", food.name),
    Attribute.of("price", MoneyImpl.of(BigDecimal(food.price).bigDecimal, "EUR"))
  ).asJava

  private def foodAttributes: java.util.List[AttributeDefinition] =
    List(name, price).asJava

  private def name: AttributeDefinition = AttributeDefinitionBuilder
    .of("name", LocalizedStrings.of(Locale.ENGLISH, "name"), TextType.of())
    .isRequired(true)
    .inputHint(TextInputHint.SINGLE_LINE)
    .build()

  private def price: AttributeDefinition = AttributeDefinitionBuilder
    .of("price", LocalizedStrings.of(Locale.ENGLISH, "price"), MoneyType.of())
    .isRequired(false)
    .build()
}