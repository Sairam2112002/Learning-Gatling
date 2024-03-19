package ptegatlingtask.simulations

import io.gatling.core.structure._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import ptegatlingtask.api._
import ptegatlingtask.config.BaseHelper._

class PTEGatlingTask extends Simulation {
    val scenario1: ScenarioBuilder = {
        scenario("PTE Gatling Home Task")
            .exec(flushHttpCache)
            .exec(flushCookieJar)
            .exitBlockOnFail(
                group("Open application and add a table to cart") {
                    feed(feederProducts)
                    .exec(
                        HomePage.openPTEApplication(),
                        TablesPage.navigateToTablesPage(),
                        exec(thinkTimerForChoosingAProduct()),
                        ProductPage.selectATable(),
                        ProductPage.addTableToCart()
                    )
                }.group("Add a chair to cart") {
                    exec(
                        ChairsPage.navigateToChairsPage(),
                        exec(thinkTimerForChoosingAProduct()),
                        ProductPage.selectAChair(),
                        ProductPage.addChairToCart()
                    )
                }.group("Place order") {
                    feed(feederPersonalDetails)
                    .exec(
                        exec(thinkTimeForCheckingProductsInCart()),
                        CartPage.openCartPage(),
                        CartPage.placeOrder(),
                        CheckoutPage.selectCountry(),
                        exec(timerForEnteringDetails()),
                        CheckoutPage.enterDetailsAndPlaceOrder()
                    )
                }
            )
    }

    // mvn clean gatling:test

    setUp(
        scenario1.inject(atOnceUsers(1))
    ).protocols(httpProtocol)
}
