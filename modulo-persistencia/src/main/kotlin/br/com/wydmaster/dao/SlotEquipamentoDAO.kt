package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.SlotEquipamentoDTO
import br.com.wydmaster.entidade.enum.TagEquipamentoEnum

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class SlotEquipamentoDAO {

    it;

    val tabela = "slotEquipamento"

    private fun inserir(slot:SlotEquipamentoDTO):SlotEquipamentoDTO{
        var sql = "INSERT INTO $tabela (personagem_id,tag) VALUES(${slot.personagemId},'${slot.tag.id}')"

        var result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        slot.id = result!!

        return slot
    }

    fun criaPersonagem(personagemId:Int):ArrayList<SlotEquipamentoDTO>{

        var slots = ArrayList<SlotEquipamentoDTO>()
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.ELMO)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.PEITO)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.CALCA)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.LUVA)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.BOTA)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.ARMA1)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.ARMA2)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.AMULETO)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.BRINCO)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.ORB)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.ANK)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.MONTARIA)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.TRAJE)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.FADA)))
        slots.add(inserir(SlotEquipamentoDTO(0,personagemId,0,TagEquipamentoEnum.CAPA)))


        return slots
    }

    fun buscarSlots(personagemId:Int):ArrayList<SlotEquipamentoDTO>{
        var sql = "SELECT se.id, se.itemUnico_id, se.tag," +
                "iu.item_id, iu.refinacao, iu.anct, " +
                "iu.atributo_id, iu.atributo_id1, iu.atributo_id2, iu.atributo_id3, iu.atributo_id4," +
                "iu.atributoValor, iu.atributoValor1, iu.atributoValor2, iu.atributoValor3, iu.atributoValor4 " +
                "FROM ${tabela} se LEFT JOIN itemUnico iu ON iu.id = se.itemUnico_id WHERE personagem_id = ${personagemId}"

        var result = MysqlHelper.it.executarSelect(sql)

        var retorno = ArrayList<SlotEquipamentoDTO>()

        while(result!!.next()){

            var id= result.getInt("se.id")
            var itemUnicoId= result.getInt("se.itemUnico_id")
            var tag =TagEquipamentoEnum.values().find { it.id.equals(result.getString("se.tag")) }!!

            var itemId = result.getInt("iu.item_id")
            var refinacao= result.getByte("iu.refinacao")
            var anct= result.getByte("iu.anct")

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


            var slot = SlotEquipamentoDTO(id, personagemId,itemUnicoId, tag )
            slot.itemUnico = itemUnico

            retorno.add(slot)
        }

        return retorno
    }

    fun equipaItem(idSlot:Int, idItem:Int?):Boolean{
        val sql = "UPDATE $tabela SET itemUnico_id = ? WHERE id = ?"
        var pstmt= MysqlHelper.it.preparar(sql)
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
        val sql = "DELETE FROM $tabela WHERE personagem_id = $personagemId"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }


}