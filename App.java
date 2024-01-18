
//Importação da biblioteca do SQL
import java.sql.*;

//Crição do objeto App
public class App {
    public static void main(String[] args) {
        /* Declaração do methodo executor main
        *public: porque podera ser importado por outros objetos/classes.
        *static: porque o methodo nao podera ser alterado.
        *void pois este é um methodo sem retorno.
        * @param Strings[] poderao ser invocados metodos do tipo String e matrizes []
        * @param args 
        */
        System.out.println("Conectando ao banco de dados..."); //Exibição de tela, avisando que o banco de daos esta sendo conectado.
        conectar(); //Solicitando o metodo conectar.
    } //Encerra metodo main.

    /*
     * Declaração do metodo conectar
     * public: Porque podera ser importado por outros objetos/classes.
     * static: Porque o metodo nao podera ser alterado.
     * Connection: Metodo do tipo conection
     */
    public static Connection conectar() {
        String status = "Nada aconteceu ainda..."; //Criação de uma var do tipo String. A qual tem o valor do status atual do sistema.
        String mysqlHost = "127.0.0.1"; //Criação de uma var do tipo String. A qual tem o valor IP do host.
        String mysqlDb = "mysql_connector"; //Criação de uma var do tipo String. A qual tem o Banco de dados.
        String mysqlUser = "Roger"; //Criação de uma var do tipo String. A qual tem o usuario.
        String mysqlPassword = ""; //Criação de uma var do tipo String. A qual tem a senha do usuario.
        String mysqlUrl = "jdbc:mysql://" + mysqlHost + "/" + mysqlDb + "?user=" + mysqlUser + "&password="
                + mysqlPassword; //Criação de uma var do tipo String. A qual tem o caminho que vai ser utilizado para fazer a conexao.
        Connection conn = null; //Criação de uma variavel do tipo connection com o valor nulo.
        try { //Invocação de uma ação do tipo try.
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            /* Class.forName: Carrega a classe do driver JDBC do MySQL.
            * .newInstance(): invocaçao de uma metodo para criar uma nova instancia da classe.
            */
            conn = DriverManager.getConnection(mysqlUrl); //Invocação da variavel conn, dando-a o poder de fazer a conexao com o banco de dados SQL
            status = "Conectado!"; //invocaçao da variavel status para alterar seu valor.
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e)  { //Invocaçao da açao catch. O qual se iniciara a qualquer erro na conexao.
            System.err.println("Erro na conexão: " + e); //Exibição de qualquer erro que de na conexao.
        }  //Encerra while.
        System.out.println(status); //Exibição da variavel status.
        return conn; //Retorno da variavel conn com a conexão feita ou não.
    } //Encerra metodo conectar.
}  //Encerra o objeto App.
