package br.com.wydmaster.entidade

import br.com.wydmaster.entidade.enum.TagEquipamentoEnum

/**
 * Criado por bruno-meurer em 02/09/17.
 */
class SlotInventarioDTO(var id:Int, var personagemId:Int, var itemUnicoId:Int, var posicao: Int) {
    var itemUnico: ItemUnicoDTO? = null
}