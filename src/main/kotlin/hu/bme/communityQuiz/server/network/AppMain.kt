package hu.bme.communityQuiz.server.network

import com.codahale.metrics.*
import com.sun.javafx.font.Metrics
import com.typesafe.config.ConfigFactory
import hu.bme.communityQuiz.server.models.HibernateManager
import io.ktor.application.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.config.HoconApplicationConfig
import io.ktor.features.*
import io.ktor.gson.GsonConverter
import io.ktor.locations.*
import io.ktor.routing.*
import java.util.concurrent.*
import hu.bme.communityQuiz.server.network.apis.CategoryApi
import hu.bme.communityQuiz.server.network.apis.QuestionApi
import hu.bme.communityQuiz.server.network.apis.QuizApi
import hu.bme.communityQuiz.server.network.apis.ScoreApi
import io.ktor.http.*
import io.ktor.metrics.dropwizard.*
import io.ktor.util.*
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration


@KtorExperimentalAPI
internal val settings = HoconApplicationConfig(ConfigFactory.defaultApplication(HTTP::class.java.classLoader))

object HTTP {
    val client = HttpClient(Apache)
}

@ExperimentalTime
fun Application.main() {
    install(DefaultHeaders)
    install(DropwizardMetrics) {
        val reporter = Slf4jReporter.forRegistry(registry)
                .outputTo(log)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build()
        reporter.start(10, TimeUnit.SECONDS)
    }
    install(ContentNegotiation) {
        register(ContentType.Application.Json, GsonConverter())
    }
    install(AutoHeadResponse) // see http://ktor.io/features/autoheadresponse.html
    install(HSTS, ApplicationHstsConfiguration()) // see http://ktor.io/features/hsts.html
    install(Compression, ApplicationCompressionConfiguration()) // see http://ktor.io/features/compression.html
    install(Locations) // see http://ktor.io/features/locations.html
    install(Routing) {
        CategoryApi()
        QuestionApi()
        QuizApi()
        ScoreApi()
    }
    install(CORS){
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        anyHost()
        //maxAgeDuration = 1.0.toDuration(DurationUnit.DAYS)
        allowNonSimpleContentTypes = true
    }

    environment.monitor.subscribe(ApplicationStopping)
    {
        HTTP.client.close()
        HibernateManager.closeFactory()
    }
}