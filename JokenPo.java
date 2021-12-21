
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

public class Main
{
    public static Map<Integer, JokenPo> combinacoes = new HashMap<Integer, JokenPo>();

    
    public static int jogadas = 0;
    public static int venceu = 0;
    public static int perdeu = 0;
    public static int empate = 0;
    public static int aux_venceu = 0;
    public static int aux_perdeu = 0;
    public static int aux_empate = 0;
    
    public static void main(String[] args) {
        
        iniciar();
    }
    private static void inicializarCombinacoes(){
        combinacoes.put(1, new JokenPo("Pedra"));
        combinacoes.put(2, new JokenPo("Tesoura"));
        combinacoes.put(3, new JokenPo("Papel"));
    }
    private static JokenPo jogadaJogador(){
        System.out.println("Opções:");
        System.out.println("1) Pedra ");
        System.out.println("2) Tesoura");
        System.out.println("3) Papel");
        
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.next().trim().toLowerCase();
        
        if(!validarPalavra(entrada, "[^0-9]")){
            int entrada_jogada = Integer.parseInt(entrada);
              if(!(entrada_jogada < 1 || entrada_jogada > 3)){
                if(combinacoes.containsKey(entrada_jogada)){
                     return combinacoes.get(entrada_jogada);
                }
              }
        }
        return null;
    }
    private static JokenPo jogadaComputador(){
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            int jogadaComputador = sr.nextInt(3);
            if(jogadaComputador == 0){
                jogadaComputador++;
            }
            if(!combinacoes.containsKey(jogadaComputador)){
                throw new IllegalArgumentException();
            }
            
             return combinacoes.get(jogadaComputador);
        } catch(NoSuchAlgorithmException | NoSuchProviderException | IllegalArgumentException e) {
            System.out.println("Não foi possível realizar a jogada do computador!");
        }
        return null;
    }
    private static void iniciar(){
        inicializarCombinacoes();
        Scanner scanner = null;
        while(true){
            System.out.println("1) Jogar");
            System.out.println("2) Ver resultado");
            System.out.println("3) Sair");
            
            scanner = new Scanner(System.in);
            String entrada = scanner.next().trim();
            if(validarPalavra(entrada, "[^0-9]")){
                System.out.println("Opção inválida!");
            }else{
                int opcao = Integer.parseInt(entrada);
                
                if(opcao == 1){
                    juiz();
                }
                
                if(opcao == 2){
                    resultado();
                }
                
                else if(opcao == 3){
                    System.out.println("Você saiu!");
                    break;
                }
            }
        }
    }
    
    private static void juiz(){
        JokenPo jpJogador = jogadaJogador();
        jpJogador.id = "Você";
        if(jpJogador == null){
            System.out.println("Jogada inválida, tente novamente");
            return;
        }
        JokenPo jpComputador = jogadaComputador();
        jpComputador.id = "Computador";
        if(jpComputador == null){
            System.out.println("Não foi possível jogar com o computador!");
            return;
        }
        
        if(jpComputador.valor == jpJogador.valor){
            aux_empate++;
        }
        else if(jpJogador.valor == "Pedra"){
            if(jpComputador.valor == "Tesoura")
                aux_venceu++;
            else
                aux_perdeu++;
        }
        else if(jpJogador.valor == "Tesoura"){
            if(jpComputador.valor == "Papel")
                aux_venceu++;
            else
                aux_perdeu++;
        }
        else if(jpJogador.valor == "Papel"){
            if(jpComputador.valor == "Pedra")
                aux_venceu++;
            else
                aux_perdeu++;
            
        }
        System.out.println(jpJogador);
        System.out.println(jpComputador);
        
        if(aux_perdeu > 0){
            perdeu += aux_perdeu;
            System.out.println("Você perdeu!");
        }else if(aux_venceu > 0){
            venceu += aux_venceu;
            System.out.println("Você ganhou!");
        }else if(aux_empate > 0){
            empate += aux_empate;
            System.out.println("Empate!");
        }
        
        aux_perdeu = 0;
        aux_venceu = 0;
        aux_empate = 0;
        jogadas++;

    }
    public static void resultado(){
        System.out.println("--------------");
        System.out.println("Jogadas: "+jogadas);
        System.out.println("Empates: "+empate);
        System.out.println("Derrotas: "+perdeu);
        System.out.println("Vencidas: "+venceu);
        System.out.println("--------------");

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
}

class JokenPo{
    
    public String id;
    public String valor;
    
    public JokenPo(){
    }
    
    public JokenPo(String valor){
        this.valor = valor;
    }

    @Override
	public String toString() {
		return "ID: "+id+", valor: "+valor;
	}
}
