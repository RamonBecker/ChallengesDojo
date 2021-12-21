
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
    
    public static void main(String[] args) {
        inicializarCombinacoes();
        jogadaJogador();
    }
    
    private static void inicializarCombinacoes(){
        combinacoes.put(0, new JokenPo("Pedra", new JokenPo("Tesoura"), new JokenPo("Papel")));
        combinacoes.put(1, new JokenPo("Tesoura", new JokenPo("Papel"), new JokenPo("Pedra")));
        combinacoes.put(2, new JokenPo("Papel", new JokenPo("Pedra"), new JokenPo("Tesoura")));
    }
    private static void jogadaJogador(){
        Scanner scanner = new Scanner(System.in);
        String entrada = scanner.next().trim().toLowerCase();
        System.out.println(validarPalavra(entrada, "[0-9]"));
    }
    private static JokenPo jogadaComputador(){
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
            int jogadaComputador = sr.nextInt(2);
            if(!combinacoes.containsKey(jogadaComputador))
                throw new IllegalArgumentException();
             return combinacoes.get(jogadaComputador);
        } catch(NoSuchAlgorithmException | NoSuchProviderException | IllegalArgumentException e) {
            System.out.println("Não foi possível realizar a jogada do computador!");
        }
        return null;
    }
    
    private static void juiz(){
        
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
    
    public String valor;
    public JokenPo ganha;
    public JokenPo perde;
    
    public JokenPo(){
    }
    
    public JokenPo(String valor){
        this.valor = valor;
    }
    
    public JokenPo(String valor, JokenPo ganha, JokenPo perde){
        this.valor = valor;
        this.ganha = ganha;
        this.perde = perde;
    }
    
    @Override
	public String toString() {
		return "Valor: "+valor+ ", Ganha: "+ganha.valor+ ", Perde: "+perde.valor;
	}
}
