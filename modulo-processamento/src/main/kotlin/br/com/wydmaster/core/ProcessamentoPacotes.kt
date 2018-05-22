package br.com.wydmaster.core
import br.com.wydmaster.stream.PacoteInputStream
import br.com.wydmaster.stream.PacoteOutputStream
import java.net.Socket
import java.util.logging.Logger

/**
 * Criado por bruno-meurer em 24/08/17.
 */
var cont = 0;
class ProcessamentoPacotes: Thread {

    val logger = Logger.getLogger("ProcessamentoPacotes")
    val s:Socket

    constructor(socket:Socket){
        println("Acoplhado com modulo de entrada com sucesso!")
        this.s = socket
    }

    override fun run(){

        var input  = PacoteInputStream(s.getInputStream())
        var output = PacoteOutputStream(s.getOutputStream())

        while(true) {

            var pacote = input.lerPacote()

            if (pacote.cabecalho.id == 0) {
                return
            }

            logger.info("$cont Recebido pacote ${pacote.cabecalho.id} ${String(pacote.pacote)}")

            cont++
            Interpretador.it.interpretarPacote(pacote, output)

        }
    }
}