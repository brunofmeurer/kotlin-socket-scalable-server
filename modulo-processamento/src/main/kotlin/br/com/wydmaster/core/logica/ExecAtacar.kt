package br.com.wydmaster.core.logica

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.Andar
import br.com.wydmaster.estrutura.pacotes.Atacar
import br.com.wydmaster.estrutura.pacotes.LevarDano
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 01/11/17.
 */
enum class ExecAtacar {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){
        var pacoteAtacar = ConverterPacote.it.byteArrayParaObjeto<Atacar>(pacote.pacote)

        var personagemAtacado = PersonagemService.it.buscarPorNome(pacoteAtacar.nome)
        var personagemAtacando= PersonagemService.it.buscarPersonagemLogado(pacoteAtacar.cabecalho.token)

        var levarDano = LevarDano()
        levarDano.cabecalho = pacoteAtacar.cabecalho
        levarDano.dano = personagemAtacando.ataque

        enviarPacoteDano(levarDano, output)
    }

    fun enviarPacoteDano(levarDano: LevarDano, output: PacoteOutputStream){

        ContaService.it.buscarTokenConectados().forEach {

            var levarDano2 = levarDano
            levarDano2.cabecalho.token = it

            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(levarDano))
        }
    }
}