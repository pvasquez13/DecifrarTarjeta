package com.globokas.connection;

import com.globokas.model.Racal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;


public class InfoConciliacionDB {

    static Logger logger = Logger.getLogger(InfoConciliacionDB.class);
    private final FactoryDBConnection factory;
    private static InfoConciliacionDB instance;

    public InfoConciliacionDB() {
        factory = FactoryDBConnection.getInstance();
    }

    public static InfoConciliacionDB getInstance() {
        if (instance == null) {
            instance = new InfoConciliacionDB();
        }
        return instance;
    }

    public void getEncryptCard(Racal racal) {
        PreparedStatement preparedStatement = null;
        String selectSQL = "SELECT PAN_CIFRADO, SECUENCIAL_KEY, SETTL_DATA FROM TXNLOG WHERE TRANSDATE = ? AND TRACE = ? AND TERM_ID = ?";
        Connection con = null;
        try {
            con = factory.RacalConnection();
            preparedStatement = con.prepareStatement(selectSQL);
            preparedStatement.setString(1, racal.getTransDate());
            preparedStatement.setInt(2, Integer.valueOf(racal.getTrace()));
            preparedStatement.setString(3, racal.getTerminal());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                racal.setEncryptCard(rs.getString("PAN_CIFRADO"));
                racal.setSequence(Integer.toString(rs.getInt("SECUENCIAL_KEY")));

            }

            if (racal.getEncryptCard() == null) {
                System.out.println("No se encontro PAN_CIFRADO, SECUENCIAL_KEY (TRANSDATE=" + racal.getTransDate() + ",TRACE="
                        + racal.getTrace() + ",TERM_ID=" + racal.getTerminal());
//                logger.warn("No se encontro PAN_CIFRADO, SECUENCIAL_KEY (TRANSDATE=" + racal.getTransDate() + ",TRACE="
//                        + racal.getTrace() + ",TERM_ID=" + racal.getTerminal());
            }

        } catch (SQLException e) {
            System.out.println("Error Consultando la Base de datos para RACAL ... ");
//            logger.error("Error Consultando la Base de datos para RACAL ... ");
            logger.error(e.getMessage(), e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.out.println("Error Closing PreparedStatement Object ...");
//                logger.error("Error Closing PreparedStatement Object ...");
                logger.error(e.getMessage(), e);
            }
        }
    }

}
