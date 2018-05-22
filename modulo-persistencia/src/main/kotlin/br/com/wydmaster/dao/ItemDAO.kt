package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ItemDTO
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO
import br.com.wydmaster.entidade.enum.TagEquipamentoEnum

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class ItemDAO {

    it;

    val tabela = "item"

    fun inserir(item:ItemDTO):ItemDTO{
        val sql = "INSERT INTO ${tabela} " +
                "(id, nome, tipoItem_id, tipo, restricaoNivel, restricaoFor, restricaoInt, restricaoDes, restricaoCons, restricaoClasse, restricaoMortal, restricaoArch, restricaoCelestial," +
                "atributo_id, atributo_id1, atributo_id2, atributo_id3, atributo_id4, atributoValor, atributoValor1, atributoValor2, atributoValor3, atributoValor4, descricao) " +
                "VALUES(${item.id},'${item.nome}',${item.tipoItemId}, '${item.tipo}',${item.restricaoNivel},${item.restricaoFor},${item.restricaoInt},${item.restricaoDes},${item.restricaoCons}," +
                "'${item.restricaoClasse}',${item.restricaoMortal},${item.restricaoArch},${item.restricaoCelestial}," +
                "${item.atributoId},${item.atributoId1},${item.atributoId2},${item.atributoId3},${item.atributoId4}," +
                "${item.atributoValor},${item.atributoValor1},${item.atributoValor2},${item.atributoValor3},${item.atributoValor4},'${item.descricao}')"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        item.id = result!!

        return item
    }

    fun selecionarPorId(id:Int):ItemDTO{
        val sql = "SELECT id, nome, tipoItem_id, tipo, restricaoNivel, restricaoFor, restricaoInt, restricaoDes, restricaoCons, restricaoClasse, restricaoMortal, restricaoArch, restricaoCelestial," +
                "atributo_id, atributo_id1, atributo_id2, atributo_id3, atributo_id4, atributoValor, atributoValor1, atributoValor2, atributoValor3, atributoValor4, descricao FROM ${tabela} WHERE id = $id"

        var result = MysqlHelper.it.executarSelect(sql)

        var retorno:ItemDTO = ItemDTO()
        while(result!!.next()){

            retorno.id = result.getInt("id")
            retorno.nome = result.getString("nome")
            retorno.tipoItemId = result.getShort("tipoItem_id")
            retorno.tipo = result.getString("tipo")
            retorno.restricaoNivel = result.getShort("restricaoNivel")
            retorno.restricaoFor = result.getShort("restricaoFor")
            retorno.restricaoInt = result.getShort("restricaoInt")
            retorno.restricaoDes = result.getShort("restricaoDes")
            retorno.restricaoCons = result.getShort("restricaoCons")
            retorno.restricaoClasse = result.getString("restricaoClasse")
            retorno.restricaoMortal = result.getBoolean("restricaoMortal")
            retorno.restricaoArch = result.getBoolean("restricaoArch")
            retorno.restricaoCelestial = result.getBoolean("restricaoCelestial")
            retorno.atributoId = result.getShort("atributo_id")
            retorno.atributoId1 = result.getShort("atributo_id1")
            retorno.atributoId2 = result.getShort("atributo_id2")
            retorno.atributoId3 = result.getShort("atributo_id3")
            retorno.atributoId4 = result.getShort("atributo_id4")
            retorno.atributoValor = result.getShort("atributoValor")
            retorno.atributoValor1 = result.getShort("atributoValor1")
            retorno.atributoValor2 = result.getShort("atributoValor2")
            retorno.atributoValor3 = result.getShort("atributoValor3")
            retorno.atributoValor4 = result.getShort("atributoValor4")
            retorno.descricao = result.getString("descricao")

        }

        return retorno
    }

    fun verificaSeExiste(id:Int):Boolean{
        val sql = "SELECT id FROM ${tabela} WHERE id = $id"

        var result = MysqlHelper.it.executarSelect(sql)

        var retorno:Boolean = false
        while(result!!.next()){
            retorno= true
        }

        return retorno
    }

    private fun alterar(item:ItemDTO){
        val sql = "UPDATE ${tabela} SET nome = '${item.nome}', tipoItem_id = ${item.tipoItemId}, tipo = '${item.tipo}' WHERE id = ${item.id}"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

    }

    fun limparTabela(){
        val sql = "DELETE FROM contaPersonagem WHERE id > 0; " +
                        "DELETE FROM slotEquipamento WHERE id > 0; " +
                        "DELETE FROM slotInventario WHERE id > 0; " +
                        "DELETE FROM itemUnico WHERE id > 0; " +
                        "DELETE FROM carta WHERE id > 0; " +
                        "DELETE FROM grupo WHERE id > 0; " +
                        "DELETE FROM personagem WHERE id > 0; " +
                        "DELETE FROM item WHERE id > 0"

        sql.split(";").forEach {
            var result = MysqlHelper.it.executarInsertUpdateDelete(it)
        }
    }
}