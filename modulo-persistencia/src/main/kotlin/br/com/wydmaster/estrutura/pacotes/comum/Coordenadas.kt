package br.com.wydmaster.estrutura.pacotes.comum

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.dependencias.ObjetoPacote

/**
 * Criado por bruno-meurer em 16/10/17.
 */
class Coordenadas : ObjetoPacote{

    constructor(posY:Float, posX:Float, posZ:Float){
        this.posY = posY
        this.posX = posX
        this.posZ = posZ
    }

    constructor(){}

    @Posicao(1)
    var posY:Float = 0f

    @Posicao(2)
    var posX:Float = 0f

    @Posicao(3)
    var posZ:Float = 0f
}