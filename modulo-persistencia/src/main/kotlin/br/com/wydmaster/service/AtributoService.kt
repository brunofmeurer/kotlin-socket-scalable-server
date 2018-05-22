package br.com.wydmaster.service

import br.com.wydmaster.dao.AtributoDAO
import br.com.wydmaster.dao.SlotEquipamentoDAO
import br.com.wydmaster.entidade.SlotEquipamentoDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class AtributoService {
    it;

    val dao = AtributoDAO.it

}