package br.com.wydmaster.service

import br.com.wydmaster.dao.SlotEquipamentoDAO
import br.com.wydmaster.dao.SlotInventarioDAO
import br.com.wydmaster.entidade.SlotEquipamentoDTO
import br.com.wydmaster.entidade.SlotInventarioDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class SlotInventarioService {
    it;

    val dao = SlotInventarioDAO.it

    fun criaPersonagem(personagemId:Int):ArrayList<SlotInventarioDTO>{
        return dao.criaPersonagem(personagemId)
    }

    fun buscarSlots(personagemId: Int):ArrayList<SlotInventarioDTO>{
        return dao.buscarSlots(personagemId)
    }

    fun equipaItem(idSlot:Int, idItem:Int?):Boolean{
        return dao.equipaItem(idSlot, idItem)
    }

    fun deletar(personagemId:Int){

        this.buscarSlots(personagemId).forEach {
            ItemUnicoService.it.deletar(it.id)
        }

        dao.deletar(personagemId)
    }
}