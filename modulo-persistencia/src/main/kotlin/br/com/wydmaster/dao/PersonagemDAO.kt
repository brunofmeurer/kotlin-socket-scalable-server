package br.com.wydmaster.dao

import br.com.wydmaster.connection.MysqlHelper
import br.com.wydmaster.entidade.PersonagemDTO
import br.com.wydmaster.entidade.auxiliar.PersonagemLogadoDTO
import br.com.wydmaster.estrutura.pacotes.comum.Coordenadas
import java.sql.ResultSet

/**
 * Criado por bruno-meurer em 19/08/17.
 */
enum class PersonagemDAO {

    it;

    val tabela = "personagem"

    //var LOG = Logger.getLogger("PacoteEntradaDAO")

    fun inserir(personagem:PersonagemDTO):PersonagemDTO{

        val sql =
                "INSERT INTO ${tabela}\n" +
                "(classe_id, nome, hpMax, hp, mpMax, mp, forca, inteligencia, destreza, constituicao, " +
                        "aprenderArma, pontos1, pontos2, pontosDeSkill1, pontosDeSkill2, pontosDeSkill3, " +
                        "nivel, xp, PK, posicaoX, posicaoY, posicaoZ, velocidade, ataque, defesa, ataqueMagico, " +
                        "velocidadeAtaque, critico, dinheiro, rotacaoY, rotacaoX, rotacaoZ, rotacaoW)\n" +
                "VALUES(${personagem.classeId}," +
                        "'${personagem.nome}'," +
                        "${personagem.hpMax}," +
                        "${personagem.hp}," +
                        "${personagem.mpMax}," +
                        "${personagem.mp}," +
                        "${personagem.forca} ," +
                        "${personagem.inteligencia} ," +
                        "${personagem.destreza} ," +
                        "${personagem.constituicao} ," +
                        "${personagem.aprenderArma} ," +
                        "${personagem.pontos1} ," +
                        "${personagem.pontos2} ," +
                        "${personagem.pontosDeSkill1} ," +
                        "${personagem.pontosDeSkill2} ," +
                        "${personagem.pontosDeSkill3} ," +
                        "${personagem.nivel} ," +
                        "${personagem.xp} ," +
                        "${personagem.PK} ," +
                        "${personagem.pos.posX} ," +
                        "${personagem.pos.posY} ," +
                        "${personagem.pos.posZ} ," +
                        "${personagem.velocidade} ," +
                        "${personagem.ataque} ," +
                        "${personagem.defesa} ," +
                        "${personagem.ataqueMagico} ," +
                        "${personagem.velocidadeAtaque} ," +
                        "${personagem.critico} ," +
                        "${personagem.dinheiro}, " +
                        "${personagem.quaternion.rotY} ," +
                        "${personagem.quaternion.rotX} ," +
                        "${personagem.quaternion.rotZ} ," +
                        "${personagem.quaternion.rotW} );"

        val result = MysqlHelper.it.executarInsertUpdateDelete(sql)

        personagem.id = result!!

        return personagem
    }

    fun verificarSeExisteNome(nome:String):Boolean{
        val sql = "SELECT id FROM ${tabela} WHERE nome = '${nome}'"

        val result = MysqlHelper.it.executarSelect(sql)

        var retorno = false
        while(result!!.next()){
            retorno = true
        }

        return retorno;
    }

    fun buscarPersonagensDaConta(token:String):ArrayList<PersonagemDTO>{
        val retorno = ArrayList<PersonagemDTO>()

        val sql = "SELECT ${getNomeCampos()} FROM contaPersonagem cp " +
                "INNER JOIN personagem p ON cp.personagem_id = p.id " +
                "INNER JOIN conta      c ON cp.conta_id      = c.id " +
                "WHERE c.token = '${token}'"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            val objeto = getObjeto(result)

            retorno.add(objeto)
        }
        result.close()

