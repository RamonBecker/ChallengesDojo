
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Main
{
    public static int count = 1; 
    public static Map<Character, Integer> alfabetoMaisculo = null;
    public static Map<Character, Integer> alfabetoMinusculo = null;
    
    public static void main(String[] args) {
  
      if(validarPalavra("teste","[0-9]")){
         System.out.print("Palavra inválida");
      }else{
    
        alfabetoMaisculo = inicializarAlfebeto("Maisculo");
        alfabetoMinusculo = inicializarAlfebeto("Minusculo");
        
        System.out.println(removerValoresRepetidos(values("Teste")));
      }
    }
    
    public static int maior(List<Integer> valores){
        int aux_maior = 0;
        for(int i = 0; i < valores.size(); i++){
            for(int j = 1; j < valores.size(); j++){
                if(valores.get(i) > valores.get(j)){
                    aux_maior = valores.get(i);
                }
            }
        }
        return aux_maior;
    } 
    
    public static List<Integer> removerValoresRepetidos(List<Integer> valores){
        int aux_count_repetido = 0;
        List<Integer> nova_lista = new ArrayList<Integer>();
        
        for(int i=0; i < valores.size(); i++){
            for(int j = 1; j < valores.size(); j++){
                if(valores.get(i) == valores.get(j))
                    aux_count_repetido++;
            }
            if(aux_count_repetido == 1)
                nova_lista.add(valores.get(i));
         //   else if(aux_count_repetido >= 2)
           //     nova_lista.add(valores.get(i));        
            aux_count_repetido = 0;
        }
        return nova_lista;
    }
    
    public static String primos(List<Integer> valores){
        int maior_valor = maior(valores);
        List<Integer> multiplos = new ArrayList<Integer>();
        List<Integer> primos = new ArrayList<Integer>();
       
        for(int i =0; i < valores.size(); i++){
            for(int j = 2; j < maior_valor; j++ ){
                if(valores.get(i) % j == 0){
                    System.out.println(valores.get(i)+ " "+j);
                }
            }
            
        }
        
                  
          int aux_count = 0;
           

          /*
          for(int j = 2; j < 10; j++){
              for(int i = 1; i < 10; i++){
                  if(i % j == 0){
                     multiplos.add(i);
                  }
              }
              
            for(int k =0; k < multiplos.size(); k++){
                if(j == multiplos.get(k)){
                    if(j != 3 || j != 2)
                        aux_count++;
                }
            }
            
            if(aux_count == 1){
                primos.add(j);
            }
            aux_count = 0;
          }
        */
        return null;
    }
    
    public static List<Integer> values(String palavra){
        List<Integer> valores = new ArrayList<Integer>();
        for(int i =0; i< palavra.length(); i++){
            Character letra = Character.valueOf(palavra.charAt(i));
            Integer valor = null;
            if(alfabetoMaisculo.containsKey(letra))
                valor = alfabetoMaisculo.get(letra);
            else if(alfabetoMinusculo.containsKey(letra))
                valor = alfabetoMinusculo.get(letra);
            if(valor != null)
                valores.add(valor);
            valor = null;
        }
        return valores;
    }
    public static Map<Character, Integer> inicializarAlfebeto(String formato){
        Map<Character, Integer> alfabeto = new HashMap<Character, Integer>();
        for(char ch = 'A'; ch <= 'Z'; ch++){
            if(formato == "Maisculo")
                alfabeto.put(Character.valueOf(ch), count);
            else if(formato == "Minusculo")
                alfabeto.put(Character.toLowerCase(ch), count);
            count++;
        }
        return alfabeto;
    }
    public static boolean validarPalavra(String palavra,String formato){
        Pattern pattern = returnPattern(formato);
        return search(palavra, pattern);
    }
    public static Pattern returnPattern(String formato){
       return Pattern.compile(formato);
    }
    public static boolean search(String palavra, Pattern pattern){
        Matcher matcher = pattern.matcher(palavra);
        return matcher.find();
    }
}

