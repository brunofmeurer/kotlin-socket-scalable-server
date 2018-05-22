package br.com.wydmaster.acoplhar

import br.com.wydmaster.acoplhar.io.ServerAcoplharInput
import java.net.ServerSocket

/**
 * Criado por bruno-meurer em 27/08/17.
 */
class MainAcoplhar : Thread() {

    override fun run() {
        println("Aguardando conexao do modulo de processamento...")

        val ss = ServerSocket(1112)

        while(true){
            val s = ss.accept()
            val sd = ServerAcoplharInput(s)
            sd.start()
        }
    }
}