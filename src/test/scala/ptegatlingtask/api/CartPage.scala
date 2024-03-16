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
                .check(css("td.td-price").find(0).saveAs("c_tablePrice"))
                .check(css("td.td-price").find(1).saveAs("c_chairPrice"))
                .check(css("td.total_net").saveAs("c_totalPrice"))
                .check(css("input[name='trans_id']", "value").saveAs("c_transactionID"))
        )
    }

    def placeOrder(): ChainBuilder = {
        exec(
            http("Place Order")
                .post(pteCheckoutUri)
                .formParam("cart_content", {
                    val tableID = "${c_tableCurrentProduct}"
                    val tableQuantity = "${c_tableCurrentQuantity}"
                    val chairID = "${c_chairCurrentProduct}"
                    val chairQuantity = "${c_chairCurrentQuantity}"

                    s"""{"${tableID}__":$tableQuantity,"${chairID}__":$chairQuantity}"""
                })
                .formParam("p_id[]", "${c_tableCurrentProduct}__")
                .formParam("p_quantity[]", "${c_tableCurrentQuantity}")
                .formParam("p_id[]", "${c_chairCurrentProduct}__")
                .formParam("p_quantity[]", "${c_chairCurrentQuantity}")
                .formParam("total_net", "${c_totalPrice}")
                .formParam("trans_id", "${c_transactionID}")
                .formParam("shipping", "order")
                .check(regex("""<option value="[A-Z]{2}" >${country} - (.*?)</option>""").saveAs("c_countryCode"))
                .check(css("input[name='ic_formbuilder_redirect']", "value").saveAs("c_redirectThankYou"))
        )
    }
}
