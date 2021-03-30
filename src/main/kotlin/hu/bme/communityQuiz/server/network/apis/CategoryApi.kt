/**
* Community Quiz
* This is the server for my community quiz game
*
* OpenAPI spec version: 1.0.0
* 
*
* NOTE: This class is auto generated by the swagger code generator program.
* https://github.com/swagger-api/swagger-codegen.git
* Do not edit the class manually.
*/
package hu.bme.communityQuiz.server.network.apis

import hu.bme.communityQuiz.server.models.HibernateManager
import hu.bme.communityQuiz.server.models.Score
import hu.bme.communityQuiz.server.network.Paths
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

// ktor 0.9.x is missing io.ktor.locations.DELETE, this adds it.
// see https://github.com/ktorio/ktor/issues/288


fun Route.CategoryApi() {

    get<Paths.getCategories> {
        val scores = HibernateManager.list<Score>("Score")
        val categories = scores.map { it.category }
        call.respond(HttpStatusCode.OK,categories)
    }
    
}
