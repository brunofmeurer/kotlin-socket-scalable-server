package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.global.idioma.Strings
import br.com.wydmaster.core.logica.util.MensagemUtil
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.ConsumirItem
import br.com.wydmaster.estrutura.pacotes.EquiparItem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.PersonagemService
import br.com.wydmaster.service.SlotEquipamentoService
import br.com.wydmaster.service.SlotInventarioService
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecConsumirItem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){

        var pacoteEquiparItem = ConverterPacote.it.byteArrayParaObjeto<ConsumirItem>(pacote.pacote)
        var personagem = PersonagemService.it.buscarIdNomePersonagemLogado(pacoteEquiparItem.cabecalho.token)

        //verifica se usuario é o mesmo logado
        if(!personagem.nome.equals(pacoteEquiparItem.nome)){
            var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PERSONAGEM_INVALIDO"))
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
            return
        }

        //TODO Validar se o item é realmente valido para consumir


        var slotsInventario = SlotInventarioService.it.buscarSlots(personagem.id)
        var slotInventario = slotsInventario.find{
            it.posicao.equals(pacoteEquiparItem.inventario.posicao)
        }

        var inventario = slotInventario!!.itemUnicoId;

        SlotInventarioService.it.equipaItem(slotInventario!!.id,0) //seta equip no inventario

        //envia pra ele mesmo, para confirmar que já pode trocar visualmente os itens
        output.enviarPacote(ConverterPacote.it.objetoParaByteArray(pacoteEquiparItem))
    }
}