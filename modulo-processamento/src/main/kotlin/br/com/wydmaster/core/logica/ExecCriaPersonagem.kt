package br.com.wydmaster.core.logica

import br.com.wydmaster.core.logica.global.idioma.Strings
import br.com.wydmaster.core.logica.util.PersonagemUtil
import br.com.wydmaster.entidade.ContaPersonagemDTO
import br.com.wydmaster.entidade.ItemUnicoDTO
import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.entidade.enum.ClasseEnum
import br.com.wydmaster.entidade.enum.TagEquipamentoEnum
import br.com.wydmaster.estrutura.ConverterPacote
import br.com.wydmaster.estrutura.pacotes.CriaPersonagem
import br.com.wydmaster.estrutura.pacotes.Mensagem
import br.com.wydmaster.estrutura.pacotes.PacoteGenerico
import br.com.wydmaster.estrutura.pacotes.SelecaoPersonagem
import br.com.wydmaster.service.*
import br.com.wydmaster.stream.PacoteOutputStream

/**
 * Criado por bruno-meurer em 25/08/17.
 */
enum class ExecCriaPersonagem {
    it;

    fun logica(pacote: PacoteGenerico, output: PacoteOutputStream){

        var pacoteCriaPersonagem = ConverterPacote.it.byteArrayParaObjeto<CriaPersonagem>(pacote.pacote)

        var retorno:ByteArray = ByteArray(0)
        if(!verificaNome(pacoteCriaPersonagem.nome))
            retorno = ConverterPacote.it.objetoParaByteArray(criaPersonagem(pacoteCriaPersonagem)) //criado com sucesso
        else {
            retorno = ConverterPacote.it.objetoParaByteArray(criaMensagem(pacote, Strings.it.get("NOME_JA_UTILIZADO")))
            return
        }

        output.enviarPacote(retorno)

    }

    private fun criaPersonagem(pacote:CriaPersonagem): SelecaoPersonagem {
        var personagem = PersonagemDTO()

        personagem.classeId = pacote!!.tipo
        personagem.nome = pacote.nome

        PersonagemService.it.inserir(personagem)
        preparaCriaItens(personagem)
        criaContaPersonagem(pacote, personagem)


        var retorno = SelecaoPersonagem()

        retorno.nome = personagem.nome
        retorno.cabecalho.token = pacote.cabecalho.token
        retorno.classe = personagem.classeId

        PersonagemUtil.it.getItens(retorno, personagem)

        return retorno
    }

    private fun criaContaPersonagem(pacote:CriaPersonagem, personagem:PersonagemDTO){
        var conta = ContaService.it.buscarConta(pacote.cabecalho.token)

        var contaPersonagem = ContaPersonagemDTO(0, personagem.id, false, conta!!.id,"")

        ContaPersonagemService.it.inserir(contaPersonagem)
    }



