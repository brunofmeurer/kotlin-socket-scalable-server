package br.com.wydmaster.estrutura

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class TipoPacoteEnum {
    LOGIN(1,false,true),
    CHAT(2, true, true),
    NUMERO_PERSONAGENS(3,true,false),
    CRIAR_PERSONAGEM(4,false,true),
    PERSONAGEM_SELECAO(5,true,false),
    MENSAGEM(6,true,true),
    DELETAR_PERSONAGEM(7,false,true),
    SELECIONAR_PERSONAGEM(8,false, true),
    PERSONAGEM(9,true,false),
    ANDAR(10,true,true),
    OUTRO_PERSONAGEM(11,true,false),
    DESCONECTA_PERSONAGEM(12,true,true),
    EQUIPAR_ITEM(13,true,true),
    CONSUMIR_ITEM(14,true,true),
    DROPAR_ITEM(15,true,true),
    MOVER_ITEM(16,true,true),
    ATACAR(17,false,true),
    SOLICITAR_TROCA(18,true,true),
    LEVAR_DANO(19,true,false),
    SOLICITAR_GRUPO(20,false,true),
    SURGIR_MONSTRO(21,true,false),
    ANDAR_MONSTRO(22,true,false),
    ATACAR_MONSTRO(23,true,true),
    MONSTRO_ATACAR(24,true,true)

    ;

    var id = 0
    var envia = false
    var recebe = false
    constructor(id:Int, envia:Boolean, recebe:Boolean){
        this.id = id
        this.envia = envia
        this.recebe = recebe
    }
}