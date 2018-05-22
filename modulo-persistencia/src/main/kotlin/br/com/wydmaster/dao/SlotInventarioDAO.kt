package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO
import br.com.wydmaster.entidade.SlotInventarioDTO
import br.com.wydmaster.entidade.enum.TagEquipamentoEnum

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class SlotInventarioDAO {

    it;

    val tabela = "slotInventario"

    private fun inserir(slot: SlotInventarioDTO): SlotInventarioDTO {
        var sql = "INSERT INTO $tabela (personagem_id,posicao) VALUES(${slot.personagemId},${slot.posicao})"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        slot.id = result!!

        return slot
    }

    fun criaPersonagem(personagemId:Int):ArrayList<SlotInventarioDTO>{

        var slots = ArrayList<SlotInventarioDTO>()

        for (i in 0 until 60) {
            slots.add(inserir(SlotInventarioDTO(0,personagemId,0, i)))
        }

        return slots
    }

    fun buscarSlots(personagemId: Int):ArrayList<SlotInventarioDTO>{
        val sql = "SELECT si.id, si.itemUnico_id, si.posicao, si.personagem_id, " +
                "iu.item_id, iu.refinacao, iu.anct, " +
                "iu.atributo_id, iu.atributo_id1, iu.atributo_id2, iu.atributo_id3, iu.atributo_id4," +
                "iu.atributoValor, iu.atributoValor1, iu.atributoValor2, iu.atributoValor3, iu.atributoValor4 " +
                "FROM ${tabela} si LEFT JOIN itemUnico iu ON iu.id = si.itemUnico_id WHERE si.personagem_id = ${personagemId}"

        val result = MysqlHelper.it.executarSelect(sql)

        val retorno = ArrayList<SlotInventarioDTO>()

        while(result!!.next()){

            val id= result.getInt("si.id")
            val itemUnicoId= result.getInt("si.itemUnico_id")
            val posicao = result.getInt("si.posicao")

            val itemId = result.getInt("iu.item_id")
            val refinacao= result.getByte("iu.refinacao")
            val anct= result.getByte("iu.anct")

            val atributoId = result.getShort("iu.atributo_id")
            val atributoId1 = result.getShort("iu.atributo_id1")
            val atributoId2 = result.getShort("iu.atributo_id2")
            val atributoId3 = result.getShort("iu.atributo_id3")
            val atributoId4 = result.getShort("iu.atributo_id4")

            val atributoValor = result.getShort("iu.atributoValor")
            val atributoValor1 = result.getShort("iu.atributoValor1")
            val atributoValor2 = result.getShort("iu.atributoValor2")
            val atributoValor3 = result.getShort("iu.atributoValor3")
            val atributoValor4 = result.getShort("iu.atributoValor4")

            val itemUnico = ItemUnicoDTO()

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

            val personagemId = result.getInt("si.personagem_id")

            val slot = SlotInventarioDTO(id, personagemId, itemUnicoId, posicao)
            slot.itemUnico = itemUnico

            retorno.add(slot)
        }

        return retorno
    }

    fun equipaItem(idSlot:Int, idItem:Int?):Boolean{
        val sql = "UPDATE $tabela SET itemUnico_id = ? WHERE id = ?"
        val pstmt= MysqlHelper.it.preparar(sql)
        if(idItem != null && idItem > 0)
            pstmt!!.setInt(1, idItem)
        else
            pstmt!!.setNull(1,java.sql.Types.INTEGER)

        pstmt!!.setInt(2, idSlot)

        pstmt!!.executeUpdate()
        pstmt.close()

        return true
    }

    fun deletar(personagemId:Int){
        val sql = "DELETE FROM $tabela WHERE inventario_id = $personagemId"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }
}