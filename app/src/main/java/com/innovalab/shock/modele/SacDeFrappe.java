package com.innovalab.shock.modele;

import android.app.Activity;
import java.util.Observable;

/**
 * Created by ludovic on 27/02/17.
 */

public class SacDeFrappe extends Observable
{
    private String msg_received, tmp_received;
    private ObjetFrappe objetFrappe;

    public SacDeFrappe(Activity parent) {

        objetFrappe = new ObjetFrappe();

        //sendData("A");
    }

    public void sendData(String s) {
        /*bt.sendData(s);
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
