package br.com.wydmaster.service

import br.com.wydmaster.dao.ItemDAO
import br.com.wydmaster.entidade.ItemDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ItemService {
    it;

    val dao = ItemDAO.it

    fun inserir(item:ItemDTO){
        dao.inserir(item)
    }

    fun selecionarPorId(id:Int):ItemDTO{
        return dao.selecionarPorId(id)
    }

    fun limparTabela(){
        dao.limparTabela()
    }

}