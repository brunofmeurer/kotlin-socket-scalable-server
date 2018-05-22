package br.com.wydmaster.entidade

/**
 * Criado por bruno-meurer em 02/09/17.
 */
class ChaoItemDTO(var id:Int,var itemUnicoId:Int,var posicaoX:Float, var posicaoY:Float, var posicaoZ:Float) {
    var itemUnico:ItemUnicoDTO? = null
}