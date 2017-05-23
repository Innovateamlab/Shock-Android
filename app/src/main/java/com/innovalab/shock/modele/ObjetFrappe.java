package com.innovalab.shock.modele;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Random;

/**
 * Created by ludovic on 27/02/17.
 */

public class ObjetFrappe extends Observable implements Serializable {

    //La précision temportelle sera définie dans la carte (durée entre deux échantillons de la liste)
    private List<Vector3> m_capteurs;
    private List<Float> m_temps, m_puissances, m_c1, m_c2, m_c3;
    private float m_puissanceMax, m_puissanceMoyenne;

    public ObjetFrappe() {
        reset();
    }

    /**
     * @brief Permet d'effectuer les calculs d'analyse à partir des données des capteurs.
     */
    public void calculerInfos() {
        m_puissanceMax = m_capteurs.get(0).x;

        //TODO : Enlever ça
        calculsDebug();
    }

    //TODO : Enlever ça
    private void calculsDebug() {
        int nbEchantillons = 20;

        float f = new Random().nextFloat() * 10;

        float max1 = m_puissanceMax, max2 = f * m_puissanceMax, max3 = f * 1.2f * m_puissanceMax;
        Log.i("Debug", "Punch : " + max1 + " | Max : " + max3);


        reset();

        for (int i = 0; i < nbEchantillons; i++) {
            int diff = Math.abs((nbEchantillons / 2) - i);
            Vector3 vect3;
            if (diff == 0) {
                vect3 = new Vector3(max1, max2, max3);
            } else {
                vect3 = new Vector3((1 - (diff / 20.0f)) * max1, (1 - (diff / 20.0f)) * max2, (1 - (diff / 20.0f)) * max3 + i);

            }


            m_capteurs.add(vect3);
            m_puissances.add((vect3.x + vect3.y + vect3.z) / 3);
            m_temps.add((float) i);
            m_c1.add(vect3.x);
            m_c2.add(vect3.y);
            m_c3.add(vect3.z);
        }
    }

    public float getPuissanceMax() {
        return m_puissanceMax;
    }

    public List<Float> getTemps() {
        return m_temps;
    }

    public List<Float> getforceVecteurFrappeNum() {
        return m_puissances;
    }

    public List<Float> getforceFrappeNumC1() {
        return m_c1;
    }

    public List<Float> getforceFrappeNumC2() {
        return m_c2;
    }

    public List<Float> getforceFrappeNumC3() {
        return m_c3;
    }


    /**
     * @brief Initialise les données membres à partir des informations de la carte.
     */
    public void ajouterEchantillonParser(String infosCarte) {
        m_capteurs.add(new Vector3(Integer.parseInt(infosCarte), 0, 0));
    }

    public void ajouterEchantillon(Vector3 vector, int i) {
        m_puissances.add((vector.x + vector.y + vector.z) / 3);
        m_temps.add((float) i);
        m_c1.add(vector.x);
        m_c2.add(vector.y);
        m_c3.add(vector.z);
        m_capteurs.add(vector);
    }

    public void reset() {
        m_capteurs = new ArrayList();
        m_temps = new ArrayList();
        m_puissances = new ArrayList();
        m_c1 = new ArrayList();
        m_c2 = new ArrayList();
        m_c3 = new ArrayList();
    }


    public float[] getG_position(int t) {
        if (t > m_capteurs.size() - 1)
            return new float[]{0, 0};
        return getG_position(m_capteurs.get(t));
    }

    public float[] getG_position(Vector3 vector3) {
        float[] m_position = new float[2];
        float x_c1 = 0;
        float x_c2 = 110 * 0.86f;
        float x_c3 = 110 * -0.86f;
        float y_c1 = 110;
        float y_c2 = 110 * -0.5f;
        float y_c3 = 110 * -0.5f;
        float m_c1 = vector3.x;
        float m_c2 = vector3.y;
        float m_c3 = vector3.z;
        float sum = m_c1 + m_c2 + m_c3;
        float x_g = (m_c1 * x_c1 + m_c2 * x_c2 + m_c3 * x_c3) / sum;
        float y_g = (m_c1 * y_c1 + m_c2 * y_c2 + m_c3 * y_c3) / sum;
        m_position[0] = x_g;
        m_position[1] = y_g;
        return m_position;
    }


    public List<Float> calculLissage(List<Float> parametreListe, int n) {
        List<Float> moyennage = new ArrayList<Float>();
        int i, j;
        float x;

        for (i = 0; i < 5; i++) {
            moyennage.add((float) 0);
        }
        for (i = 5; i < parametreListe.size() - 5; i++) {
            x = 0;
            for (j = 0; j < 10; j++) {
                x = x + parametreListe.get(i - 5 + j);
            }
            moyennage.add((x + moyennage.get(i - 1)) / 11.0f);
        }
        for (i = 0; i < 5; i++) {
            moyennage.add((float) 0);
        }

        if (n > 0) {
            return (calculLissage(moyennage, n--));
        } else {
            return (moyennage);
        }
    }


