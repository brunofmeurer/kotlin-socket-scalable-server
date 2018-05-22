package br.com.wydmaster.acoplhar.io

import br.com.wydmaster.core.Interpretador
import br.com.wydmaster.entidade.Acoplhado
import br.com.wydmaster.stream.PacoteInputStream
import br.com.wydmaster.stream.PacoteOutputStream
import java.net.Socket
import java.util.logging.Logger

/**
 * Criado por bruno-meurer em 27/08/17.
 */
var cont = 0
class ServerAcoplharInput : Thread{

    var s:Socket
    var acoplhado:Acoplhado
    val logger = Logger.getLogger("ServerAcoplharInput")

    constructor(s: Socket){

        this.s = s
        this.acoplhado = Acoplhado(cont, PacoteOutputStream(s.getOutputStream()))

        println("Modulo de processamento ${cont} iniciado com sucesso!")

        Interpretador.it.listaAcoplados.add(this.acoplhado)
        cont++
    }
    override fun run(){

        var input = PacoteInputStream(s.getInputStream())

        while(true) {

            try {
                var pacote = input.lerPacote()

                if (pacote.cabecalho.id == 0) {
                    desacopla()
                    return
                }

                Interpretador.it.listaConexoes.forEach {
                    if (it.conta!!.token.equals(pacote.cabecalho.token)) {
                        it.output.enviarPacote(pacote.pacote)

                        logger.info("ENVIANDO pacote>>>>> ${pacote.cabecalho.id} ${String(pacote.pacote)}")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                desacopla()
                return
            }
        }
    }

    fun desacopla(){
        Interpretador.it.listaAcoplados.remove(this.acoplhado)
        logger.info("Modulo ${acoplhado.id} desacoplado!")
        s!!.close()
    }
}