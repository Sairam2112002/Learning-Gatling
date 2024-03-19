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
                        ProductPage.selectAProduct("table"),
                        ProductPage.addProductToCart()
                    )
                }.group("Add a chair to cart") {
                    exec(
                        ChairsPage.navigateToChairsPage(),
                        ProductPage.selectAProduct("chair"),
                        ProductPage.addProductToCart()
                    )
                }.group("Place order") {
                    feed(feederPersonalDetails)
                    .exec(
                        CartPage.openCartPage(),
                        CartPage.placeOrder(),
                        CheckoutPage.selectCountry(),
                        CheckoutPage.enterDetailsAndPlaceOrder()
                    )
                }
            )
    }

    val scenario2: ScenarioBuilder = {
        scenario("PTE Gatling Home Task")
            .exec(flushHttpCache)
            .exec(flushCookieJar)
            .exitBlockOnFail(
                feed(feederProducts)
                .exec(
                    HomePage.openPTEApplication(),
                    TablesPage.navigateToTablesPage(),
                    ProductPage.selectAProduct("table"),
                    ProductPage.addProductToCart(),
                    ChairsPage.navigateToChairsPage(),
                    ProductPage.selectAProduct("chair"),
                    ProductPage.addProductToCart(),
                    CartPage.openCartPage(),
                    CartPage.placeOrder()
                )
            )
    }

    // mvn clean gatling:test

    setUp(
        scenario2.inject(atOnceUsers(1))
    ).protocols(httpProtocol)
}
