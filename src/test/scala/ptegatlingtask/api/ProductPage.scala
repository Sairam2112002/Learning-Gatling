package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper._

object ProductPage {
    def selectATable(): ChainBuilder = {
        exec(http("Select a Table").get(session => pteProductsUri +  s"${session("table").as[String]}"))
    }

    def selectAChair(): ChainBuilder = {
        exec(http("Select a Chair").get(session => pteProductsUri +  s"${session("chair").as[String]}"))
    }

    def addTableToCart(): ChainBuilder = {
        exec(
            http("Add table to cart")
                .post(pteAdminUri)
                .formParam("action", "ic_add_to_cart")
                .formParam("add_cart_data", "current_product=117&cart_content=&current_quantity=1")
                .formParam("cart_widget", "0")
                .formParam("cart_container", "0")
        )
    }

    def addChairToCart(): ChainBuilder = {
        exec(
            http("Add chair to cart")
                .post(pteAdminUri)
                .formParam("action", "ic_add_to_cart")
                .formParam("add_cart_data", "current_product=89&cart_content=&current_quantity=1")
                .formParam("cart_widget", "0")
                .formParam("cart_container", "0")
        )
    }
}
