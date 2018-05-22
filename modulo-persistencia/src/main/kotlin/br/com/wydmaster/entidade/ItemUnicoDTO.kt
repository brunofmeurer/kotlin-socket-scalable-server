package br.com.wydmaster.entidade

/**
 * Criado por bruno-meurer em 02/09/17.
 */
class ItemUnicoDTO {

    constructor(id:Int, itemId:Int, refinacao:Byte, anct:Byte){
        this.id = id
        this.itemId = itemId
        this.refinacao = refinacao
        this.anct = anct
    }
    constructor(){}

    var id:Int = 0
    var itemId:Int = 0
    var refinacao:Byte = 0
    var anct:Byte = 0
    var atributoId:Short = 0
    var atributoId1:Short = 0
    var atributoId2:Short = 0
    var atributoId3:Short = 0
    var atributoId4:Short = 0
    var atributoValor:Short = 0
    var atributoValor1:Short = 0
    var atributoValor2:Short = 0
    var atributoValor3:Short = 0
    var atributoValor4:Short = 0
}