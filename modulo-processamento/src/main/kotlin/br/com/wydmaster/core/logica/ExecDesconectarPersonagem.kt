package br.com.wydmaster.core.logica

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.DesconectarPersonagem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaPersonagemService
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecDesconectarPersonagem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){
        var desconectarPersonagem= ConverterPacote.it.byteArrayParaObjeto<DesconectarPersonagem>(pacote.pacote)

        var personagem = PersonagemService.it.buscarIdNomePersonagemLogado(desconectarPersonagem.cabecalho.token)
        //desconectarPersonagem.nome = personagem.nome
        ContaPersonagemService.it.deslogado(desconectarPersonagem.cabecalho.token)

        var pacote = ConverterPacote.it.objetoParaByteArray(desconectarPersonagem)

        //output.enviarPacote(ConverterPacote.it.objetoParaByteArray(desconectarPersonagem))
        enviarPacoteDesconectar(personagem.nome, output)
    }

    fun enviarPacoteDesconectar(nome: String, output: PacoteOutputStream){

        ContaService.it.buscarTokenConectados().forEach {

            var desconectarPersonagem = DesconectarPersonagem()
            desconectarPersonagem.cabecalho.token = it

            desconectarPersonagem.nome = nome

            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(desconectarPersonagem))
        }
    }
}