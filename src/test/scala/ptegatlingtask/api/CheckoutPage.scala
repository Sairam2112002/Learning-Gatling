package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper._

object CheckoutPage {
    def selectCountry(): ChainBuilder = {
        exec(
            http("Select a Country")
                .post(pteAdminUri)
                .formParam("action", "ic_state_dropdown")
                .formParam("country_code", "IN")
                .formParam("state_code", ""),
        )
    }

    def enterDetailsAndPlaceOrder(): ChainBuilder = {
        exec(
            http("Enter Details and Place Order")
                .post(pteCheckoutUri)
                .formParam("ic_formbuilder_redirect", "http://localhost/thank-you")
                .formParam("cart_content", """{"117__":1,"89__":1}""")
                .formParam("""product_price_117__""", "89.00")
                .formParam("""product_price_89__""", "345.00")
                .formParam("total_net", "434.00")
                .formParam("trans_id", "17105043347648")
                .formParam("shipping", "order")
                .formParam("cart_content", """{"117__":1,"89__":1}""")
                .formParam("cart_type", "order")
                .formParam("cart_inside_header_1", "<b>BILLING ADDRESS</b>")
                .formParam("cart_company", "Gryffindor")
                .formParam("cart_name", "Harry Potter")
                .formParam("cart_address", "Hogwarts")
                .formParam("cart_postal", "P44")
                .formParam("cart_city", "Hogsmeade")
                .formParam("cart_country", "IN")
                .formParam("cart_state", "IN_AP")
                .formParam("cart_phone", "1234567890")
                .formParam("cart_email", "hp@gmail.com")
                .formParam("cart_comment", "no comments")
                .formParam("cart_inside_header_2", "<b>DELIVERY ADDRESS</b> (FILL ONLY IF DIFFERENT FROM THE BILLING ADDRESS)")
                .formParam("cart_s_company", "")
                .formParam("cart_s_name", "")
                .formParam("cart_s_address", "")
                .formParam("cart_s_postal", "")
                .formParam("cart_s_city", "")
                .formParam("cart_s_country", "")
                .formParam("cart_s_state", "")
                .formParam("cart_s_phone", "")
                .formParam("cart_s_email", "")
                .formParam("cart_s_comment", "")
                .formParam("cart_submit", "Place Order")
                .check(status.in(200, 302))
        )
    }
}
