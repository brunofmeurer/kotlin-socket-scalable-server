package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ChaoItemDTO
import br.com.wydmaster.entidade.ItemUnicoDTO

/**
 * Criado por bruno-meurer em 23/10/17.
 */
enum class ChaoItemDAO {

    it;

    val tabela = "chaoItem"

    fun inserir(chaoItemDTO: ChaoItemDTO): ChaoItemDTO {
        val sql = "INSERT INTO ${tabela} (itemUnico_id, posicaoX, posicaoY, posicaoZ) VALUES(${chaoItemDTO.itemUnicoId},${chaoItemDTO.posicaoX},${chaoItemDTO.posicaoY},${chaoItemDTO.posicaoZ})"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        chaoItemDTO.id = result!!

        return chaoItemDTO
    }

    fun deletar(id:Int){
        val sql = "DELETE FROM $tabela WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun buscarItensNoChao():ArrayList<ChaoItemDTO>{
        val sql = "SELECT ci.id, ci.posicaoX, ci.posicaoY, ci.posicaoZ, ci.itemUnico_id, " +
                "iu.item_id, iu.refinacao, iu.anct, " +
                "iu.atributo_id, iu.atributo_id1, iu.atributo_id2, iu.atributo_id3, iu.atributo_id4," +
                "iu.atributoValor, iu.atributoValor1, iu.atributoValor2, iu.atributoValor3, iu.atributoValor4 "
                "FROM ${tabela} ci, itemUnico iu WHERE iu.id = ci.itemUnico_id"

        var retorno = ArrayList<ChaoItemDTO>()

        var result = MysqlHelper.it.executarSelect(sql)
        while(result!!.next()){
            var id = result.getInt("ci.id")
            var px = result.getFloat("ci.posicaoX")
            var py = result.getFloat("ci.posicaoY")
            var pz = result.getFloat("ci.posicaoZ")
            var itemUnicoId = result.getInt("ci.itemUnico_id")

            var itemId = result.getInt("iu.itemId")
            var refinacao = result.getByte("iu.refinacao")
            var anct = result.getByte("iu.anct")
            var atributoId = result.getShort("iu.atributo_id")
            var atributoId1 = result.getShort("iu.atributo_id1")
            var atributoId2 = result.getShort("iu.atributo_id2")
            var atributoId3 = result.getShort("iu.atributo_id3")
            var atributoId4 = result.getShort("iu.atributo_id4")

            var atributoValor = result.getShort("iu.atributoValor")
            var atributoValor1 = result.getShort("iu.atributoValor1")
            var atributoValor2 = result.getShort("iu.atributoValor2")
            var atributoValor3 = result.getShort("iu.atributoValor3")
            var atributoValor4 = result.getShort("iu.atributoValor4")

            var chaoItem = ChaoItemDTO(id,itemUnicoId,px,py,pz)


            var itemUnico = ItemUnicoDTO()
            itemUnico.id = itemUnicoId
            itemUnico.itemId = itemId
            itemUnico.refinacao = refinacao
            itemUnico.anct = anct

            itemUnico.atributoId = atributoId
            itemUnico.atributoId1 = atributoId1
            itemUnico.atributoId2 = atributoId2
            itemUnico.atributoId3 = atributoId3
            itemUnico.atributoId4 = atributoId4

            itemUnico.atributoValor = atributoValor
            itemUnico.atributoValor1 = atributoValor1
            itemUnico.atributoValor2 = atributoValor2
            itemUnico.atributoValor3 = atributoValor3
            itemUnico.atributoValor4 = atributoValor4

            chaoItem.itemUnico = itemUnico

            retorno.add(chaoItem)

        }

        return retorno

    }

    /*fun buscaQuantidade(token:String):Int{
        val sql =
                "SELECT COUNT(cp.id) FROM $tabela cp INNER JOIN conta c ON cp.conta_id = c.id " +
                        "WHERE c.token = '$token'"

        var result = MysqlHelper.it.executarSelect(sql)

        var retorno:Int = 0
        while(result!!.next()){
            retorno= result.getInt(1)
        }
        result.close()

        return retorno;
    }*/
}