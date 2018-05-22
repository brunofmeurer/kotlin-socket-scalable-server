package br.com.wydmaster.service

import br.com.wydmaster.dao.ContaPersonagemDAO
import br.com.wydmaster.dao.SlotEquipamentoDAO
import br.com.wydmaster.entidade.ContaPersonagemDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class SlotEquipamentoService {
    it;

    val dao = SlotEquipamentoDAO.it

    fun criaPersonagem(personagemId:Int):ArrayList<SlotEquipamentoDTO>{
        return dao.criaPersonagem(personagemId)
    }

    fun buscarSlots(personagemId: Int):ArrayList<SlotEquipamentoDTO>{
        return dao.buscarSlots(personagemId)
    }

    fun equipaItem(idSlot:Int, idItem:Int?):Boolean{
        return dao.equipaItem(idSlot, idItem)
    }

    fun deletar(personagemId:Int){
        dao.deletar(personagemId)

        this.buscarSlots(personagemId).forEach {
            ItemUnicoService.it.deletar(it.id)
        }
    }
}