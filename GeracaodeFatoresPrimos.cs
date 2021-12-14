using System;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
	 
	  Console.WriteLine("Digite um número: ");
	  int numero = Conversao(Console.ReadLine().Trim());
	  if(!(numero <= 0))
		  FatorPrimo(numero);
	  else
	   Console.WriteLine("Valor digitado inválido!");	  
	}
	
	public static void FatorPrimo(int numero){
	  List<int> primos = CrivodeEratostenes(numero);
	  List<int> primos_decomposicao = new List<int>();
		
	  int i = 0;
	  int aux = numero;
		
	  while(true){
		int resultado = aux % primos[i];
		if(resultado > 0)
		   i++;	
		  
		if(resultado == 0){
			aux = aux / primos[i];
		    primos_decomposicao.Add(primos[i]);
		}
			
		if(aux == 1)
		  break;	
	   }
		
	  for(int j=0; j < primos_decomposicao.Count; j++){
		  Console.WriteLine(primos_decomposicao[j]);	
	  }
	}
	
	public static List<int> CrivodeEratostenes(int numero){
		
		int multiplo_encontrado = 0;
		int aux_multiplo_encontrado = 0;
		List<int> numeros = new List<int>();
		Dictionary<int, List<int>> divisor_multiplos = new Dictionary<int, List<int>>();
		
		if(NumNegativos(numeros) > 0)
		   return null;
		
		for(int i = 0; i <= numero;i ++){
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
		
		return numeros;
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
	public static int Conversao(string valorDigitado){
	  int num = 0;
	  if(int.TryParse(valorDigitado, out num))
	    return num;
	  return -1;
	}
}
