

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.Scanner;
import java.util.Random;

import  java.lang.IllegalArgumentException; 

import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

// Descubra o assassino

public class Main
{
    public static List<Suspeito> suspeitos = new ArrayList<Suspeito>(){{
        add(new Suspeito(1, "Charles B. Abbage"));
        add(new Suspeito(2, "Donald Duck Knuth"));
        add(new Suspeito(3, "Ada L. Ovelace"));
        add(new Suspeito(4, "Alan T. Uring"));
        add(new Suspeito(5, "Ivar J. Acobson"));
        add(new Suspeito(6, "Ras Mus Ler Dorf"));
    }};
    public static List<Arma> armas = new ArrayList<Arma>(){{
        add(new Arma(1,"Peixeira"));
        add(new Arma(2,"DynaTAC 8000X"));
        add(new Arma(3,"Trezoitão"));
        add(new Arma(4,"Trebuchet"));
        add(new Arma(5,"Maça"));
        add(new Arma(6,"Gládio"));
    }};
    public static List<Local> locais = new ArrayList<Local>(){{
       add(new Local(1, "Redmond")); 
       add(new Local(2, "Palo Alto")); 
       add(new Local(3, "San Francisco")); 
       add(new Local(4, "Restaurante no Fim do Universo")); 
       add(new Local(5, "São Paulo")); 
       add(new Local(6, "Cupertino")); 
       add(new Local(7, "Helsinki")); 
       add(new Local(8, "Maida Vale"));
       add(new Local(9, "Toronto")); 
    }};
    public static String entrada_suspeito = "";
    public static String entrada_arma = "";
    public static String entrada_local = "";
    public static void main(String[] args) {
       iniciarTeoria();
    }
    private static Teoria criarTeoria(){
        try {
    
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            int suspeitoTeoria = sr.nextInt(6);
            int armaTeoria = sr.nextInt(6);
            int localTeoria = sr.nextInt(9);
            
            if(suspeitoTeoria == 0)
               suspeitoTeoria++;
            if(armaTeoria == 0)
               armaTeoria++;
            if(localTeoria == 0)
               localTeoria++;
        
            Suspeito aux_suspeito = null;
            Arma aux_arma = null;
            Local aux_local = null;   
            
            for (Suspeito suspeito : suspeitos ){
                 if(suspeito.id == suspeitoTeoria)
                     break;
                aux_suspeito = suspeito;  
            } 
            
            for(Arma arma: armas){
                if(arma.id == armaTeoria)
                    break;
                aux_arma = arma;
            }
            
            for(Local local: locais){
                if(local.id == localTeoria)
                    break;
                aux_local = local;    
            }
            
            Teoria teoria = new Teoria(aux_suspeito, aux_arma, aux_local);
            
            if(teoria.suspeito == null || teoria.arma == null || teoria.local == null)
               throw new IllegalArgumentException();
            return teoria;   
            
        } catch(NoSuchAlgorithmException | NoSuchProviderException | IllegalArgumentException e) {
            System.out.println("Não foi possível criar a teoria!");
        }
        return null;
    }
    private static void iniciarTeoria(){
        String opcao = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1) Dar testemunho: ");
            System.out.println("3) Sair");
            opcao = scanner.next().trim();
            
            if(!(Util.validarPalavra(opcao, "[^0-9]"))){
                int aux_op = Integer.parseInt(opcao);
                if(aux_op == 1){
                    if(entradaSuspeito(scanner)){
                        if(entradaArma(scanner)){
                            if(entradaLocal(scanner)){
                                int aux_entrada_arma = Integer.parseInt(entrada_arma);
                                int aux_entrada_suspeito = Integer.parseInt(entrada_suspeito);
                                int aux_entrada_local = Integer.parseInt(entrada_local);
                                
                                Teo
                                
                            }else{
                                System.out.println("Entrada inválida, tente novamente!");
                            }
                        }else{
                            System.out.println("Entrada inválida, tente novamente!");
                        }
                    }else{
                        System.out.println("Entrada inválida, tente novamente!");
                    }
                }else if(aux_op == 2){
                    
                }
                else if(aux_op == 3){
                    System.out.println("Você saiu");
                    break;
                }
            }else{
                System.out.println("Entrada inválida, tente novamente!");
            }      
        }
    }
    private static void verificarTeoria(Teoria teoria){
        
        for(Suspeito suspeito: suspeitos){
            if(suspeito.id == teoria.suspeito.id){
                break;
            }    
        }
    }    
    private static boolean entradaLocal(Scanner scanner){
       System.out.println("Locais");
       System.out.println("1, Redmond\n"+
                         "2, Palo Alto\n"+
                         "3, San Francisco\n"+
                         "4, Restaurante no Fim do Universo\n"+
                         "5, São Paulo\n"+
                         "6, Cupertino\n"+
                         "7, Helsinki\n"+
                         "8, Maida Vale\n"+
                         "9, Toronto\n");
     System.out.println("Diga qual foi o local: ");
     entrada_local = scanner.next().trim();
     if(Util.validarPalavra(entrada_local, "[^0-9]"))
        return false;
     return true;
    }
    private static boolean entradaArma(Scanner scanner){
        System.out.println("Armas");
        System.out.println("1, Peixeira\n"+
                           "2, DynaTAC 8000X\n"+
                           "3, Trezoitão\n"+
                           "4, Trebuchet\n"+
                           "5, Maça\n"+
                           "6, Gládio\n"
        );
        System.out.println("Diga qual foi a arma: ");
        entrada_arma = scanner.next().trim();
        if(Util.validarPalavra(entrada_arma, "[^0-9]"))
           return false;
        return true;
    }
    private static boolean entradaSuspeito(Scanner scanner){
        System.out.println("Suspeitos\n");
        System.out.println("1, Charles B. Abbage\n"+
                           "2, Donald Duck Knuth\n"+
                           "3, Ada L. Ovelace\n"+
                           "4, Alan T. Uring\n"+
                           "5, Ivar J. Acobson\n"+
                           "6, Ras Mus Ler Dorf\n"
                           );
        System.out.println("Diga o suspeito: ");
        entrada_suspeito = scanner.next().trim();
        
        if(Util.validarPalavra(entrada_suspeito, "[^0-9]"))
            return false;
        return true;
    }
}
class Util{
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
}
class Teoria{
    public Suspeito suspeito;
    public Arma arma;
    public Local local;
    
    public Teoria(){
    }
    public Teoria(Suspeito suspeito, Arma arma, Local local){
        this.suspeito = suspeito;
        this.arma = arma;
        this.local = local;
    }
    @Override
    public String toString(){
        return " "+this.suspeito+" | "+this.arma+ " | "+this.local;
    }
}
class Suspeito{
    public int id;
    public String nome;
    public Suspeito(){
    }
    public Suspeito(String nome){
        this.nome = nome;
    }
    public Suspeito(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return "ID"+this.id+", Suspeito: "+this.nome;
    }
}
class Arma{
    public int id;
    public String nome;
    public Arma(){
    }
    public Arma(String nome){
        this.nome = nome;
    }
    public Arma(int id, String nome){
        this.nome = nome;
    }
    @Override
    public String toString(){
        return "ID: "+this.id+", Arma: "+this.nome;
    }
}
class Local{
    public int id;
    public String nome;
    public Local(){
    }
    public Local(String nome){
        this.nome = nome;
    }
    
    public Local(int id, String nome){
        this.id = id;
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.id+", local: "+this.nome;
    }
}
