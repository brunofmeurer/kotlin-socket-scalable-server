package br.com.wydmaster.core.io
import br.com.wydmaster.core.Interpretador
import br.com.wydmaster.entidade.ConexaoClient
import br.com.wydmaster.entidade.ContaDTO
import br.com.wydmaster.estrutura.TipoPacoteEnum
import br.com.wydmaster.stream.PacoteInputStream
import br.com.wydmaster.stream.PacoteOutputStream
import java.net.Socket
import java.util.logging.Logger


/**
 * Criado por bruno-meurer em 19/08/17.
 */

class ServerInput : Thread{

    var s:Socket? = null
    var conexaoClient:ConexaoClient
    var conta:ContaDTO? = null
    val logger = Logger.getLogger("ServerInput")


    constructor(s:Socket){
        this.s = s
        this.conexaoClient = ConexaoClient(null, PacoteOutputStream(s.getOutputStream()))
    }

    override fun run() {
        try {

            var input = PacoteInputStream(s!!.getInputStream())

            while(true) {

                var pacote = input.lerPacote()

                if(pacote.cabecalho.id == 0){
                    desconectar()
                    return
                }

                if(Interpretador.it.listaAcoplados.size <= 0){
                    logger.info("Nenhum modulo de processamento acoplado. Pacotes serão ignorados ate que ao menos um modulo de processamento seja acoplado.")
                }else {

                    if (pacote.cabecalho.id.equals(TipoPacoteEnum.LOGIN.id)) {
                        conta = Interpretador.it.primeiroRequest(conexaoClient, pacote)

                        if (conta == null) {
                            logger.info("Falha ao tentar conectar usuario: ${pacote.pacote[0]} senha:${pacote.pacote[1]} IP:${s!!.inetAddress} ")

                            desconectar()
                            return
                        }
                    } else {
                        //logger.info("PacoteGenerico ${pacote.cabecalho.id} Recebido Conta:${conta!!.usuario} ")
                        Interpretador.it.trataPacote(pacote)
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
            desconectar()
        }
    }

    fun desconectar(){
        s!!.close()
        Interpretador.it.logout(this.conta)
        logger.info("Conta ${conta?.usuario} desconectada.")
    }
}