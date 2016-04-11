/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes2;

/**
 *
 * @author Maciek
 */
public class gaussC {
     public static void swap(Object a, Object b){
       Object t = a;
       a = b;
       b = t;
   } 
    
    
    public static boolean gauss(int n,double AB[][],double X[]){
    int    i,j,k;
	double m,s;
      double eps = 1e-12;
      int[] W = new int[n+1];
	for(i = 0; i <=n; i++) W[i] = i;

	// eliminacja współczynników

	for(i = 0; i < n - 1; i++)
	{
		k = i;
		for(j = i + 1; j < n; j++)
			if(Math.abs(AB[i][W[k]]) < Math.abs(AB[i][W[j]])) k = j;
		swap(W[k],W[i]);
		for(j = i + 1; j < n; j++)
		{
			if(Math.abs(AB[i][W[i]]) < eps) return false;
			m = -AB[j][W[i]] / AB[i][W[i]];
			for(k = i + 1; k <= n; k++)
				AB[j][W[k]] += m * AB[i][W[k]];
		}
	}

	// wyliczanie niewiadomych

	for(i = n - 1; i >= 0; i--)
	{
		if(Math.abs(AB[i][W[i]]) < eps) return false;
		s = AB[i][n];
		for(j = n - 1; j >= i + 1; j--)
			s -= AB[i][W[j]] * X[W[j]];
		X[W[i]] = s / AB[i][W[i]];
	}
	return true;
    
    
    
    
    
    
    
    
    
    
    
    }
}
