package br.com.wydmaster.estrutura.pacotes.comum

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.dependencias.ObjetoPacote

/**
 * Criado por bruno-meurer em 16/10/17.
 */
class Quaternion: ObjetoPacote{

    constructor(){}
    constructor(rotY:Float, rotX:Float, rotZ:Float, rotW:Float){
        this.rotY = rotY
        this.rotX = rotX
        this.rotZ = rotZ
        this.rotW = rotW

    }


    @Posicao(1)
    var rotY:Float = 0f

    @Posicao(2)
    var rotX:Float = 0f

    @Posicao(3)
    var rotZ:Float = 0f

    @Posicao(4)
    var rotW:Float = 0f

}