package ptegatlingtask.scenarios

import io.gatling.core.structure._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import ptegatlingtask.api._
import ptegatlingtask.config.BaseHelper._

object PTEGatlingTaskScenarios {
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
                    exec(thinkTimerForChoosingAProduct()), // pause
                    ProductPage.selectAProduct("table"),
                    ProductPage.addProductToCart()
                )
            }.group("Add a chair to cart") {
                randomSwitch(
                    50d -> {
                        exec(
                            ChairsPage.navigateToChairsPage(),
                            exec(thinkTimerForChoosingAProduct()), // pause
                            ProductPage.selectAProduct("chair"),
                            ProductPage.addProductToCart()
                        )
                    }
                )
            }.group("Place order") {
                randomSwitch(
                    30d -> {
                        feed(feederPersonalDetails)
                        .exec(
                            CartPage.openCartPage(),
                            exec(thinkTimerForCheckingProductsInCart()), // pause
                            CartPage.placeOrder(),
                            exec(timerForEnteringDetails()), // pause
                            CheckoutPage.selectCountry(),
                            CheckoutPage.enterDetailsAndPlaceOrder()
                        )
                    }
                )
            }
        )
    }
}
