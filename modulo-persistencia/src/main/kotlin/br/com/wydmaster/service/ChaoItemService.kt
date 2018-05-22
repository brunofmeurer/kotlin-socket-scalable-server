package br.com.wydmaster.service

import br.com.wydmaster.dao.ChaoItemDAO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ChaoItemService {
    it;

    val dao = ChaoItemDAO.it

}