package br.com.wydmaster.core.logica.util

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.OutroPersonagem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 23/10/17.
 */
enum class EnviarPacoteUtil {
    it;

    fun enviarPacoteParaTodos(pacote:PacoteGenerico, output: PacoteOutputStream){

        var personagens = PersonagemService.it.buscarPersonagensLogados()

        personagens.forEach {
            var token = it.token
            pacote.cabecalho.token = token

            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(pacote))

        }
    }
}