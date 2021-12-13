using System;
using System.Collections.Generic;
					
public class Program
{
	
	private static List<string> expressoes =  new List<string>{"and", "or", "xor"};
	
	public static void Main()
	{
		string expressao_booleana = "true and false xor true or true and true";
		expressao(expressao_booleana);
	}
	
	public static void expressao(string expressao_booleana){
	expressao_booleana = expressao_booleana.ToLower().Trim(); 	
	int aux_cont_resultado_final = 0;
		
	if(expressao_booleana.Length <= 5){
	   if(expressao_booleana == "true"){
		   aux_cont_resultado_final = 1;
	   }else if(expressao_booleana == "false"){
		   aux_cont_resultado_final = 0;
	   }
	}
	 else if(!verificarCombinacaoExpressao(expressao_booleana)){
 		Console.WriteLine("Expressão Inválida!");
		 return;
	 }else{
		string operador_expressao = "";
		string expressao_proximo = "";
		string expressao_anterior = "";
		string palavra = "";
		string aux_palavra = "";
		
		string [] cortes = expressao_booleana.Split(' ');
		string [] expressao_cortada = null;
		int tam = 0;
		int aux_cont_true = 0;
		int aux_cont_false = 0;

		int verificando_espaco_branco = 0;
		
		for(int i = 0; i < cortes.Length; i++)
		    if(string.IsNullOrEmpty(cortes[i]))
			    verificando_espaco_branco++;
		
		if(verificando_espaco_branco > 0){
			expressao_cortada = new string[cortes.Length - 1];
			int j = 0;
			for(int i = 0; i < cortes.Length; i ++){
			   if(!string.IsNullOrEmpty(cortes[i])){ 
			     expressao_cortada[j] =((string)cortes[i]).Trim();
				 aux_palavra += expressao_cortada[j] +" ";
				 j++;
			   }
				
			}
			cortes = expressao_cortada;
		    expressao_booleana = aux_palavra.Trim();
		}
		
		for(int i = 0; i < cortes.Length; i++){ 
			for(int j = 0; j < expressoes.Count; j++){
				if(cortes[i] != expressoes[j]){
				   tam++;   
				}
			}

			if(tam == expressoes.Count){
			    expressao_anterior = cortes[i];
			   if(i < cortes.Length - 1){
				 operador_expressao = cortes[i + 1];
			   }
			   if( i < cortes.Length - 2){
				 expressao_proximo = cortes[i + 2];
			   }
				if(string.IsNullOrEmpty(palavra)){
				  palavra += expressao_anterior + " " +operador_expressao + " "+expressao_proximo+" ";
				}else{
					palavra = palavra.Trim();
					palavra += " "+ operador_expressao + " "+expressao_proximo; 
				}

				if(!string.IsNullOrEmpty(operador_expressao)){
				  if(!(string.IsNullOrEmpty(expressao_anterior) && string.IsNullOrEmpty(expressao_proximo))){
					if(operador_expressao == "and"){
						if(expressao_anterior == "true" && expressao_proximo == "true"){
							aux_cont_true++;
						}else{
						    aux_cont_false++;	
						}
					}else if(operador_expressao == "or" || operador_expressao == "xor"){
						if(expressao_anterior == "false" && expressao_proximo == "false"){
							aux_cont_false++;
						}else if(expressao_proximo == "false"){
								aux_cont_false++;
						}else{
							aux_cont_true++;	
						}
					}

				    if(aux_cont_true > 0){
					  if(aux_cont_false > 0){	
					   	 aux_cont_false = 0;
					  }
				   }
				  }
				}
			}
		 tam = 0;
		 if(palavra.Trim().Length == expressao_booleana.Length){
		    break;	 
		 }
		}
		if(aux_cont_false >= 1)
			aux_cont_resultado_final = aux_cont_false;
		else
			aux_cont_resultado_final = aux_cont_true;
	 }
		
	  Console.WriteLine("Quantidade de True possíveis: "+aux_cont_resultado_final);		
	}
	
	public static bool verificarCombinacaoExpressao(string expressao){
		
	   String palavra = "";
	   int verificacao_encontrado = 0;
	   int aux_encontrado = 0;
	   int aux_nao_encontrado = 0 ; 
		
	   for(int i= 0; i < expressao.Length; i++){
			if(expressao[i] != ' '){
				palavra += expressao[i];
			}
		   
		    if(expressao[i] == ' '){
				for(int j = 0; j < expressoes.Count; j++){
			 	    if(palavra == expressoes[j]){
						string aux_expressao = "";
						for(int k = i + 1; k < expressao.Length; k++){
						    aux_expressao += expressao[k];
							if(aux_expressao.Length >=4){
							  if(aux_expressao == "true" || aux_expressao == "false"){
								  verificacao_encontrado++;
							  }
							}else{
							  	aux_nao_encontrado++;
							}
							if(verificacao_encontrado > 0)
								break;
						}

						if(verificacao_encontrado > 0){
							break;
						}else if(aux_nao_encontrado > 0){
						  	aux_encontrado = 0;
							aux_nao_encontrado = aux_encontrado;
						}
					}
			    }
				palavra = "";
			 }
			if(verificacao_encontrado > 0){
			  aux_encontrado = verificacao_encontrado;
			  verificacao_encontrado = 0;	
			}
		   
		   if ( i + 1 == expressao.Length){
		   	for(int j = 0; j < expressoes.Count; j++){
			     if(palavra == expressoes[j]){
					 aux_encontrado = 0;
					 break;
				 }
		   	}
		   }
		}

		if(aux_encontrado > 0){
			return true;
		}
		return false;
	}
}
