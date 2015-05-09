package migrations

import common.helpers.ImplicitConversions._
import common.sphere.{ProductTypeDrafts, SphereClient}
import io.sphere.sdk.producttypes.commands.ProductTypeCreateCommand
import io.sphere.sdk.producttypes.queries.ProductTypeQuery
import io.sphere.sdk.producttypes.{ProductType, ProductTypeDraft}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ProductTypeMigrations(sphereClient: SphereClient) extends Migration {

  override def run(): Future[Any] = {
    createType(ProductTypeDrafts.food)
  }

  def createType(typeDraft: ProductTypeDraft): Future[ProductType] = {
    getExistingProductType(typeDraft.getName) flatMap {
      case Some(productType: ProductType) => Future(productType)
      case None => sphereClient.execute(ProductTypeCreateCommand.of(typeDraft))
    }
  }

  def getExistingProductType(typeName: String): Future[Option[ProductType]] = {
    sphereClient.execute(ProductTypeQuery.of().byName(typeName)) map(res => res.head().asScala)
  }
}