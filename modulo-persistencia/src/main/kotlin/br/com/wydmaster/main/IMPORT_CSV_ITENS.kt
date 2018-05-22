package br.com.wydmaster.main

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.ItemDTO
import br.com.wydmaster.service.ItemService
import java.io.File
import java.util.*

/**
 * Criado por bruno-meurer em 24/09/17.
 */

fun main(args: Array<String>) {

    MysqlHelper.it.conectar()
    println("---> Importando ITEM LIST ")
    val scanner = Scanner(File("/home/bruno-meurer/pessoal/wyd master/modulo-persistencia/src/main/resources/itemList.csv"), "UTF-8")
    scanner.useDelimiter(";")

    ItemService.it.limparTabela()

    scanner.nextLine(); //começa da segunda linha, pois a primeira é cabeçalho
    while (scanner.hasNextLine()) {


        val linha = scanner.nextLine().split(";")
        var id = linha.get(0).toInt()
        var nome = linha.get(1).trim()
        var tipoItem = linha.get(2).toShort()
        var tipo = linha.get(3).trim()
        var classes = linha.get(4).trim()
        var atributo = linha.get(5).trim()
        var valor= linha.get(6).trim()
        var atributo1 = linha.get(7).trim()
        var valor1= linha.get(8).trim()
        var atributo2 = linha.get(9).trim()
        var valor2= linha.get(10).trim()
        var atributo3 = linha.get(11).trim()
        var valor3= linha.get(12).trim()
        var atributo4 = linha.get(13).trim()
        var valor4= linha.get(14).trim()

        var level = linha.get(15).toShort()
        var forca = linha.get(16).toShort()
        var inteligencia = linha.get(17).toShort()
        var destreza = linha.get(18).toShort()
        var constituicao = linha.get(19).toShort()

        println(" Importando item: ${nome} ")

        var item = ItemDTO()
        item.id = id
        item.nome = nome
        item.tipoItemId = tipoItem
        item.tipo = tipo
        item.atributoId = atributo.toShort()
        item.atributoId1 = atributo1.toShort()
        item.atributoId2 = atributo2.toShort()
        item.atributoId3 = atributo3.toShort()
        item.atributoId4 = atributo4.toShort()

        item.atributoValor = valor.toShort()
        item.atributoValor1 = valor1.toShort()
        item.atributoValor2 = valor2.toShort()
        item.atributoValor3 = valor3.toShort()
        item.atributoValor4 = valor4.toShort()

        item.restricaoClasse = classes

        item.restricaoNivel = level
        item.restricaoFor = forca
        item.restricaoInt = inteligencia
        item.restricaoDes = destreza
        item.restricaoCons = constituicao

        ItemService.it.inserir(item)

    }
    scanner.close()

}