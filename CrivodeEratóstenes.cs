using System;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
		List<int> numeros = new List<int>();
		int n = 100;
		
		if(n > 0){
		  for(int i = 1; i <= n;i ++){
		      numeros.Add(i);	
		  }
		  
		Remover(numeros, 1);
			
		  for(int i = 0; i <numeros.Count; i++){
			 for(int j=0; j < numeros.Count; j ++){
			    if(numeros[j] % numeros[i] != 0){
				    Console.WriteLine(numeros[i]);  
			     }
			 }
		  }
			
		}
	}
	
	public static void Remover(List<int> numeros, int n){
	 	int i= 0;
		for(i = 0; i < numeros.Count; i++)
		  if(numeros[i] == n)
			break;  
		numeros.RemoveAt(i);
	}
}
