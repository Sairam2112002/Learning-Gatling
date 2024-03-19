package ptegatlingtask.config

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef.http
import io.gatling.http.protocol.HttpProtocolBuilder

object BaseHelper {
    val pteBaseUri = "http://localhost/"
    val pteTablesUri = "http://localhost/tables"
    val pteChairsUri = "http://localhost/chairs"
    val pteProductsUri = "http://localhost/products/"
    val pteAdminUri = "http://localhost/wp-admin/admin-ajax.php"
    val pteCartUri = "http://localhost/cart"
    val pteCheckoutUri = "http://localhost/checkout"
    val pteTankYouUri = "http://localhost/thank-you"

    val feederProducts = csv("ptegatlingtask/feeders/products.csv").circular
    val feederPersonalDetails = csv("ptegatlingtask/feeders/personalDetails.csv").circular

    def thinkTimerForChoosingAProduct(): ChainBuilder = {
        pause(5, 10)
    }

    def thinkTimeForCheckingProductsInCart(): ChainBuilder = {
        pause(2, 5)
    }

    def timerForEnteringDetails(): ChainBuilder = {
        pause(20, 30)
    }

    private val htmlAllowList = AllowList(
        s"""$pteBaseUri""",
        s"""$pteTablesUri""",
        s"""$pteChairsUri""",
        s"""$pteProductsUri""",
        s"""$pteAdminUri""",
        s"""$pteCartUri""",
        s"""$pteCheckoutUri""",
        s"""$pteTankYouUri""")

    val httpProtocol: HttpProtocolBuilder = http
        .inferHtmlResources(htmlAllowList, DenyList(""".*"""))
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate, br")
        .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
}
