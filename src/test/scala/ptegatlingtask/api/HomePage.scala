package ptegatlingtask.api

import io.gatling.core.Predef._
import io.gatling.core.structure._
import io.gatling.http.Predef._
import ptegatlingtask.config.BaseHelper.pteBaseUri

object HomePage {
    def openPTEApplication(): ChainBuilder = {
        exec(http("Open Performance Testing Essentials Application").get(pteBaseUri))
    }
}
