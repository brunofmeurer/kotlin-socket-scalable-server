package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.global.idioma.Strings
import br.com.wydmaster.core.logica.util.MensagemUtil
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.Andar
import br.com.wydmaster.estrutura.pacotes.Chat
import br.com.wydmaster.estrutura.pacotes.EquiparItem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.service.*
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecEquiparItem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){

        var pacoteEquiparItem = ConverterPacote.it.byteArrayParaObjeto<EquiparItem>(pacote.pacote)
        var personagem = PersonagemService.it.buscarPersonagemLogado(pacoteEquiparItem.cabecalho.token)

        //verifica se usuario é o mesmo logado
        if(!personagem.nome.equals(pacoteEquiparItem.nome)){
            var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PERSONAGEM_INVALIDO"))
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
            return
        }

        //slots busca
        var slotsEquipamentos = SlotEquipamentoService.it.buscarSlots(personagem.id)
        var slotEquipamento = slotsEquipamentos.find{
            it.tag.id.equals(pacoteEquiparItem.equipamento.tag)
        }

        var slotsInventario = SlotInventarioService.it.buscarSlots(personagem.id)
        var slotInventario = slotsInventario.find{
            it.posicao.equals(pacoteEquiparItem.inventario.posicao)
        }

        var equipamento = slotEquipamento!!.itemUnicoId
        var inventario = slotInventario!!.itemUnicoId

        //se inventario for igual a zero quer dizer que é só desequipar sem precisar validar
        if(inventario != 0) {
            var item = ItemService.it.selecionarPorId(slotInventario.itemUnico!!.itemId)

            if(!item.restricaoClasse.contains(personagem.classeId.toString())){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("IEM_NÃO_PERMITIDO_PARA_CLASSE"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            if(personagem.nivel < item.restricaoNivel){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("NIVEL_INSUFICIENTE_PARA_ITEM"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            if(personagem.forca < item.restricaoFor){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PONTOS_EM_FORCA_INSUFICIENTES"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            if(personagem.inteligencia < item.restricaoInt){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PONTOS_EM_INT_INSUFICIENTES"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            if(personagem.destreza < item.restricaoDes){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PONTOS_EM_DES_INSUFICIENTES"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            if(personagem.constituicao < item.restricaoCons){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("PONTOS_EM_CONS_INSUFICIENTES"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }

            //verifica tipo do item e onde está sendo equipado
            if(item.tipoItemId != slotEquipamento.tag.numDb){
                var mensagem = MensagemUtil.it.criaMensagem(pacote, Strings.it.get("SLOT_INVALIDO"))
                output.enviarPacote(ConverterPacote.it.objetoParaByteArray(mensagem))
                return
            }
        }

        //salva no banco equipada
        SlotEquipamentoService.it.equipaItem(slotEquipamento!!.id,inventario) //seta item do inventario no equip
        SlotInventarioService.it.equipaItem(slotInventario!!.id,equipamento) //seta equip no inventario

        var objeto = ConverterPacote.it.byteArrayParaObjeto<EquiparItem>(ConverterPacote.it.objetoParaByteArray(pacoteEquiparItem));

        //inverte itens para enviar no pacote
        objeto.inventario.idItem = slotEquipamento!!.itemUnico!!.itemId
        objeto.inventario.anct = slotEquipamento!!.itemUnico!!.anct
        objeto.inventario.refinacao = slotEquipamento!!.itemUnico!!.refinacao

        objeto.equipamento.idItem = slotInventario!!.itemUnico!!.itemId
        objeto.equipamento.anct = slotInventario!!.itemUnico!!.anct
        objeto.equipamento.refinacao = slotInventario!!.itemUnico!!.refinacao

        enviarPacoteEquipar(objeto, output)
    }

    private fun enviarPacoteEquipar(equipar: EquiparItem, output: PacoteOutputStream){

        ContaService.it.buscarTokenConectados().forEach {
            equipar.cabecalho.token = it
            output.enviarPacote(ConverterPacote.it.objetoParaByteArray(equipar))
        }
    }
}