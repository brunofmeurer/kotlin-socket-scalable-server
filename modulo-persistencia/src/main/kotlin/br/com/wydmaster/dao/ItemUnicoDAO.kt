package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ItemUnicoDAO {

    it;

    val tabela = "itemUnico"

    fun inserir(item: ItemUnicoDTO): ItemUnicoDTO { //criar item, este metodo deve ser controlado
        var sql = "INSERT INTO $tabela " +
                "(item_id, refinacao, anct, " +
                "atributo_id,atributo_id1, atributo_id2, atributo_id3, atributo_id4," +
                "atributoValor, atributoValor1, atributoValor2, atributoValor3, atributoValor4) " +
                "VALUES(${item.itemId}, ${item.refinacao},${item.anct}," +
                "${item.atributoId}, ${item.atributoId1}, ${item.atributoId2}, ${item.atributoId3}, ${item.atributoId4}," +
                "${item.atributoValor}, ${item.atributoValor1}, ${item.atributoValor2}, ${item.atributoValor3}, ${item.atributoValor4})"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        item.id = result!!

        return item
    }

    fun deletar(id:Int){
        val sql = "DELETE FROM $tabela WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}