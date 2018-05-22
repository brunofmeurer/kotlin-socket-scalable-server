package br.com.wydmaster.connection

import java.sql.*
import java.util.logging.Level
import java.util.logging.Logger


/**
 * Criado por bruno-meurer em 20/08/17.
 */


enum class MysqlHelper {
    it;

    private var conn:Connection? = null

    var LOG = Logger.getLogger("MysqlHelper")

    fun conectar(){

        val url = "jdbc:mysql://localhost:3306/"
        val dbName = "wyd_master"
        var utcConfig = "?useTimezone=true&serverTimezone=UTC&useSSL=false"
        try {
            conn = DriverManager.getConnection(url + dbName + utcConfig,"root","root")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun voltarComando(){
        conn?.rollback()
    }

    fun executarInsertUpdateDelete(sql: String):Int?{
        var deuCerto = false
        var retorno:Int? = 0
        while(!deuCerto) {
            try {
                val stmt = conn?.createStatement()

                retorno = stmt?.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)

                val rs = stmt?.getGeneratedKeys()
                if (rs!!.next()) {
                    retorno = rs.getInt(1)
                }
                rs.close()

                deuCerto = true
            }catch (e:Exception){e.printStackTrace()}
        }

        return retorno
    }

    fun executarSelect(sql: String):ResultSet?{
        val stmt = conn?.createStatement()
        stmt?.execute(sql)

        return stmt?.resultSet
    }

    fun preparar(sql:String):PreparedStatement?{
        return conn?.prepareStatement(sql)
    }

    fun fechar(){
        conn?.close()
    }
}