package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Equipamento
import br.com.wydmaster.estrutura.pacotes.comum.Inventario
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 28/09/17.
 */
class SugirMonstro {

    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(21)

    @Posicao(2)
    var personagemDTO: PersonagemDTO = PersonagemDTO()

    @Posicao(3)
    var numero:Int = 0
}