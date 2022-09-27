/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nbodysimulation.basic;

/**
 *
 * @author Sely
 */
//A quadrant object that can self-subdivide. Important for creating a Barnes-Hut tree, since it holds quadrants.
public class Quad {

    private double xmid, ymid, length;

    //Create a square quadrant with a given length and midpoints (xmid,ymid)
    public Quad(double xmid, double ymid, double length) {
        this.xmid = xmid;
        this.ymid = ymid;
        this.length = length;
    }

    //How long is this quadrant?
    public double length() {
        return length;
    }

    //Check if the current quadrant contains a point
    public boolean contains(double xmid, double ymid) {
        synchronized (this) {
            if (xmid <= this.xmid + this.length / 2.0 && xmid >= this.xmid - this.length / 2.0 && ymid <= this.ymid + this.length / 2.0 && ymid >= this.ymid - this.length / 2.0) {
                return true;
            } else {
                return false;
            }
        }
    }
  //Create subdivisions of the current quadrant

    // Subdivision: Northwest quadrant
    public Quad NW() {
        synchronized (this) {
            Quad newquad = new Quad(this.xmid - this.length / 4.0, this.ymid + this.length / 4.0, this.length / 2.0);
            return newquad;
        }
    }

    // Subdivision: Northeast quadrant
    public Quad NE() {
        synchronized (this) {
            Quad newquad = new Quad(this.xmid + this.length / 4.0, this.ymid + this.length / 4.0, this.length / 2.0);
            return newquad;
        }
    }

    // Subdivision: Southwest quadrant
    public Quad SW() {
        synchronized (this) {
            Quad newquad = new Quad(this.xmid - this.length / 4.0, this.ymid - this.length / 4.0, this.length / 2.0);
            return newquad;
        }
    }

    // Subdivision: Southeast quadrant
    public Quad SE() {
        synchronized (this) {
            Quad newquad = new Quad(this.xmid + this.length / 4.0, this.ymid - this.length / 4.0, this.length / 2.0);
            return newquad;
        }
    }

}
