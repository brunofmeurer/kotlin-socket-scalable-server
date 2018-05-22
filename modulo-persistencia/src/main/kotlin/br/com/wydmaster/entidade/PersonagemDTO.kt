package br.com.wydmaster.entidade

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Coordenadas
import br.com.wydmaster.estrutura.pacotes.comum.Quaternion
import br.com.wydmaster.estrutura.pacotes.dependencias.ObjetoPacote

/**
 * Criado por bruno-meurer em 02/09/17.
 */
open class PersonagemDTO : ObjetoPacote(){
    @Posicao(1)
    var id:Int = 0
    @Posicao(2)
    var classeId:Byte = 0
    @Posicao(3)
    @Tamanho(45)
    var nome:String = ""
    @Posicao(4)
    var hpMax:Int = 80
    @Posicao(5)
    var hp:Int = 80
    @Posicao(6)
    var mpMax:Int = 20
    @Posicao(7)
    var mp:Int = 20
    @Posicao(8)
    var forca:Short = 5
    @Posicao(9)
    var inteligencia:Short = 5
    @Posicao(10)
    var destreza:Short = 5
    @Posicao(11)
    var constituicao:Short = 5
    @Posicao(12)
    var aprenderArma:Short = 0
    @Posicao(13)
    var pontos1:Short = 0
    @Posicao(14)
    var pontos2:Short = 0
    @Posicao(15)
    var pontosDeSkill1:Short = 0
    @Posicao(16)
    var pontosDeSkill2:Short = 0
    @Posicao(17)
    var pontosDeSkill3:Short = 0
    @Posicao(18)
    var nivel:Short = 1
    @Posicao(19)
    var xp:Long = 0
    @Posicao(20)
    var PK:Short = 0

    @Posicao(21)
    var pos = Coordenadas(4f,-169f,-96f)

    @Posicao(24)
    var ataque:Short = 10
    @Posicao(25)
    var defesa:Short = 10
    @Posicao(26)
    var ataqueMagico:Short = 0
    @Posicao(27)
    var velocidadeAtaque:Short = 100
    @Posicao(28)
    var critico:Short = 0

    @Posicao(29)
    var velocidade:Short = 1

    @Posicao(30)
    var dinheiro:Long = 0L

    @Posicao(31)
    var quaternion = Quaternion()

}