    private fun preparaCriaItens(personagem:PersonagemDTO){

        //cria slots equipamentos
        var slots = SlotEquipamentoService.it.criaPersonagem(personagem.id)

        //cria slots
        SlotInventarioService.it.criaPersonagem(personagem.id)

        when(personagem.classeId.toInt()){
            ClasseEnum.HUNTRESS.id->{
                slots.forEach {
                    var id = 0
                    when(it.tag){
                        TagEquipamentoEnum.ELMO->{
                            var elmo = ItemUnicoService.it.inserir(ItemUnicoDTO(0,179,0,0))
                            it.itemUnicoId = elmo.id
                            id = elmo.id
                        }

                        TagEquipamentoEnum.PEITO->{
                            var peito = ItemUnicoService.it.inserir(ItemUnicoDTO(0,183,0,0))
                            it.itemUnicoId = peito.id
                            id = peito.id
                        }

                        TagEquipamentoEnum.CALCA->{
                            var calca = ItemUnicoService.it.inserir(ItemUnicoDTO(0,184,0,0))
                            it.itemUnicoId = calca.id
                            id = calca.id
                        }

                        TagEquipamentoEnum.LUVA->{
                            var luva = ItemUnicoService.it.inserir(ItemUnicoDTO(0,185,0,0))
                            it.itemUnicoId = luva.id
                            id = luva.id
                        }

                        TagEquipamentoEnum.BOTA->{
                            var bota = ItemUnicoService.it.inserir(ItemUnicoDTO(0,186,0,0))
                            it.itemUnicoId = bota.id
                            id = bota.id
                        }
                    }

                    if(id != 0)
                        SlotEquipamentoService.it.equipaItem(it.id, id)
                }


            }
            ClasseEnum.BEASTMASTER.id->{
                slots.forEach {
                    var id = 0
                    when(it.tag){
                        TagEquipamentoEnum.ELMO->{
                            var elmo = ItemUnicoService.it.inserir(ItemUnicoDTO(0,56,0,0))
                            it.itemUnicoId = elmo.id
                            id = elmo.id
                        }

                        TagEquipamentoEnum.PEITO->{
                            var peito = ItemUnicoService.it.inserir(ItemUnicoDTO(0,67,0,0))
                            it.itemUnicoId = peito.id
                            id = peito.id
                        }

                        TagEquipamentoEnum.CALCA->{
                            var calca = ItemUnicoService.it.inserir(ItemUnicoDTO(0,68,0,0))
                            it.itemUnicoId = calca.id
                            id = calca.id
                        }

                        TagEquipamentoEnum.LUVA->{
                            var luva = ItemUnicoService.it.inserir(ItemUnicoDTO(0,71,0,0))
                            it.itemUnicoId = luva.id
                            id = luva.id
                        }

                        TagEquipamentoEnum.BOTA->{
                            var bota = ItemUnicoService.it.inserir(ItemUnicoDTO(0,72,0,0))
                            it.itemUnicoId = bota.id
                            id = bota.id
                        }
                    }

                    if(id != 0)
                        SlotEquipamentoService.it.equipaItem(it.id, id)
                }

            }
            ClasseEnum.FOEMA.id->{

                slots.forEach {
                    var id = 0
                    when(it.tag){
                        TagEquipamentoEnum.ELMO->{
                            var elmo = ItemUnicoService.it.inserir(ItemUnicoDTO(0,5,0,0))
                            it.itemUnicoId = elmo.id
                            id = elmo.id
                        }

                        TagEquipamentoEnum.PEITO->{
                            var peito = ItemUnicoService.it.inserir(ItemUnicoDTO(0,34,0,0))
                            it.itemUnicoId = peito.id
                            id = peito.id
                        }

                        TagEquipamentoEnum.CALCA->{
                            var calca = ItemUnicoService.it.inserir(ItemUnicoDTO(0,35,0,0))
                            it.itemUnicoId = calca.id
                            id = calca.id
                        }

                        TagEquipamentoEnum.LUVA->{
                            var luva = ItemUnicoService.it.inserir(ItemUnicoDTO(0,36,0,0))
                            it.itemUnicoId = luva.id
                            id = luva.id
                        }

                        TagEquipamentoEnum.BOTA->{
                            var bota = ItemUnicoService.it.inserir(ItemUnicoDTO(0,37,0,0))
                            it.itemUnicoId = bota.id
                            id = bota.id
                        }
                    }

                    if(id != 0)
                        SlotEquipamentoService.it.equipaItem(it.id, id)
                }

            }
            ClasseEnum.TRANSKNIGHT.id->{
                slots.forEach {
                    var id = 0
                    when(it.tag){
                        TagEquipamentoEnum.ELMO->{
                            var elmo = ItemUnicoService.it.inserir(ItemUnicoDTO(0,121,0,0))
                            it.itemUnicoId = elmo.id
                            id = elmo.id
                        }

                        TagEquipamentoEnum.PEITO->{
                            var peito = ItemUnicoService.it.inserir(ItemUnicoDTO(0,147,0,0))
                            it.itemUnicoId = peito.id
                            id = peito.id
                        }

                        TagEquipamentoEnum.CALCA->{
                            var calca = ItemUnicoService.it.inserir(ItemUnicoDTO(0,148,0,0))
                            it.itemUnicoId = calca.id
                            id = calca.id
                        }

                        TagEquipamentoEnum.LUVA->{
                            var luva = ItemUnicoService.it.inserir(ItemUnicoDTO(0,149,0,0))
                            it.itemUnicoId = luva.id
                            id = luva.id
                        }

                        TagEquipamentoEnum.BOTA->{
                            var bota = ItemUnicoService.it.inserir(ItemUnicoDTO(0,150,0,0))
                            it.itemUnicoId = bota.id
                            id = bota.id
                        }
                    }

                    if(id != 0)
                        SlotEquipamentoService.it.equipaItem(it.id, id)
                }
            }
        }
    }

    private fun verificaNome(nome:String):Boolean{
        return PersonagemService.it.verificarSeExisteNome(nome)
    }

    private fun criaMensagem(pacote:PacoteGenerico, mensagem:String):Mensagem{
        var mens = Mensagem()
        mens.cabecalho.token = pacote.cabecalho.token
        mens.mensagem = mensagem
        return mens
    }
}