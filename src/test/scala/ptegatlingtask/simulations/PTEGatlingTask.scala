package ptegatlingtask.simulations

import io.gatling.core.structure._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import ptegatlingtask.api._
import ptegatlingtask.config.BaseHelper._

class PTEGatlingTask extends Simulation {
    val scenario1: ScenarioBuilder = {
        scenario("PTE Gatling Home Task")
            .feed(feederProducts)
            .exec(flushHttpCache)
            .exec(flushCookieJar)
            .exitBlockOnFail(
                exec(
                    HomePage.openPTEApplication(),
                    TablesPage.navigateToTablesPage(),
                    ProductPage.selectATable(),
                    ProductPage.addTableToCart(),
                    ChairsPage.navigateToChairsPage(),
                    ProductPage.selectAChair(),
                    ProductPage.addChairToCart(),
                    CartPage.navigateToCartPage(),
                    CartPage.placeOrder(),
                    CheckoutPage.selectCountry(),
                    CheckoutPage.enterDetailsAndPlaceOrder()
                )
            )
    }

    setUp(
        scenario1.inject(atOnceUsers(1))
    ).protocols(httpProtocol)
}
