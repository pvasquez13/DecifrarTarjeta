package com.globokas.service;

import com.globokas.model.Racal;

/**
 *
 * @author pvasquez
 */
public class DecifrarTarjetaService {

    public String decifrarTarjeta(Racal racalIN, RacalService racalService) {
//        RacalService racalService = RacalService.getInstance();
        Racal racal = racalService.getNumeroTrajeta(racalIN);
//        racalService.closeSixFactory();

        return racal!=null?racal.getEncryptCard():"";
    }
}
