package hu.bme.communityQuiz.server

fun main(args:Array<String>) {
    run(args)
}

fun run(args:Array<String>){
    io.ktor.server.netty.EngineMain.main(args)

}
