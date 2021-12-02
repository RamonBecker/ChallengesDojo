using System;
using System.Text;
using System.Globalization ;
using System.Linq;
using System.Collections.Generic;
					
public class Program
{
	public static void Main()
	{
		Anagrama("PATO");
	}
	
	public static void Anagrama(string palavra){
		Dictionary<char, int> repeticoes = new Dictionary<char, int>();
		palavra = palavra.ToLower().Trim();
		palavra = RemoverAcentuacao(palavra);
		int aux_tamanho_palavra = palavra.Length;
		int aux_repeticoes_encontradas = 0;
	    int aux_fatorial = fatorial(aux_tamanho_palavra);
		int aux_soma_fatorial_palavras_repetidas = 1;
	
		for(int i = 0; i < aux_tamanho_palavra; i++){
		     for(int j = 1; j < aux_tamanho_palavra; j++){
				 if(palavra[i].Equals(palavra[j])){
					 aux_repeticoes_encontradas++;
				 }
				 
				 if( j - 1 == 0){
					 if(palavra[i].Equals(palavra[j - 1])){
						 aux_repeticoes_encontradas++;
					 }
				 }
			 }
			
			if(aux_repeticoes_encontradas > 1){
				if(!repeticoes.ContainsKey(palavra[i])){
					repeticoes.Add(palavra[i], 0);
				}
				repeticoes[palavra[i]] = aux_repeticoes_encontradas;
			}
			
			aux_repeticoes_encontradas = 0;
		}
		foreach(KeyValuePair<char, int> palavra_repetida in repeticoes){
			int num_repeticoes = palavra_repetida.Value;
			int aux_calc_fatorial_palavra = fatorial(num_repeticoes);	
			aux_soma_fatorial_palavras_repetidas *= aux_calc_fatorial_palavra;	
		}
		
		int calc_final_anagrama = aux_fatorial / aux_soma_fatorial_palavras_repetidas;
		int aux_calc = calc_final_anagrama / aux_tamanho_palavra;
		Console.WriteLine("Número total de anagramas da palavra "+palavra+": "+calc_final_anagrama);
		Console.WriteLine(aux_calc);
		
		int aux_indice = 0;
		
		List<string> palavras_novas = new List<string>();
		
		int possibilidade = 0;
	
		string aux_nova_palavra = new string(palavra);
		
		for(int i = 0; i < calc_final_anagrama; i++){  
			
			for(int j = aux_indice; j < aux_tamanho_palavra; j++){
				if( j - 1 >= 0){
						
				}
			}
			
			if(aux_indice == aux_tamanho_palavra){
			   aux_indice = 0;	
			}
		}
	}
	
	public static int fatorial(int num){
		int aux_calc_fatorial = 0;
		
		for(int i = num; i > 0; i--){
			if(aux_calc_fatorial == 0){
			  aux_calc_fatorial = i;	
			}else{
			  aux_calc_fatorial *= i;
			}
		}
		return aux_calc_fatorial;
	}
	
	public static string RemoverAcentuacao(string text)
	{
    return new string(text
        .Normalize(NormalizationForm.FormD)
        .Where(ch => char.GetUnicodeCategory(ch) != UnicodeCategory.NonSpacingMark)
        .ToArray());
       }
}
