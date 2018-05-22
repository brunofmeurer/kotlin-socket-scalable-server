package br.com.wydmaster.core.logica.util

import br.com.wydmaster.entidade.ItemDTO
import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO
import br.com.wydmaster.entidade.enum.AtributoEnum
import br.com.wydmaster.entidade.enum.TagEquipamentoEnum
import br.com.wydmaster.estrutura.pacotes.SelecaoPersonagem
import br.com.wydmaster.service.ItemService
import br.com.wydmaster.service.SlotEquipamentoService

/**
 * Criado por bruno-meurer em 24/09/17.
 */
enum class PersonagemUtil {
    it;

    //retorna itens equipados
    fun getItens(retorno: SelecaoPersonagem, personagem: PersonagemDTO){
        var slots = SlotEquipamentoService.it.buscarSlots(personagem.id)

        slots.forEach {
            when(it.tag){
                TagEquipamentoEnum.ELMO->{
                    retorno.elmo = it.itemUnico!!.itemId
                    retorno.elmoRef = it.itemUnico!!.refinacao
                    retorno.elmoAnct = it.itemUnico!!.anct
                }

                TagEquipamentoEnum.PEITO->{
                    retorno.peito = it.itemUnico!!.itemId
                    retorno.peitoRef = it.itemUnico!!.refinacao
                    retorno.peitoAnct = it.itemUnico!!.anct
                }

                TagEquipamentoEnum.CALCA->{
                    retorno.calca = it.itemUnico!!.itemId
                    retorno.calcaRef = it.itemUnico!!.refinacao
                    retorno.calcaAnct = it.itemUnico!!.anct
                }

                TagEquipamentoEnum.LUVA->{
                    retorno.luva = it.itemUnico!!.itemId
                    retorno.luvaRef = it.itemUnico!!.refinacao
                    retorno.luvaAnct = it.itemUnico!!.anct
                }

                TagEquipamentoEnum.BOTA->{
                    retorno.bota = it.itemUnico!!.itemId
                    retorno.botaRef = it.itemUnico!!.refinacao
                    retorno.botaAnct = it.itemUnico!!.anct
                }
            }
        }

        salvarAtributos(slots)
    }

    fun salvarAtributos(slots:ArrayList<SlotEquipamentoDTO>){
        var defesa = calcularAtributo(slots, AtributoEnum.DEFESA)
        var ataque = calcularAtributo(slots, AtributoEnum.ATAQUE)
        var critico= calcularAtributo(slots, AtributoEnum.CRITICO)
        var ataqueMagico = calcularAtributo(slots, AtributoEnum.ATAQUE_MAGICO)

        //TODO salvar no DB
    }

    fun calcularAtributo(slots:ArrayList<SlotEquipamentoDTO>, atributo:AtributoEnum):Float{
        var total = 0f
        slots.forEach {

            var item:ItemDTO? = null

            if(it.itemUnicoId != 0)
                item = ItemService.it.selecionarPorId(it.itemUnico!!.itemId)

            if(it.itemUnico!!.atributoId.equals(atributo.ordinal)){
                total += it.itemUnico!!.atributoValor + (0.1f * it.itemUnico!!.refinacao)
            }

            if(it.itemUnico!!.atributoId1.equals(atributo.ordinal)){
                total += it.itemUnico!!.atributoValor1 + (0.1f * it.itemUnico!!.refinacao)
            }

            if(it.itemUnico!!.atributoId2.equals(atributo.ordinal)){
                total += it.itemUnico!!.atributoValor2 + (0.1f * it.itemUnico!!.refinacao)
            }

            if(it.itemUnico!!.atributoId3.equals(atributo.ordinal)){
                total += it.itemUnico!!.atributoValor3 + (0.1f * it.itemUnico!!.refinacao)
            }

            if(it.itemUnico!!.atributoId4.equals(atributo.ordinal)){
                total += it.itemUnico!!.atributoValor4 + (0.1f * it.itemUnico!!.refinacao)
            }

            if(item != null){

                if(item.atributoId.equals(atributo.ordinal)){
                    total += item.atributoValor + (0.1f * it.itemUnico!!.refinacao)
                }

                if(item.atributoId1.equals(atributo.ordinal)){
                    total += item.atributoValor1 + (0.1f * it.itemUnico!!.refinacao)
                }

                if(item.atributoId2.equals(atributo.ordinal)){
                    total += item.atributoValor2 + (0.1f * it.itemUnico!!.refinacao)
                }

                if(item.atributoId3.equals(atributo.ordinal)){
                    total += item.atributoValor3 + (0.1f * it.itemUnico!!.refinacao)
                }

                if(item.atributoId4.equals(atributo.ordinal)){
                    total += item.atributoValor4 + (0.1f * it.itemUnico!!.refinacao)
                }
            }
        }

        return total
    }
}