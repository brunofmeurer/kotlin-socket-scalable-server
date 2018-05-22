package br.com.wydmaster.service


import br.com.wydmaster.dao.PersonagemDAO
import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.entidade.auxiliar.PersonagemLogadoDTO
import br.com.wydmaster.estrutura.pacotes.comum.Coordenadas

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class PersonagemService {
    it;

    val dao = PersonagemDAO.it

    fun inserir(personagem: PersonagemDTO): PersonagemDTO?{
        return dao.inserir(personagem)
    }

    fun buscarPersonagensDaConta(token:String):ArrayList<PersonagemDTO>{
        return dao.buscarPersonagensDaConta(token)
    }

    fun buscarPersonagensLogados():ArrayList<PersonagemLogadoDTO>{
        return dao.buscarPersonagensLogados()
    }

    fun buscarPersonagemLogado(token:String):PersonagemDTO{
        return dao.buscarPersonagemLogado(token)
    }

    fun buscarIdNomePersonagemLogado(token:String):PersonagemDTO{
        return dao.buscarIdNomePersonagemLogado(token)
    }


    fun buscarPorId(id:Int):PersonagemDTO{
        return dao.buscarPorId(id)
    }

    fun buscarPorNome(nome:String):PersonagemDTO{
        return dao.buscarPorNome(nome)
    }

    fun verificarSeExisteNome(nome:String):Boolean{
        return dao.verificarSeExisteNome(nome)
    }

    fun deletar(id:Int){ //deletar ContaPersonagem Também
        ContaPersonagemService.it.deletar(id)
        SlotEquipamentoService.it.deletar(id)
        SlotInventarioService.it.deletar(id)
        return dao.deletar(id)
    }

    fun andar(id:Int, coordenadas:Coordenadas){
        dao.andar(id, coordenadas)
    }
}