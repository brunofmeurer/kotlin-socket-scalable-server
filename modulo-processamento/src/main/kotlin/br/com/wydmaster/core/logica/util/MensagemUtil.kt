package br.com.wydmaster.core.logica.util

import br.com.wydmaster.estrutura.pacotes.Mensagem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico

/**
 * Criado por bruno-meurer em 21/10/17.
 */
enum class MensagemUtil {

    it;

    fun criaMensagem(pacote: PacoteGenerico, mensagem:String): Mensagem {
        var mens = Mensagem()
        mens.cabecalho.token = pacote.cabecalho.token
        mens.mensagem = mensagem
        return mens
    }
}