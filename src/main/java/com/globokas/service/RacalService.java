package com.globokas.service;

import com.globokas.connection.FactorySixConnection;
import com.globokas.connection.InfoConciliacionDB;
import com.globokas.model.Racal;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.novatronic.components.Connection;
import com.novatronic.components.sixclient.tcp.ClienteSIXConnectionFactory;
import com.novatronic.components.sixclient.tcp.ClienteSIXExeBean;

public class RacalService {

    static Logger logger = Logger.getLogger(RacalService.class);

    private static RacalService instance;
    private ClienteSIXConnectionFactory factory;
    private InfoConciliacionDB infoConciliacionDB;

    public static RacalService getInstance() {
        if (instance == null) {
            instance = new RacalService();
        }

        return instance;
    }

    public Racal getNumeroTrajeta(Racal racal) {
        try {
            infoConciliacionDB = InfoConciliacionDB.getInstance();
            infoConciliacionDB.getEncryptCard(racal);
            Connection sixConnection = null;
            try {
                if (racal.getEncryptCard() != null) {
                    ClienteSIXExeBean request = null;
                    ClienteSIXExeBean response = null;
                    Racal racalReturn = new Racal();
                    String id = "DecifrarTarjetaApp";
                    request = new ClienteSIXExeBean();
                    request.setId(id);
                    String tramaRequest = "P20000010012121808090     0032" + racal.getEncryptCard()
                            + completeText(racal.getSequence(), 6);
                    request.setTrama(tramaRequest);

                    factory = FactorySixConnection.getInstance();
                    sixConnection = factory.getConnection(id);
                    response = (ClienteSIXExeBean) sixConnection.execute(request);
                    sixConnection.liberar();

                    String tramaResponse = response.getTrama();

                    if (tramaResponse != null) {
                        racalReturn.setEncryptCard(tramaResponse.substring(tramaResponse.length() - 16));
                        return racalReturn;
                    }
                    logger.error("Error - tramaResponse incorrecta");
                }
            } catch (Exception e) {
                if (sixConnection != null) {
                    sixConnection.liberar();
                }
                logger.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        return null;
    }

    public void closeSixFactory() {
        try {
            if (factory != null) {
                factory.destroy();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public String completeText(String valor, int longitud) {
        if (valor == null) {
            return "";
        }

        return StringUtils.leftPad(valor, longitud, "0");
    }

}