    public List<Float> lissage() {
        /*List<Float> moyennage = new ArrayList<Float>();
        List<Float> moyennage2 = new ArrayList<Float>();
        List<Float> moyennage3 = new ArrayList<Float>();
        List<Float> moyennage4 = new ArrayList<Float>();
        List<Float> moyennage5 = new ArrayList<Float>();
        List<Float> moyennage6 = new ArrayList<Float>();
        List<Float> moyennage7 = new ArrayList<Float>();
        List<Float> moyennage8 = new ArrayList<Float>();
        List<Float> moyennage9 = new ArrayList<Float>();*/
        /*List<Float> position = new ArrayList<Float>();
        List<Float> positionSuppose = new ArrayList<Float>();*/
        /*int i,j;
        float x;*/
        /*float a,b;*/


        // Premiere version moyennage moyenne non centrée
        /*for(i=0;i<m_puissances.size()-10;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + m_puissances.get(i+j);
            }
            moyennage.add(x/10.0f);
            //m_temps.set(i,(float) i);
        }
        for(i=0;i<10;i++){
            moyennage.add((float) 0);
        }*/


        // Deuxieme version moyennage ave derivée , 1 seule valeur affiché
        /*for(i=0;i<5;i++){
            moyennage.add((float) 0);
        }
        for(i=5;i<m_puissances.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + m_puissances.get(i-5+j);
            }
            moyennage.add((x + moyennage.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage.add((float) 0);
        }
        position.add((float) 0);
        position.add((float) 0);
        for(i=2;i<m_puissances.size();i++){
            a = position.get(i-1) - position.get(i-2);
            if (a==0) {
                b = position.get(i-1);
            }
            else {
                b = (position.get(i-1) + position.get(i-2))/(2*(2*i-3)*a);
            }
            positionSuppose.add(a*i+b);
            position.add((positionSuppose.get(i-2) + moyennage.get(i))/2);
        }*/

        // Troisième version inutilisable "dérivée infinie"
        /*position.add((float) 0);
        position.add((float) 0);
        for(i=2;i<m_puissances.size();i++){
            a = m_puissances.get(i-1) - m_puissances.get(i-2);
            if (a != 0) {
                b = (m_puissances.get(i - 1) + m_puissances.get(i - 2)) / (2 * (2 * i - 3) * a);
            }
            else {
                b = m_puissances.get(i-1);
            }
            positionSuppose.add(a*i+b);
            position.add((positionSuppose.get(i-2) + m_puissances.get(i))/2);
        }*/


        //Quatrième version nul
        /*position.add((float) 0);
        position.add((float) 0);
        for(i=2;i<m_puissances.size();i++){
            a = position.get(i-1) - position.get(i-2);
            if (a != 0) {
                b = (position.get(i-1) + position.get(i-2))/(2*(2*i-3)*a);
            }
            else {
                b = position.get(i-1);
            }
            positionSuppose.add(a*i+b);
            position.add((positionSuppose.get(i-2) + m_puissances.get(i))/2);
        }*/





        /*for(i=0;i<5;i++){
            moyennage.add((float) 0);
        }
        for(i=5;i<m_puissances.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + m_puissances.get(i-5+j);
            }
            moyennage.add((x + moyennage.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage2.add((float) 0);
        }
        for(i=5;i<moyennage.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage.get(i-5+j);
            }
            moyennage2.add((x + moyennage2.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage2.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage3.add((float) 0);
        }
        for(i=5;i<moyennage2.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage2.get(i-5+j);
            }
            moyennage3.add((x + moyennage3.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage3.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage4.add((float) 0);
        }
        for(i=5;i<moyennage3.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage3.get(i-5+j);
            }
            moyennage4.add((x + moyennage4.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage4.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage5.add((float) 0);
        }
        for(i=5;i<moyennage4.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage4.get(i-5+j);
            }
            moyennage5.add((x + moyennage5.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage5.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage6.add((float) 0);
        }
        for(i=5;i<moyennage5.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage5.get(i-5+j);
            }
            moyennage6.add((x + moyennage6.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage6.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage7.add((float) 0);
        }
        for(i=5;i<moyennage6.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage6.get(i-5+j);
            }
            moyennage7.add((x + moyennage7.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage7.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage8.add((float) 0);
        }
        for(i=5;i<moyennage7.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage7.get(i-5+j);
            }
            moyennage8.add((x + moyennage8.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage8.add((float) 0);
        }

        for(i=0;i<5;i++){
            moyennage9.add((float) 0);
        }
        for(i=5;i<moyennage8.size()-5;i++){
            x=0;
            for(j=0;j<10;j++){
                x = x + moyennage8.get(i-5+j);
            }
            moyennage9.add((x + moyennage9.get(i-1))/11.0f);
        }
        for(i=0;i<5;i++){
            moyennage9.add((float) 0);
        }

        return moyennage9;*/
        return (calculLissage(m_puissances, 9));
    }
}
