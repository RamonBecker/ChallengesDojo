using System;
using System.Linq;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
		List<Numero> numeros = new List<Numero>();
		List<Numero> primos = new List<Numero>();
		List<Numero> multiplos = new List<Numero>();
		int n = 120;
		if(NumNegativos(numeros) > 0)
		   return;
		for(int i = 1; i <= n;i ++){
			numeros.Add(new Numero{ n = i, marcado = false});
		}
		numeros = Remover(numeros, 1);
		
		for(int i = 0; i < numeros.Count; i++){
		   // if(numeros[i].n == 2){
				for(int j = 0; j < numeros.Count; j++){
					if(numeros[j].n % numeros[i].n == 0){
					if(!EncontrarElemento(multiplos, numeros[i].n)){
						numeros[j].marcado = true;
						multiplos.Add(numeros[j]);
						}
					}
			//	}
					
				   if(!EncontrarElemento(multiplos, numeros[i].n)){
					   primos.Add(numeros[i]);
					}
				
			}
		}
		
		/*
		Numero aux_numero = null;
			for(int i = 0; i< numeros.Count; i++){
			    for(int k = 0; k < numeros.Count; k++){
					if(numeros[i].n % multiplos[k].n == 0){
						numeros[i].marcado = true;
						aux_numero = numeros[i];
						break;
					}
				}
			}
			*/
	}
	public static bool EncontrarElemento(List<Numero> numeros, int n ){
		return numeros.Exists(c => c.n == n);
	}						   
	public static List<Numero> Remover(List<Numero> numeros, int n){
	 	int i= 0;
		for(i = 0; i < numeros.Count; i++)
		  if(numeros[i].n == n)
			break;  
		numeros.RemoveAt(i);
		return numeros;
	}	
	public static int NumNegativos(List<Numero> numeros){
		return numeros.FindAll(c => c.n <= 0).ToList().Count;
	}
}

public class Numero{
  public int n;
  public bool marcado;	
 	
}
