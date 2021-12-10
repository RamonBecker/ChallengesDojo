using System;
using System.Collections.Generic;
					
public class Program
{
	
	private static List<string> expressoes =  new List<string>{"and", "or", "xor"};
	private static Dictionary<string, int> expressoes_n_vezes = new Dictionary<string, int>();
	
	public static void Main()
	{
	   /*	
      Parênteses Booleanos
      Dada uma expressão booleana, contendo os símbolos: 'true', 'false', 'and', 'or' e 'xor', 
	  elabore um programa que conte de quantas maneiras é possível obter uma resposta ' true', utilizando parênteses.
      Por exemplo, dada a expressão 'true and false xor true' existe apenas uma maneira de a expressão retornar 'true': 'true and (false xor true)'
	  */
		
		string expressao_booleana = "true and (false xor true)";
		int k = -1;
		int j = -1;
		
		for(int i = 0; i < expressao_booleana.Length; i++){
		  if(expressao_booleana[i] == '('){
			  k = i;
			  break;
		  }
		}
		
		if(k >= 0){
			for(int i = k; i < expressao_booleana.Length; i++){
			   if(expressao_booleana[i] == ')'){
				 j = i;
				 break;
			   }
			}
			
			if(j > 0){
				string aux = "";
				int cont = 0;
				
			    string []	palavras =expressao_booleana.Split(' ');
				
			   	for(int i = k+1; i <j; i ++){
					if(expressao_booleana[i] != ' '){
					   aux =  string.Concat(aux, expressao_booleana[i]);
					   	
					}	
					Console.WriteLine(aux);
					if(expressao_booleana[i] == ' '){
						
					  for(int m = 0; m < expressoes.Count; m++){
						   
					   	 if(expressoes[m].ToLower().Equals(aux.ToLower())){
							cont++; 
						 }
	
						  if(cont > 0){
							 if(!expressoes_n_vezes.ContainsKey(expressoes[m])){
								 expressoes_n_vezes.Add(expressoes[m], 0);
							 }
							 int n_vezes = expressoes_n_vezes[expressoes[m]];
							 n_vezes += cont;
							 expressoes_n_vezes[expressoes[m]] = n_vezes;
							 
						  }
						  cont = 0;
					  }
					}
					//
				}
				
				int cont_vazio = 0;
			
				     
				
				if(cont_vazio == expressoes_n_vezes.Count)
					Console.WriteLine("Não foi possível encontrar correspondências das expressões booleanas dentro");
				else
					foreach(KeyValuePair<string, int> expressao_n_vezes in expressoes_n_vezes)
					    if(expressao_n_vezes.Value > 0) 
						    Console.WriteLine(expressao_n_vezes.Key+" vezes: "+expressao_n_vezes.Value);	
						
			}
			else
				Console.WriteLine("Não foi possível encontrar correspondências das expressões booleanas");
			
		}
		else
			Console.WriteLine("Não foi possível encontrar correspondências das expressões booleanas");
	}
}
