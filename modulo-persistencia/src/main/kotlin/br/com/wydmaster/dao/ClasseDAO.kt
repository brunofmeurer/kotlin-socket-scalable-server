package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ClasseDTO

/**
 * Criado por bruno-meurer em 23/10/17.
 */
enum class ClasseDAO {

    it;

    val tabela = "classe"

    fun inserir(classeDTO: ClasseDTO): ClasseDTO{
        val sql = "INSERT INTO ${tabela} () VALUES()"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        classeDTO.id = result!!.toShort()

        return classeDTO
    }
}