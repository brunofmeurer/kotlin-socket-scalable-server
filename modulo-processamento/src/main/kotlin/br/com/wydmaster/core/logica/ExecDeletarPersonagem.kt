package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.global.idioma.Strings
import br.com.wydmaster.core.logica.util.MensagemUtil
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.*
import br.com.wydmaster.estrutura.pacotes.DeletarPersonagem
import br.com.wydmaster.service.ContaService
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecDeletarPersonagem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){

        var deletarPersonagemPacote= ConverterPacote.it.byteArrayParaObjeto<DeletarPersonagem>(pacote.pacote)

        var personagem = PersonagemService.it.buscarPorNome(deletarPersonagemPacote.nome)

        var validaSenha = ContaService.it.verificarSenha(deletarPersonagemPacote.cabecalho.token, deletarPersonagemPacote.senha)

        if(personagem.nivel > 100){
            var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("NIVEL_IMPEDE_DELETAR_PERSONAGEM"))
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
            return
        }

        if(!validaSenha){
            var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("SENHA_INVALIDA"))
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
            return
        }

        PersonagemService.it.deletar(personagem.id)

        var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PERSONAGEM_DELETADO_COM_SUCESSO"))

        output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
        output.enviarPacote(ConverterPacote.it.objetoParaByteArray(deletarPersonagemPacote))
    }
}