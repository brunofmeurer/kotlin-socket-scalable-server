package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.global.idioma.Strings
import br.com.wydmaster.core.logica.util.MensagemUtil
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.EquiparItem
import br.com.wydmaster.estrutura.pacotes.MoverItem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.service.SlotEquipamentoService
import br.com.wydmaster.service.SlotInventarioService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecMoverItem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){

        var pacoteMoverItem = ConverterPacote.it.byteArrayParaObjeto<MoverItem>(pacote.pacote)
        var personagem = PersonagemService.it.buscarIdNomePersonagemLogado(pacoteMoverItem.cabecalho.token)

        //verifica se usuario é o mesmo logado
        if(!personagem.nome.equals(pacoteMoverItem.nome)){
            var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PERSONAGEM_INVALIDO"))
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
            return
        }

        var slotsInventario = SlotInventarioService.it.buscarSlots(personagem.id)
        var de = slotsInventario.find{
            it.posicao.equals(pacoteMoverItem.de.posicao)
        }

        var para = slotsInventario.find{
            it.posicao.equals(pacoteMoverItem.para.posicao)
        }

        var itemDe = de!!.itemUnicoId;
        var itemPara = para!!.itemUnicoId;

        SlotInventarioService.it.equipaItem(de!!.id,itemPara) //seta item de no para
        SlotInventarioService.it.equipaItem(para!!.id,itemDe) //seta item para no de

        //var moverItem = MoverItem()
        //moverItem.cabecalho = pacoteMoverItem.cabecalho
        //moverItem.para = pacoteMoverItem.de
        //moverItem.de = pacoteMoverItem.para
        //moverItem.nome = pacoteMoverItem.nome

        //envia pra ele mesmo, para confirmar que já pode trocar visualmente os itens
        output.enviarPacote(ConverterPacote.it.objetoParaByteArray(pacoteMoverItem))
    }
}