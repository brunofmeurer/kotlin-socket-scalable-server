package br.com.wydmaster.core.logica

import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.Andar
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecAndar {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){
        var pacoteAndar = ConverterPacote.it.byteArrayParaObjeto<Andar>(pacote.pacote)

        var personagemConectado = PersonagemService.it.buscarPorNome(pacoteAndar.nome)

        personagemConectado!!.pos = pacoteAndar.ate
        personagemConectado!!.quaternion = pacoteAndar.quaternion

        PersonagemService.it.andar(personagemConectado.id, pacoteAndar.ate)

        enviarPacoteAndar(pacoteAndar, output)
    }

    fun enviarPacoteAndar(andar: Andar, output: PacoteOutputStream){

        ContaService.it.buscarTokenConectados().forEach {

            var andar2 = Andar()
            andar2.cabecalho.token = it

            andar2.de = andar.de
            andar2.ate = andar.ate
            andar2.quaternion = andar.quaternion
            andar2.nome = andar.nome

            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(andar2))
        }
    }
}