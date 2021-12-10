using System;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
		int multiplo_encontrado = 0;
		int aux_multiplo_encontrado = 0;
		List<int> numeros = new List<int>();
		Dictionary<int, List<int>> divisor_multiplos = new Dictionary<int, List<int>>();
	
		int n = 120;
		
		if(NumNegativos(numeros) > 0)
		   return;
		
		for(int i = 0; i <= n;i ++){
			if( i > 0)
			   if(i != 1)
				  numeros.Add(i);
		}
		
		for(int i = 0;  i < numeros.Count; i++){
				for(int j = 0 ; j < numeros.Count; j ++){
					if(numeros[j] % numeros[i] == 0){
						List<int> multiplos = null;
						if(!divisor_multiplos.ContainsKey(numeros[i])){
							multiplos = new List<int>();
							divisor_multiplos.Add(numeros[i], multiplos);
						}
						multiplos = divisor_multiplos[numeros[i]];

						for(int k = 0; k < multiplos.Count; k ++){
							if(multiplos[k] != numeros[j]){
								multiplo_encontrado++;
							}
						}
						if(multiplo_encontrado == multiplos.Count){

							foreach(KeyValuePair<int, List<int>> num in divisor_multiplos){
								  List<int> aux_multiplos = num.Value;
								  for(int l = 0; l < aux_multiplos.Count; l++){
									   if(aux_multiplos[l] == numeros[j]){
										  aux_multiplo_encontrado++;	
										  break;
									   }
								  }
								if(aux_multiplo_encontrado > 0)
									break;
							}

							if(aux_multiplo_encontrado == 0)
							   multiplos.Add(numeros[j]);
						}

						multiplo_encontrado = 0;
						aux_multiplo_encontrado = 0;
						divisor_multiplos[numeros[i]] = multiplos;
					}
				}
		}
	   
		foreach(KeyValuePair<int, List<int>> num in divisor_multiplos){
			   List<int> multiplos = num.Value;
			   int divisor = num.Key;
			   if(multiplos.Count == 0)
			      Remover(numeros, divisor);  
		}
		
		for(int i = 0; i < numeros.Count; i++){	
		 	 Console.WriteLine(numeros[i]);
		}
	}
	
	public static void Remover(List<int> numeros, int n){
		int i = RetornarPosicao(numeros, n);
		if(i < 0)
		  return;	
		numeros.RemoveAt(i);
	}
	
	public static int RetornarPosicao(List<int> numeros, int n){
		int index = -1;
		for(int i=0; i < numeros.Count; i++){
		  if(numeros[i] == n){
			 index = i;
			 break;
		  }
		}
		return index;
	}
	
	public static int NumNegativos(List<int> numeros){
		for(int i = 0; i < numeros.Count; i++){
		    	if(numeros[i] < 0)
					return 1;
		}
		return 0;
	}
}
