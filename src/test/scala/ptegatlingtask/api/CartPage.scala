package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper._

object CartPage {
    def openCartPage(): ChainBuilder = {
        exec(
            http("Navigate to Cart page")
                .get(pteCartUri)
                .check(css("td.td-price").find(0).saveAs("tablePrice"))
                .check(css("td.td-price").find(1).saveAs("chairPrice"))
                .check(css("td.total_net").saveAs("totalPrice"))
        )
    }

    def placeOrder(): ChainBuilder = {
        exec(
            http("Place Order")
                .post(pteCheckoutUri)
                // """{"117__":1,"89__":1}"""
                .formParam("cart_content", {
                    val tableID = "${c_tableCurrentProduct}"
                    val tableQuantity = "${c_tableCurrentQuantity}"
                    val chairID = "${c_chairCurrentProduct}"
                    val chairQuantity = "${c_chairCurrentQuantity}"

                    s"""{"${tableID}__":$tableQuantity,"${chairID}__":$chairQuantity}"""
                })
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
