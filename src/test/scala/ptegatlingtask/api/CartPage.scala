package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper._

object CartPage {
    def navigateToCartPage(): ChainBuilder = {
        exec(http("Navigate to Cart page").get(pteCartUri))
    }

    def placeOrder(): ChainBuilder = {
        exec(
            http("Place Order")
                .post(pteCheckoutUri)
                .formParam("cart_content", """{"117__":1,"89__":1}""")
                .formParam("p_id[]", "117__")
                .formParam("p_quantity[]", "1")
                .formParam("p_id[]", "89__")
                .formParam("p_quantity[]", "1")
                .formParam("total_net", "434.00")
                .formParam("trans_id", "17105043347648")
                .formParam("shipping", "order")
        )
    }
}
