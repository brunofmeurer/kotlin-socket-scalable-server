package br.com.wydmaster.core.logica

import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.*
import br.com.wydmaster.estrutura.pacotes.SelecionarPersonagem
import br.com.wydmaster.estrutura.pacotes.comum.AddItem
import br.com.wydmaster.estrutura.pacotes.comum.Equipamento
import br.com.wydmaster.estrutura.pacotes.comum.Inventario
import br.com.wydmaster.service.*
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecSelecionarPersonagem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){
        val selecionarPersonagem= ConverterPacote.it.byteArrayParaObjeto<SelecionarPersonagem>(pacote.pacote)

        val personagemDTO = PersonagemService.it.buscarPorNome(selecionarPersonagem.nome)

        val personagemPacote = MeuPersonagem()

        personagemPacote.equipamentos.clear()
        personagemPacote.equipamentos = getEquipamentos(personagemDTO.id)
        personagemPacote.inventario.clear()
        personagemPacote.inventario = getItensInventario(personagemDTO.id)
        personagemPacote.cabecalho.token = pacote.cabecalho.token
        personagemPacote.personagemDTO = personagemDTO

        val pacote = ConverterPacote.it.objetoParaByteArray(personagemPacote)

        ContaPersonagemService.it.logado(personagemDTO.id)

        output.enviarPacote(pacote)

        enviarPersonagensEmJogo(output)
    }

    private fun getEquipamentos(personagemId:Int):ArrayList<Equipamento>{
        val retorno = ArrayList<Equipamento>()
        SlotEquipamentoService.it.buscarSlots(personagemId).forEach {
            var equipamento = Equipamento()
            equipamento.idItem = it.itemUnico!!.itemId
            equipamento.refinacao = it.itemUnico!!.refinacao
            equipamento.tag = it.tag.name
            equipamento.adds = getAdds(it.itemUnico!!)
            retorno.add(equipamento)
        }
        return retorno
    }

    private fun getItensInventario(personagemId:Int):ArrayList<Inventario>{
        val retorno = ArrayList<Inventario>()
        SlotInventarioService.it.buscarSlots(personagemId).forEach {
            val inventario = Inventario()
            inventario.idItem = it.itemUnico!!.itemId
            inventario.refinacao = it.itemUnico!!.refinacao
            inventario.posicao = it.posicao
            inventario.adds = getAdds(it.itemUnico!!)
            retorno.add(inventario)
        }
        return retorno
    }

    private fun getAdds(item:ItemUnicoDTO):ArrayList<AddItem>{

        var retorno = ArrayList<AddItem>()

        var add = AddItem()
        add.idAdd = item.atributoId
        add.valorAdd = item.atributoValor

        retorno.add(add)

        add = AddItem()
        add.idAdd = item.atributoId1
        add.valorAdd = item.atributoValor1

        retorno.add(add)

        add = AddItem()
        add.idAdd = item.atributoId2
        add.valorAdd = item.atributoValor2

        retorno.add(add)

        return retorno
    }

    private fun enviarPersonagensEmJogo(output: PacoteOutputStream){

        ContaService.it.buscarTokenConectados().forEach { //enviar para quem?
                val token = it
                val outroPersonagem = OutroPersonagem()
                outroPersonagem.cabecalho.token = token

            PersonagemService.it.buscarPersonagensLogados()
                .filter{ !it.token.equals(token) }
                .forEach{

                    outroPersonagem.personagemDTO =it.personagem
                    outroPersonagem.equipamentos.clear()
                    outroPersonagem.equipamentos = getEquipamentos(it.personagem.id)

                    output.enviarPacote(ConverterPacote.it.objetoParaByteArray(outroPersonagem))

                }
            }
    }
}