using System;
using System.Collections.Generic;

public class Program
{
	private static Dictionary<string, Coordenada> coordenadas = new Dictionary<string, Coordenada>()
	{{"A", new Coordenada{ponto= "A",valor = 0}},
	{"B", new Coordenada{ponto= "B",valor = 0}},
    {"C", new Coordenada{ponto= "C",valor = 0}},
    {"D", new Coordenada{ponto= "D",valor = 0}},
	{"E", new Coordenada{ponto= "E",valor = 0}}
	};
	
	public static void Main()
	{	
	 coordenadas = Inserir(coordenadas);
	 
	if(coordenadas == null){
		Console.WriteLine("Valor inválido");
		return;
	}
		
		
	foreach(KeyValuePair<string, Coordenada> item in coordenadas){
		Console.WriteLine(item.Key);
		Console.WriteLine(item.Value.valor);
	   }
	}
	
	public static Dictionary<string, Coordenada> Inserir(Dictionary<string, Coordenada> coordenadas){
		int num = -1;
		List<Coordenada> novas_coordenadas = new List<Coordenada>();
		
		foreach(KeyValuePair<string, Coordenada> item_coordenada in coordenadas){
			Console.WriteLine("Ponto: "+item_coordenada.Key);
			Console.WriteLine("Digite o valor da coordenada: ");
			string valorDigitado = Console.ReadLine().Trim();
			num = Conversao(valorDigitado);
			if(num < 0 || string.IsNullOrEmpty(valorDigitado))
				break;
			Console.WriteLine(num);
			novas_coordenadas.Add(new Coordenada{ponto= item_coordenada.Key, valor = num});
		}
		
		if(num < 0)
			return null;
		
		foreach(Coordenada coordenada in novas_coordenadas)
		   if(coordenadas.ContainsKey(coordenada.ponto))
		   	  coordenadas[coordenada.ponto] = coordenada;
		return coordenadas;
	}
	
	public static int Conversao(string valorDigitado){
		int num = 0;
		if(int.TryParse(valorDigitado, out num))
		 	return num;
		else 
			return -1;
	}
}

public class Coordenada{
  public string ponto;
  public int valor;
	
  public Coordenada(){
  }	
  public Coordenada(int valor){
   this.valor = valor;
  }
  public Coordenada(string ponto){
   this.ponto = ponto; 
  }
  public Coordenada(string ponto, int valor){
   this.ponto = ponto; 
   this.valor = valor;
 }
}
