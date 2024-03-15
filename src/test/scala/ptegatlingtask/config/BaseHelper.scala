package ptegatlingtask.config

import io.gatling.core.Predef._
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

    val feederProducts = csv("ptegatlingtask/feeders/products.csv").circular

    val httpProtocol: HttpProtocolBuilder = http
        .disableAutoReferer
        .disableFollowRedirect
        .inferHtmlResources(AllowList(), DenyList(""".*\.js""", """.*\.css""", """.*\.gif""", """.*\.jpeg""", """.*\.webp""",""".*\.jpg""", """.*\.ico""", """.*\.woff""", """.*\.woff2""", """.*\.(t|o)tf""", """.*\.png""", """.*\.svg""", """.*detectportal\.firefox\.com.*"""))
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate, br")
        .acceptLanguageHeader("en-GB,en-US;q=0.9,en;q=0.8")
        .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36")
}
