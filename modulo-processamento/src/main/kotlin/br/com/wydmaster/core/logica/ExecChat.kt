package br.com.wydmaster.core.logica

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.Chat
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecChat {
    it;

    fun logica(pacote:PacoteGenerico, output: PacoteOutputStream){

        var pacoteChatAll = ConverterPacote.it.byteArrayParaObjeto<Chat>(pacote.pacote)

        if(pacoteChatAll != null) {
            ContaService.it.buscarTokenConectados().forEach {
                pacoteChatAll.cabecalho.token = it;
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(pacoteChatAll))
            }
        }
    }
}