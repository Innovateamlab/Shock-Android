package com.innovalab.shock.modele;

import java.io.Serializable;

/**
 * Created by ludovic on 28/02/17.
 */

public class Vector3 implements Serializable {
    public float x, y, z;
    public Vector3(float x,float y,float z) {this.x=x;this.y=y;this.z=z;}

    public String toString(){
        return "{"+x+","+y+","+z+"}";
    }
}