        return retorno
    }

    fun buscarPersonagemLogado(token:String):PersonagemDTO{
        var objeto = PersonagemDTO()

        val sql = "SELECT ${getNomeCampos()} FROM contaPersonagem cp " +
                "INNER JOIN personagem p ON cp.personagem_id = p.id " +
                "INNER JOIN conta      c ON cp.conta_id      = c.id " +
                "WHERE c.token = '${token}' " +
                "  AND c.logado = true " +
                "  AND cp.logado = true "

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            objeto = getObjeto(result)

        }
        result.close()

        return objeto
    }

    fun buscarIdNomePersonagemLogado(token:String):PersonagemDTO{
        var objeto = PersonagemDTO()

        val sql = "SELECT p.id, p.nome FROM contaPersonagem cp " +
                "INNER JOIN personagem p ON cp.personagem_id = p.id " +
                "INNER JOIN conta      c ON cp.conta_id      = c.id " +
                "WHERE c.token = '${token}' " +
                "  AND c.logado = true " +
        "  AND cp.logado = true "

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            objeto.id = result.getInt("p.id")
            objeto.nome = result.getNString("p.nome")

        }
        result.close()

        return objeto
    }

    fun buscarPorId(id:Int):PersonagemDTO{
        var objeto = PersonagemDTO()

        val sql = "SELECT ${getNomeCampos()} FROM personagem WHERE id = ${id} "

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            objeto = getObjeto(result)

        }
        result.close()

        return objeto
    }

    fun buscarPorNome(nome:String):PersonagemDTO{
        var objeto = PersonagemDTO()

        val sql = "SELECT ${getNomeCampos()} FROM personagem p WHERE nome = '${nome}' "

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){
            objeto = getObjeto(result)
        }

        result.close()

        return objeto
    }

    fun deletar(id:Int){
        val sql = "DELETE FROM $tabela WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }

    fun buscarPersonagensLogados():ArrayList<PersonagemLogadoDTO>{
        var retorno = ArrayList<PersonagemLogadoDTO>()

        val sql = "SELECT ${getNomeCampos()}, c.token FROM contaPersonagem cp " +
                "INNER JOIN personagem p ON cp.personagem_id = p.id " +
                "INNER JOIN conta      c ON cp.conta_id      = c.id " +
                "WHERE c.logado = true AND cp.logado = true"

        val result = MysqlHelper.it.executarSelect(sql)

        while(result!!.next()){

            var objeto = PersonagemLogadoDTO()

            objeto.personagem = getObjeto(result)
            objeto.token = result.getString("c.token")

            retorno.add(objeto)
        }
        result.close()

        return retorno
    }

    private fun getNomeCampos():String{
        val nomeCampos =
                "p.id, p.classe_id, p.nome, p.hpMax, p.hp, p.mpMax, p.mp, p.forca, p.inteligencia, " +
                "p.destreza, p.constituicao, p.aprenderArma, p.pontos1, p.pontos2, p.pontosDeSkill1, " +
                "p.pontosDeSkill2, p.pontosDeSkill3, p.nivel, p.xp, p.PK, p.posicaoX, p.posicaoY, p.posicaoZ, " +
                "p.velocidade, p.ataque, p.defesa, p.ataqueMagico, p.velocidadeAtaque, p.critico, " +
                "p.dinheiro, p.rotacaoY, p.rotacaoX, p.rotacaoZ, p.rotacaoW "

        return nomeCampos;
    }

    private fun getObjeto(result:ResultSet):PersonagemDTO{
        val objeto = PersonagemDTO()

        if(result != null) {
            objeto.id = result.getInt("p.id")
            objeto.classeId = result.getByte("p.classe_id")
            objeto.nome = result.getNString("p.nome")
            objeto.hpMax = result.getInt("p.hpMax")
            objeto.hp = result.getInt("p.hp")
            objeto.mpMax = result.getInt("p.hpMax")
            objeto.mp = result.getInt("p.mp")
            objeto.forca = result.getShort("p.forca")
            objeto.inteligencia = result.getShort("p.inteligencia")
            objeto.destreza = result.getShort("p.destreza")
            objeto.constituicao = result.getShort("p.constituicao")
            objeto.aprenderArma = result.getShort("p.aprenderArma")
            objeto.pontos1 = result.getShort("p.pontos1")
            objeto.pontos2 = result.getShort("p.pontos2")
            objeto.pontosDeSkill1 = result.getShort("p.pontosDeSkill1")
            objeto.pontosDeSkill2 = result.getShort("p.pontosDeSkill2")
            objeto.pontosDeSkill3 = result.getShort("p.pontosDeSkill3")
            objeto.nivel = result.getShort("p.nivel")
            objeto.xp = result.getLong("p.xp")
            objeto.PK = result.getShort("p.PK")
            objeto.pos.posX = result.getFloat("p.posicaoX")
            objeto.pos.posY = result.getFloat("p.posicaoY")
            objeto.pos.posZ = result.getFloat("p.posicaoZ")
            objeto.velocidade = result.getShort("p.velocidade")
            objeto.ataque = result.getShort("p.ataque")
            objeto.defesa = result.getShort("p.defesa")
            objeto.ataqueMagico = result.getShort("p.ataqueMagico")
            objeto.velocidadeAtaque = result.getShort("p.velocidadeAtaque")
            objeto.critico = result.getShort("p.critico")
            objeto.dinheiro = result.getLong("p.dinheiro")
            objeto.quaternion.rotY = result.getFloat("p.rotacaoY")
            objeto.quaternion.rotX = result.getFloat("p.rotacaoX")
            objeto.quaternion.rotZ = result.getFloat("p.rotacaoZ")
            objeto.quaternion.rotW = result.getFloat("p.rotacaoW")


        }

        return objeto
    }

    fun andar(id:Int, coordenadas:Coordenadas){
        val sql = "UPDATE $tabela " +
                "SET posicaoX = ${coordenadas.posX}," +
                "    posicaoY = ${coordenadas.posY}," +
                "    posicaoZ = ${coordenadas.posZ} WHERE id = $id"
        MysqlHelper.it.executarInsertUpdateDelete(sql)
    }


}