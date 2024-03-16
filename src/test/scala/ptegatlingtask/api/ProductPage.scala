package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper._

object ProductPage {
    def selectATable(): ChainBuilder = {
        exec(
            http("Select a Table")
                .get(pteProductsUri + "${table}")
                .check(css("input[name='current_product']", "value").saveAs("c_tableCurrentProduct"))
                .check(css("input[name='cart_content']", "value").saveAs("c_tableCartContent"))
                .check(css("input[name='current_quantity']", "value").saveAs("c_tableCurrentQuantity"))
        )
    }

    def addTableToCart(): ChainBuilder = {
        exec(
            http("Add Table to cart")
                .post(pteAdminUri)
                .formParam("action", "ic_add_to_cart")
                .formParam("add_cart_data", "current_product=${c_tableCurrentProduct}&cart_content=${c_tableCartContent}&current_quantity=${c_tableCurrentQuantity}")
                .formParam("cart_widget", "0")
                .formParam("cart_container", "0")
                .check(status.is(200))
                .check(substring("Added!").exists)
        )
    }

    def selectAChair(): ChainBuilder = {
        exec(
            http(s"Select a Chair")
                .get(pteProductsUri + "${chair}")
                .check(css("input[name='current_product']", "value").saveAs("c_chairCurrentProduct"))
                .check(css("input[name='cart_content']", "value").saveAs("c_chairCartContent"))
                .check(css("input[name='current_quantity']", "value").saveAs("c_chairCurrentQuantity"))
        )
    }

    def addChairToCart(): ChainBuilder = {
        exec(
            http("Add Chair to cart")
                .post(pteAdminUri)
                .formParam("action", "ic_add_to_cart")
                .formParam("add_cart_data", "current_product=${c_chairCurrentProduct}&cart_content=${c_chairCartContent}&current_quantity=${c_chairCurrentQuantity}")
                .formParam("cart_widget", "0")
                .formParam("cart_container", "0")
                .check(status.is(200))
                .check(substring("Added!").exists)
        )
    }
}
