package hu.bme.communityQuiz.server.util

import hu.bme.communityQuiz.server.models.HibernateManager
import hu.bme.communityQuiz.server.models.Question
import hu.bme.communityQuiz.server.models.Score
import java.math.BigDecimal

fun main(args:Array<String>) {
    try {
        loadTestData()
    }
    catch(t:Throwable){
        t.printStackTrace()
    }
    finally {
        HibernateManager.closeFactory()
    }
}

fun loadTestData() {
    val questions = listOf(
        Question(
            id="history1",
            category = "history",
            question = "Mit ünneplünk március tizenötödikén?",
            rightAnswer = "1848-as szabadságharc",
            wrongAnswer1 = "1956-os forradalom",
            wrongAnswer2 = "aradi vértanúk emléknapja",
            wrongAnswer3 = "Szent István megkoronázása"
        ),
        Question(
            id="history2",
            category = "history",
            question = "Mikor ért véget a második világháború?",
            rightAnswer = "1945",
            wrongAnswer1 = "1938",
            wrongAnswer2 = "1939",
            wrongAnswer3 = "1946"
        ),
        Question(
            id="history3",
            category = "history",
            question = "Mikor kezdődött az első világháború?",
            rightAnswer = "1914",
            wrongAnswer1 = "1918",
            wrongAnswer2 = "1915",
            wrongAnswer3 = "1919"
        ),
        Question(
            id="sports1",
            category = "sports",
            question = "Hány aranyat nyert eddig Hosszú Katinka 2021-ig?",
            rightAnswer = "67",
            wrongAnswer1 = "56",
            wrongAnswer2 = "30",
            wrongAnswer3 = "113"
        ),
        Question(
            id="sports2",
            category = "sports",
            question = "Fejezze be a szöveget: Három-Egy a félidőben és a végén...",
            rightAnswer = "Hat-Három",
            wrongAnswer1 = "Hét-Három",
            wrongAnswer2 = "Hét-Négy",
            wrongAnswer3 = "Hat-Négy"
        ),
        Question(
            id="sports3",
            category = "sports",
            question = "Mikor került megrendezésre a 2020-as olimpia",
            rightAnswer = "2021 nyarán",
            wrongAnswer1 = "2020 nyarán",
            wrongAnswer2 = "2020 ősszel",
            wrongAnswer3 = "2019 nyarán"
        ),
        Question(
            id="art1",
            category = "art",
            question = "Mi volt Picasso művészeti ága?",
            rightAnswer = "Festészet",
            wrongAnswer1 = "Szobrászat",
            wrongAnswer2 = "Irodalom",
            wrongAnswer3 = "Divat"
        ),
        Question(
            id="art2",
            category = "art",
            question = "Mihez nem értett Da Vinci?",
            rightAnswer = "Atom fizika",
            wrongAnswer1 = "Gépészet",
            wrongAnswer2 = "Anatómia",
            wrongAnswer3 = "Festészet"
        ),
        Question(
            id="art3",
            category = "art",
            question = "Melyik nem egy Eminem szám?",
            rightAnswer = "The reel slim shady",
            wrongAnswer1 = "Lose Yourself",
            wrongAnswer2 = "Without Me",
            wrongAnswer3 = "Fall"
        )
    )
    HibernateManager.saveOrUpdateAll(questions)
    val scores = listOf(
        Score(id="history",category = "history",point = BigDecimal(0)),
        Score(id="sports",category = "sports",point = BigDecimal(0)),
        Score(id="art",category = "art",point = BigDecimal(0)),
    )
    HibernateManager.saveOrUpdateAll(scores)
}