package br.com.wydmaster.service

import br.com.wydmaster.dao.ItemUnicoDAO
import br.com.wydmaster.dao.SlotEquipamentoDAO
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ItemUnicoService {
    it;

    val dao = ItemUnicoDAO.it

    fun inserir(item: ItemUnicoDTO): ItemUnicoDTO { //criar item, este metodo deve ser controlado
        return dao.inserir(item)
    }

    fun deletar(id:Int){
        return dao.deletar(id)
    }

}