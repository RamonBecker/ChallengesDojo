	
using System;
					
public class Program
{
	public static void Main()
	{
		Anagrama("AMOR");
	}

public static void Anagrama(string palavra){
		 int calc_fatorial = 0;
		 int cont_repetido = 0;
		 Dictionary<string, int> repeticoes = new Dictionary<string, int>();
         Dictionary<string, int> filtro_repeticoes = new Dictionary<string, int>();
		
		char palavra_I = ' ';
		string palavra_repetida = "";
		
		 for(int i = 0; i < palavra.Length; i ++){
			 for(int j = 1; j < palavra.Length; j++){
				 palavra_I = Char.ToLower(palavra[i]);
				 char palavra_J = Char.ToLower(palavra[j]);
				 if(palavra_I.Equals(palavra_J)){
					 palavra_repetida = Convert.ToString(palavra_I);
                     if(!(repeticoes.ContainsKey(palavra_repetida))){
					 	repeticoes.Add(palavra_repetida, 0);
					 }
				 }
			 }
			 if(repeticoes.ContainsKey(palavra_repetida)){
				 cont_repetido = repeticoes[palavra_repetida];
				 cont_repetido++;
				 repeticoes[palavra_repetida] = cont_repetido;
			 }
		 }
		
		filtro_repeticoes =  repeticoes.ToDictionary(p => p.Value > 1);    // repeticoes.Select(p => p.Value > 1).ToDictionary();
		
		foreach( KeyValuePair<string, int> kvp in filtro_repeticoes ) {
              Console.WriteLine("Key = {0}, Value = {1}", kvp.Key, kvp.Value);
         }
	}
}