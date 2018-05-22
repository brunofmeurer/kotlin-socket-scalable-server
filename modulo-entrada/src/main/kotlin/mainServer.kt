import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.acoplhar.MainAcoplhar
import br.com.wydmaster.core.io.ServerInput
import br.com.wydmaster.service.ContaService
import java.net.ServerSocket

/**
 * Criado por bruno-meurer em 19/08/17.
 */


fun main(args: Array<String>){
    try{

        MysqlHelper.it.conectar()
        val ss = ServerSocket(1111)

        println(" __        __              _     __  __                 _                 \n" +
                " \\ \\      / /  _   _    __| |   |  \\/  |   __ _   ___  | |_    ___   _ __ \n" +
                "  \\ \\ /\\ / /  | | | |  / _` |   | |\\/| |  / _` | / __| | __|  / _ \\ | '__|\n" +
                "   \\ V  V /   | |_| | | (_| |   | |  | | | (_| | \\__ \\ | |_  |  __/ | |   \n" +
                "    \\_/\\_/     \\__, |  \\__,_|   |_|  |_|  \\__,_| |___/  \\__|  \\___| |_|   \n" +
                "               |___/                                                      ")

        println("  __  __               _         _____           _                        _         \n" +
                " |  \\/  |   ___     __| |       | ____|  _ __   | |_   _ __    __ _    __| |   __ _ \n" +
                " | |\\/| |  / _ \\   / _` |       |  _|   | '_ \\  | __| | '__|  / _` |  / _` |  / _` |\n" +
                " | |  | | | (_) | | (_| |  _    | |___  | | | | | |_  | |    | (_| | | (_| | | (_| |\n" +
                " |_|  |_|  \\___/   \\__,_| (_)   |_____| |_| |_|  \\__| |_|     \\__,_|  \\__,_|  \\__,_|\n" +
                "                                                                                    ")

        println("Servico iniciado com sucesso!")

        println("Porta:${ss.localPort}")

        println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------")

        initMainAcoplamento()
        init()

        while(true){
            val s = ss.accept()
            val sd = ServerInput(s)
            sd.start()
        }

    }catch (e:Exception) {
        e.printStackTrace()
    }
}

fun initMainAcoplamento(){
    MainAcoplhar().start()
}

fun init(){
    ContaService.it.desconectarTodos()
}