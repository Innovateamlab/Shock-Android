package com.innovalab.shock.modele;

/**
 * Created by ludovic on 28/02/17.
 */



public class ObjetNotification
{
    public Labels label;
    public Object object;

    public ObjetNotification(Labels label, Object obj)
    {
        this.label = label;
        this.object = obj;
    }

    public ObjetFrappe getObjetFrappe() {return (ObjetFrappe) object;}
    public String getString(){return (String) object;}
}
