import java.sql.*; //Importação da biblioteca do SQL.
import java.util.*; //Importação da biblioteca Util do java.
public class CreateSql {  //Criação do objeto CreateSql
    public static void main(String[] args) { 
        /* Declaração do methodo executor main
        *public: porque podera ser importado por outros objetos/classes.
        *static: porque o methodo nao podera ser alterado.
        *void: pois este é um methodo sem retorno.
        * @param Strings[] poderao ser invocados metodos do tipo String e matrizes [] 
        */
        Boolean sair = false; //Criação duma variavel do tipo Boolean com o valor false.
        String SouN, tableName, strCreateTable, status = "Nada aconteceu ainda";
        /* Criação de varias variaveis do tipo String
         * SouN: Recebera o valor que o usuario digitara [s] ou [n]
         * tableName: Recebera o nome da tabela do banco de dados
         * strCreateTable: Ira receber o comando que sera utilizado dentro do Banco de dados.
         * status: Recebera o status do sistema atualmente.
         */
        Scanner scnTable = new Scanner(System.in);  //Criação de um Scanner com nome scnTable.
        Scanner scnQtdCampos = new Scanner(System.in);  //Criação de um Scanner com nome scnQtdCampos.
        Scanner scnCampos = new Scanner(System.in);  //Criação de um Scanner com nome scnCampos.
        Scanner scnSair = new Scanner(System.in);  //Criação de um Scanner com nome scnSair
        Connection conn = App.conectar();  //Invocaçao da variavel conn. A qual tem o metodo de conexão.
        Statement stmSql = null;  //Criação de Statement stmSql com valor nulo.
        List<String> colunas = new ArrayList<>(); //Adição de uma ArrayList.
        int qtdCampos;  //Criação de uma variavel do tipo int.
        status = "Nada aconteceu ainda...";  //Atualização da variavel status.
        System.out.println("Bem vindo ao criador de tabelas.");  //Exibição de mensagem para o Usuario.
        while (sair == false) {  //Inicio de um loop o qual continuara enquanto o valor da variavel sair seja igual a false.
            try { //Inicio de uma ação do tipo try.
                stmSql = conn.createStatement();  //Atribuição de valor para a Statement criada anteriormente atribuindo-a uma conexão com o banco de dados.
                System.out.println("Digite o nome da tabela que deseja criar: ");  //Exibição de mensagem pro usuario.
                tableName = scnTable.nextLine();  //Solicitação para que o Usuario digite algo.
                System.out.println("Digite a quantidade de campos (colunas) que deseja criar: ");  //Exibição de mensagem pro usuario.
                qtdCampos = scnQtdCampos.nextInt();  //Solicitação para que o Usuario digite algo.

                for (int i = 1; i <= qtdCampos; i++) {
                    /* Criação de um loop
                     * int i: Criação de uma variavel do tipo int
                     * i = 1: O loop começa com i igual a 1.
                     * i <= qtdCampos: O loop se encerra quando i for menor ou igual ao valor presente na variavel qtdCampos.
                     * i++: o valor de i sera acrecentado mais 1 a cada tick.
                     */
                    System.out.println("Digite o nome do campo [" + i + "]: ");  //Exibição de mensagem para o Usuario.
                    colunas.add(scnCampos.nextLine());  //Adição de uma coluna na ArrayList.
                } //Encerra o loop For 

                String strColunas = "";  //Criação de uma variavel string que recebera o valor a seguir.
                for (int c = 0; c < colunas.size(); c++) {
                    /* Criaçao de loop do tipo for.
                     * int c: Criaçao duma variavel do tipo int.
                     * c = 0: O loop começara com o valor da variavel c sendo 0.
                     * c < colunas.size(): O loop ira terminar quando o valor de c, for menor que o numero de colunas presentes na arraylist.
                     * c++: a variavel c ira receber mais 1 a cada tick.
                     */
                    strColunas += "`" + colunas.get(c) + "` VARCHAR(255) NULL,";  //strColunas esta recebendo os valores da arraylist + o valor necessario para o funcionamente do codigo dentro do banco de dados.
                } //Encerramento do for.

                strCreateTable = "CREATE TABLE `mysql_connector`.`" + tableName + "`(`id` INT NOT NULL AUTO_INCREMENT, " + strColunas + " PRIMARY KEY (`id`));";  //Variavel strCreateTable recebendo o codigo que sera utilizado no SQL workbench.
                stmSql.addBatch(strCreateTable);  //Criação do receptaculo, que recebera o comando cujo esta presente na variavel strCreateTable.
                stmSql.executeBatch();  //Criação do executor do comando presente no receptaculo.
                stmSql.close();  //Fechamento do receptaculo.

                System.out.println("Deseja criar outra tabela? Digite [s] para criar outra tabela ou [n] para encerrar.");  //Mensagem para o usuario.
                SouN = scnSair.nextLine(); //Solicitação feita para o Usuario, o qual deve digitar [s] ou [n].
                if (SouN.equals("n") || SouN.equals("N")) {
                    //Inicio de uma ação condicional. Se a variavle SouN for igual a n ou N fazer...
                    sair = true;  //Altera o valor da variavel sair para true.
                    status = "Tabela(s) criada(s) com sucesso.";  //Altera o valor presente na variavel status.
                }  //Encerra a ação condicional
            } catch (Exception e) {
                //Inicia uma ação do tipo catch. O qual exibira, caso aja algum erro, o erro ocorrido.
                System.err.println("Ops! ocorreu o erro " + e);  //Mensagem exibindo o erro ocorrido.
            }  //Encerramento do catch
            System.out.println(status);  //Mensagem exibindo o valor da variavel status.
        }
        scnTable.close(); //Fechamento do Scanner scnTable.
        scnQtdCampos.close();  //Fechamento do Scanner scnQtdCampos.
        scnCampos.close();  //Fechamento do Scanner scnCampos.
        scnSair.close();  //Fechamento do Scanner scnSair.
    } //Encerramento do metodo main.
} //Encerramento do objeto CreateSql.