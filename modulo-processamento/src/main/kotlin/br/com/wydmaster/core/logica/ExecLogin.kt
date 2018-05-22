package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.util.PersonagemUtil
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.estrutura.pacotes.SelecaoPersonagem
import br.com.wydmaster.estrutura.pacotes.QuantidadePersonagem
import br.com.wydmaster.service.ContaPersonagemService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecLogin {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){
        retornaQuantidadePersonagens(pacote, output)
        retornaPersonagens(pacote, output)
    }

    private fun retornaQuantidadePersonagens(pacote: PacoteGenerico, output: PacoteOutputStream){
        var quantidade = ContaPersonagemService.it.buscarQuantidade(pacote.cabecalho.token)

        var retorno = QuantidadePersonagem()

        retorno.cabecalho.token = pacote.cabecalho.token
        retorno.numeroPersonagens = quantidade.toByte()

        output.enviarPacote(ConverterPacote.it.objetoParaByteArray(retorno))
    }

    private fun retornaPersonagens(pacote:PacoteGenerico, output: PacoteOutputStream){
        PersonagemService.it.buscarPersonagensDaConta(pacote.cabecalho.token).forEach {
            var personagem = SelecaoPersonagem()

            personagem.nome = it.nome
            personagem.cabecalho.token = pacote.cabecalho.token
            personagem.classe = it.classeId

            PersonagemUtil.it.getItens(personagem, it)

            var pacote = ConverterPacote.it.objetoParaByteArray(personagem)

            output.enviarPacote(pacote)
        }
    }
}