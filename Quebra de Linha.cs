using System;
					
public class Program
{
	public static void Main()
	{
		separar("Um pequeno xereta viu dez cegonhas felizes.", 10);
	}
	
	public static void separar(string texto, int coluna)
	{
		///string texto = "Um pequeno jabuti xereta viu dez cegonhas felizes.";
		string [] palavras_completas = texto.Split(' ');
		string aux_palavra = "";
		string aux_frase = "";
		int cont_espacos = 0, aux_cont_espacos = 0, m = 0, k = 0, cont= 0, 
		l = 0, cont_palavra_maior = 0;
		
		for(int i = 0; i < palavras_completas.Length; i++)
		{
		    if(palavras_completas[i].Length > coluna)
			{
			   cont_palavra_maior++;
			   break;	
			}
		}
		
		if(cont_palavra_maior == 0)
		{
			for(int i=0; i < texto.Length; i++)
			{
			   if(texto[i].Equals(' '))
			   {
				 cont_espacos++;   
			   }
			}	
			while(true){
				for(int i = k; i< texto.Length; i++)
				{
					aux_palavra += texto[i];
					if(texto[i].Equals(' '))
					{
					  k = i + 1;  
					  aux_cont_espacos++;
					}
					if(cont == coluna)
					{
					   break;	
					}
					
					cont++;
					l = i + 1;
				}

				string [] aux_palavras_cortadas = aux_palavra.Split(' ');

				for(int i = 0; i < palavras_completas.Length; i++)
				{
				   for(int j = 0; j < aux_palavras_cortadas.Length; j++)
				   {
					   if(aux_palavras_cortadas[j].Contains(palavras_completas[i]))
					   {
						   if(aux_palavras_cortadas[j].Length == palavras_completas[i].Length)
						   {
							   aux_frase += palavras_completas[i] +" ";
							   m++;
						   }
					   }
				   }
				}

				if(m <= aux_palavras_cortadas.Length)
				{
				   aux_frase += "\n";	
				}
				aux_palavra = "";
				cont = 0;
				m = 0;
				
				if(cont_espacos == aux_cont_espacos)
				{
					if(l == texto.Length){
					    break;
					}
				}
				l = 0;
				   
			}
		  Console.WriteLine(aux_frase);
		}
		else
		{
			Console.WriteLine("Há palavras que excede o número de colunas!");
		}
	}	
}