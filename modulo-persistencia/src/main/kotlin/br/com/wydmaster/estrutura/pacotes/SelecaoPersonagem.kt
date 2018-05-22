package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 27/08/17.
 */
/*Pacote 5*/
class SelecaoPersonagem {

    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(5)

    @Posicao(3)
    @Tamanho(45)
    var nome:String = ""

    @Posicao(4)
    var classe:Byte = 0

    @Posicao(5)
    var elmo:Int = 0

    @Posicao(6)
    var elmoRef:Byte = 0

    @Posicao(7)
    var elmoAnct:Byte = 0

    @Posicao(8)
    var peito:Int = 0

    @Posicao(9)
    var peitoRef:Byte = 0

    @Posicao(10)
    var peitoAnct:Byte = 0

    @Posicao(11)
    var calca:Int = 0

    @Posicao(12)
    var calcaRef:Byte = 0

    @Posicao(13)
    var calcaAnct:Byte = 0

    @Posicao(14)
    var luva:Int = 0

    @Posicao(15)
    var luvaRef:Byte = 0

    @Posicao(16)
    var luvaAnct:Byte = 0

    @Posicao(17)
    var bota:Int = 0

    @Posicao(18)
    var botaRef:Byte = 0

    @Posicao(19)
    var botaAnct:Byte = 0

    @Posicao(20)
    var arma1:Int = 0

    @Posicao(21)
    var arma1Ref:Byte = 0

    @Posicao(22)
    var arma1Anct:Byte = 0

    @Posicao(23)
    var arma2:Int = 0

    @Posicao(24)
    var arma2Ref:Byte = 0

    @Posicao(25)
    var arma2Anct:Byte = 0

    @Posicao(26)
    var capa:Int = 0

    @Posicao(27)
    var capaRef:Byte = 0

    @Posicao(28)
    var capaAnct:Byte = 0
}