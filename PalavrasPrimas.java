import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Main
{
    public static int count = 1; 
    public static Map<Character, Integer> alfabeto = new HashMap<Character, Integer>();
    
    public static void main(String[] args) {
  
        palavraPrimaIndividual("teste");
        palavraPrimaComposta("teste");
    }
    
    public static void palavraPrimaComposta(String palavra){
       if(validarPalavra(palavra, "[^a-zA-Z]*[^\\p{L}+$][^0-9]")){
           System.out.print("Palavra inválida");
       }else{    
           List<Integer> valores = values(palavra);
           int aux_soma_valores = 0 ;
           int aux_count_primo = 0;
           for(int i = 0; i < valores.size(); i++){
               aux_soma_valores += valores.get(i);  
           }
           
           for(int i= 2; i < aux_soma_valores; i++){
               if(aux_soma_valores % i == 0){
                   aux_count_primo++;
               }
           }
           
           if(aux_count_primo == 1){
               System.out.println("A palavra é prima");
           }else{
               System.out.println("A palavra não é prima");
           }
       }
    }
    public static void palavraPrimaIndividual(String palavra){
        if(validarPalavra(palavra,"[^a-zA-Z]*[^\\p{L}+$][^0-9]")){
         System.out.print("Palavra inválida");
        }else{
        
        alfabeto = inicializarAlfabeto("Minusculo");
        alfabeto = inicializarAlfabeto("Maisculo");
        
        List<Integer> valores = values(palavra);
        List<Integer> list_primos = primos(valores);

        Character aux_letra = null;
        int count_primo = 0;
        
        for(Integer val: valores){
            for (Map.Entry<Character,Integer> pair : alfabeto.entrySet()) {
                Integer valor = pair.getValue();
                if(valor == val){
                    aux_letra =  pair.getKey();
                    break;
                }
            }
            
            if(aux_letra != null){
                for(Integer primo: list_primos){
                    if(primo == val){
                       count_primo++;
                       break;
                    }
                }
                if(count_primo > 0){
                    System.out.println(aux_letra+" "+val+ " é primo!");
                }else{
                    System.out.println(aux_letra+" "+val+ " não é primo!");
                }
                count_primo = 0;
            }
            aux_letra = null;
        }
      }
    }
    public static List<Integer> removerValoresRepetidos(List<Integer> valores){
        int aux_count_repetido = 0;
        List<Integer> nova_lista = new ArrayList<Integer>();
        int count_nao_encontrado = 0;
        
        for(int i=0; i < valores.size(); i++){
            for(int j = 1; j < valores.size(); j++){
                if(valores.get(i) == valores.get(j)){
                    aux_count_repetido++;
                }
                if(aux_count_repetido == 2){
                    break;
                }
            }
            
            if(aux_count_repetido <= 2){
                for (int k =0; k < nova_lista.size(); k++)
                     if(valores.get(i) != nova_lista.get(k)){
                         count_nao_encontrado++;
                }
                if(count_nao_encontrado == nova_lista.size())
                    nova_lista.add(valores.get(i));
            }
            count_nao_encontrado = 0;
            aux_count_repetido = 0;
        }
        return nova_lista;
    }
    public static List<Integer> primos(List<Integer> valores){
        List<Integer> novos_valores = removerValoresRepetidos(valores);
        List<Integer> primos = new ArrayList<Integer>();
        
        int maior_valor = maior(novos_valores);
        int aux_count = 0;
        
        for(int i =0; i < novos_valores.size(); i++){
            List<Integer> multiplos = new ArrayList<Integer>();
            for(int j = 2; j < maior_valor; j++ ){
                if(novos_valores.get(i) % j == 0){
                    multiplos.add(j);
                }
            }

            if(multiplos.size() == 1){
               primos.add(multiplos.get(0));
            }
            aux_count = 0;
        }
        return primos; 
    }
    public static List<Integer> values(String palavra){
        List<Integer> valores = new ArrayList<Integer>();
        for(int i =0; i< palavra.length(); i++){
            Character letra = Character.valueOf(palavra.charAt(i));
            Integer valor = null;
            if(alfabeto.containsKey(letra))
              valor = alfabeto.get(letra);
            if(valor != null)
                valores.add(valor);
            valor = null;
        }
        return valores;
    }
    public static Map<Character, Integer> inicializarAlfabeto(String formato){
        for(char ch = 'A'; ch <= 'Z'; ch++){
            if(formato == "Maisculo")
                alfabeto.put(Character.valueOf(ch), count);
            else if(formato == "Minusculo")
                alfabeto.put( Character.toLowerCase(ch), count);
            count++;
        }
        return alfabeto;
    }
    public static Pattern returnPattern(String formato){
       return Pattern.compile(formato);
    }
    public static boolean search(String palavra, Pattern pattern){
        Matcher matcher = pattern.matcher(palavra);
        return matcher.find();
    }
    public static boolean validarPalavra(String palavra,String formato){
        Pattern pattern = returnPattern(formato);
        return search(palavra, pattern);
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
}

