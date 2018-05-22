package br.com.wydmaster.service

import br.com.wydmaster.dao.ChaoItemDAO
import br.com.wydmaster.dao.ClasseDAO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ClasseService {
    it;

    val dao = ClasseDAO.it

}