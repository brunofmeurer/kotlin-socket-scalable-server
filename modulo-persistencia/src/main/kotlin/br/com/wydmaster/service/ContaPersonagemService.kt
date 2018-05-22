package br.com.wydmaster.service

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.dao.ContaPersonagemDAO
import br.com.wydmaster.dao.PersonagemDAO
import br.com.wydmaster.entidade.ContaPersonagemDTO
import br.com.wydmaster.entidade.PersonagemDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ContaPersonagemService {
    it;

    val dao = ContaPersonagemDAO.it

    fun inserir(contaPersonagem: ContaPersonagemDTO): ContaPersonagemDTO{
        return dao.inserir(contaPersonagem)
    }

    fun buscarQuantidade(token:String):Int{
        return dao.buscaQuantidade(token)
    }

    fun deletar(personagemId:Int){
        return dao.deletar(personagemId)
    }

    fun logado(personagemId:Int){
        return dao.logado(personagemId)
    }

    fun deslogado(token:String){
        return dao.deslogado(token)
    }

    fun desconectarTodos(){
        dao.desconectarTodos()
    }
}