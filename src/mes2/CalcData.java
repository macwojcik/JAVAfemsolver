/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes2;

import static mes2.Window.nh;

/**
 *
 * @author Maciek
 */
public class CalcData {
    double dR;
    double dtau;
    double ntime;
    double x;
    double alfa;
    double Rp;
    double tptau;
    double dT;
    double[] crood = new double[nh];
    double[] temp = new double[nh];
    double[] temptau = new double[2];
    double dtmax;
    double tau;
    double[][] globalH = new double [nh][nh];
    double[] globalP = new double [nh];
    double[][] matrix = new double [nh][nh+1];



    public static double[][] clearglobalh(double[][] globalH) {
        
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nh; j++)
			globalH[i][j] = 0;
            }   
        
        return globalH;
    }
    
    public static double[] clearglobalp(double[] globalP) {
        
         for (int i = 0; i < nh; i++)
		globalP[i] = 0;  
        
        return globalP;
    }

    public static Element buildlocals(Element[] e, CalcData cd, Data d, int ip, int ie, double[] W, double[] N1, double[] N2) {
        
        if (ip != 1) {
            e[ie].localH[0][0] += d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N1[ip] * N1[ip] / cd.dtau;
            e[ie].localH[0][1] += (-1) * d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N1[ip] * N2[ip] / cd.dtau;
            e[ie].localH[1][0] = e[ie].localH[0][1];
            e[ie].localH[1][1] += d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N2[ip] * N2[ip] / cd.dtau;
            
            e[ie].localP[0] += d.c * d.ro * cd.dR * cd.tptau * cd.Rp * W[ip] * N1[ip] / cd.dtau; 
            e[ie].localP[1] += d.c * d.ro * cd.dR * cd.tptau * cd.Rp * W[ip] * N2[ip] / cd.dtau;
        }
        else {
            e[ie].localH[0][0] += d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N1[ip] * N1[ip] / cd.dtau;
            e[ie].localH[0][1] += (-1) * d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N1[ip] * N2[ip] / cd.dtau;
            e[ie].localH[1][0] = e[ie].localH[0][1];
            e[ie].localH[1][1] += d.k * cd.Rp * W[ip] / cd.dR + d.c * d.ro * cd.dR * cd.Rp * W[ip] * N2[ip] * N2[ip] / cd.dtau + 2 * cd.alfa * d.rmax;
            
            e[ie].localP[0] += d.c * d.ro * cd.dR * cd.tptau * cd.Rp * W[ip] * N1[ip] / cd.dtau; 
            e[ie].localP[1] += d.c * d.ro * cd.dR * cd.tptau * cd.Rp * W[ip] * N2[ip] / cd.dtau + 2 * cd.alfa * d.rmax * d.tempair;
        }
        
        return e[ie];
    }

    public static double[][] buildglobalh(int ie, Element[] el, CalcData cd) {
        
        cd.globalH[ie][ie] += + el[ie].localH[0][0];
        cd.globalH[ie][ie + 1] += el[ie].localH[0][1];
        cd.globalH[ie + 1][ie] += el[ie].localH[1][0];
        cd.globalH[ie + 1][ie + 1] += el[ie].localH[1][1];
        
        
        return cd.globalH;
    }
    
    
    public static double[] buildglobalp(int ie, Element[] el, CalcData cd) {
        
        cd.globalP[ie] += el[ie].localP[0];
        cd.globalP[ie + 1] += el[ie].localP[1];
        
        
        return cd.globalP;
    }
    
    
     public static double[][] mergematrix(CalcData cd) {
        
        for (int i = 0; i < nh; i++) {
            for (int j = 0; j < nh; j++)
			cd.matrix[i][j] = cd.globalH[i][j];
            }   
        
        for (int i = 0; i < nh; i++)
		cd.matrix[i][nh] = cd.globalP[i];
        
        
        return cd.matrix;
    }
    
    
}