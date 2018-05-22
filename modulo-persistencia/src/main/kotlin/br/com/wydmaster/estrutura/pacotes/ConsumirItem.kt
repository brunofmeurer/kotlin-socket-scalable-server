package br.com.wydmaster.estrutura.pacotes

import br.com.wydmaster.estrutura.pacotes.anotacoes.Posicao
import br.com.wydmaster.estrutura.pacotes.anotacoes.Tamanho
import br.com.wydmaster.estrutura.pacotes.comum.Equipamento
import br.com.wydmaster.estrutura.pacotes.comum.Inventario
import br.com.wydmaster.estrutura.pacotes.dependencias.Cabecalho

/**
 * Criado por bruno-meurer em 21/10/17.
 */
class ConsumirItem {
    @Posicao(1)
    var cabecalho: Cabecalho = Cabecalho(14)
    @Posicao(3)
    var inventario = Inventario()
    @Posicao(5)
    @Tamanho(45)
    var nome = ""
}