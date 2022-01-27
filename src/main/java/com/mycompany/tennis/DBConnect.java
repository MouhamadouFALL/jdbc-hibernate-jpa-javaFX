package com.mycompany.tennis;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class DBConnect {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            BasicDataSource dataSource = new BasicDataSource();

            
            //----------------------------------------------------------------------------------------------------------------------------------------------------------------
            // MySQL driver MySQL Connector
            // ouvrir une connexion avec DriverManager
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDateTimeCode=false&serverTimezone=UTC","root","");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false","root","");

            // Ouvrir la connection avec Interface DataSource
            MysqlDataSource dataSource = new MysqlDataSource();

            // definir les params de connexion
            dataSource.setURL("jdbc:mysql://localhost:3306/tennis?useSSl=false");
            //dataSource.setServerName("localhost");
            //dataSource.setPort(3306);
            //dataSource.setDatabaseName("tennis");
            //dataSource.setUseSSL(false);
            dataSource.setUser("root");
            dataSource.setPassword("");

            conn = dataSource.getConnection();

            // Désactiver la validaton automatique des transactions
            conn.setAutoCommit(false);

            ///////////////////////////////////////////////////////////  Lire des enregistrements  //////////////////////////////////////////////////////////////
            // requête pour lire les données
            // cas ou on connait la requete à exécuter
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select ID, NOM, PRENOM from joueur"); // statement retourne un jeux de données
            // Faire quelque chose du résultat : ici on affiche le résultat
            //System.out.println("   ID   |   NOM       |   PRENOM        |");
            //while (rs.next()) {
            //    final String prenom = rs.getString("PRENOM");
            //   final String nom = rs.getString("NOM");
            //   final long id = rs.getLong("ID");
            //   System.out.println("   "+id+"       "+nom+"    "+prenom+" ");
            //}

            // System.out.println("==============================================================================");

            // cas dont la requête à exécuter est inconnue
            PreparedStatement preparedStatement = conn.prepareStatement("select nom, prenom, id from joueur where id=?");
            long identifiant = 29;
            preparedStatement.setLong(1, identifiant);
            //ResultSet rs1 = preparedStatement.executeQuery();

            /*if (rs1.next()) {
                final String prenom = rs1.getString("PRENOM");
                final String nom = rs1.getString("NOM");
                final long id = rs1.getLong("ID");
                System.out.println("   "+id+"       "+nom+"    "+prenom+" ");
            }
            else {
                System.out.println("Le joueur "+identifiant+" n'a pas été enregistrer");
            }

            System.out.println("Modification");*/

            //////////////////////////////////////////////////  Modifier un enregistrement  ////////////////////////////////////////////////////////////////
            PreparedStatement preparedStatementUp = conn.prepareStatement("update joueur set nom=?, prenom=? where id=?");
            long identifiant1 = 24L;
            String nom = "Errani";
            String prenom = "Sara";
            preparedStatementUp.setString(1, nom);
            preparedStatementUp.setString(2, prenom);
            preparedStatementUp.setLong(3, identifiant1);
            //int nbEnregistrementModifier = preparedStatementUp.executeUpdate();

            //////////////////////////////////////////////////  Insérer dans la base de données  ///////////////////////////////////////////////////////////
            PreparedStatement preparedStatementInsert = conn.prepareStatement("insert into joueur (NOM, PRENOM, SEXE) values (?, ?, ?)");
            String nomToInsert = "Penda";
            String prenomToInsert = "FAYE";
            String sexe = "F";
            preparedStatementInsert.setString(1, nomToInsert);
            preparedStatementInsert.setString(2, prenomToInsert);
            preparedStatementInsert.setString(3, sexe);

            preparedStatementInsert.executeUpdate();

            nomToInsert = "Bouh";
            prenomToInsert = "BA";
            sexe = "H";
            preparedStatementInsert.setString(1, nomToInsert);
            preparedStatementInsert.setString(2, prenomToInsert);
            preparedStatementInsert.setString(3, sexe);
            //preparedStatementInsert.setNull(3, Types.CHAR);

            //preparedStatementInsert.executeUpdate();


            //////////////////////////////////////////////////  Supprimer dans la base de données  ///////////////////////////////////////////////////////////
            PreparedStatement preparedStatement1Delete = conn.prepareStatement("delete from joueur where id=?");
            Long id = 47l;
            preparedStatement1Delete.setLong(1, id);
            //preparedStatement1Delete.executeUpdate();

            // valider la transaction
            conn.commit();

            //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

            //System.out.println("nombre enregistrement :" + nbEnregistrementModifier);
            //System.out.println("");

            // ici facultatif car on ferme la connexion apres avoir terminer
            ///rs.close();
            //statement.close();
            System.out.println("Successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
