
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


// https://dojopuzzles.com/problems/caixa-eletronico/

public class Main
{
    public static List<Integer> nums = new ArrayList<>();
    public static  Troco troco = new Troco();
    public static  Conta conta = new Conta();
    
    public static void main(String[] args) {
        iniciar();
    }
    
    
    private static void iniciar(){
        Scanner scanner = new Scanner(System.in);
        String opcao = "";
        int aux_op = 0;
        while(true){
            System.out.println("1) Depositar");
            System.out.println("2) Sacar");
            System.out.println("3) Saldo");
            System.out.println("4) Sair");
            System.out.println("Digite a opção: ");
            opcao = scanner.next().trim();
            if(!(Util.validarPalavra(opcao, "[^0-9]"))){
               aux_op = Integer.parseInt(opcao);
               if(aux_op == 1)
                   depositar(new Scanner(System.in));
               if(aux_op == 2)
                  sacar(new Scanner(System.in));
               if(aux_op == 3)
                   saldo();
               if(aux_op == 4)
                 break;
           }else{
               System.out.println("Entrada inválida!");
            } 
        }
        if(aux_op == 4)
           System.out.println("Você saiu");
    }

    private static void saldo(){
        conta.saldo();
    }
    
    private static void depositar(Scanner scanner){
       System.out.println("Digite o valor que você quer depositar: ");
        try {
          Double valor = Double.parseDouble(scanner.next().trim());
          conta.depositar(valor);
          System.out.println("Valor "+valor+" depositado com sucesso");
        } catch(Exception e) {
            System.out.println("Valor digitado inválido!");
        }
    }
    
    private static void sacar(Scanner scanner){
        System.out.println("Digite o valor que você quer sacar: ");
        try {
            Double valor = Double.parseDouble(scanner.next().trim());
            List<Nota> notas_filtro = new ArrayList<>();
            Nota aux_nota_anterior = null;
            Nota aux_nota = new Nota();
            Nota aux_nota_atual = new Nota();
            
            for(Nota nota : troco.notas){
                if(nota.valor <= valor){
                    notas_filtro.add(nota);
                }
            }
           
           for(Nota nota:  notas_filtro){
               if(aux_nota_anterior == null) {
                  aux_nota.valor =  valor - nota.valor;
                  aux_nota_atual.valor = aux_nota.valor;
               }
               else if(aux_nota_anterior != null){
                   aux_nota.valor = aux_nota_anterior.valor - aux_nota.valor;
                   aux_nota_atual.valor += aux_nota.valor;
               }
                aux_nota_anterior = nota;
               System.out.println("Anterior:"+aux_nota_anterior);
               System.out.println(aux_nota.valor);
               System.out.println("SOMA: "+aux_nota_atual.valor);
           }
           
           System.out.println(notas_filtro);
          
        } catch(Exception e) {
            System.out.println("Valor digitado inválido!");
        }
    }
}


class Nota{
    public Double valor;
    public Integer quantidade;
    
    public Nota(){
    }
    
    public Nota(Double valor){
        this.valor = valor;
    }
    
    public Nota(Integer quantidade){
        this.quantidade = quantidade;
    }
    
    public Nota(Double valor, Integer quantidade){
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    @Override
    public String toString(){
        return ""+this.valor+", "+this.quantidade;
    }
}
class Troco{
    public List<Nota> notas;
    public Double valorTotal(){
        Double soma = 0.0;
        for(Nota nota : notas){
            if(nota.quantidade > 0){
                soma += nota.valor;
            }
        }
        return soma;
    }
    
    public Troco(){
        inicializarNotas();
    }
    private void inicializarNotas(){

        this.notas = new ArrayList<>();
        this.notas.add(new Nota(100.0,0));
        this.notas.add(new Nota(50.0,0));
        this.notas.add(new Nota(25.0,0));
        this.notas.add(new Nota(20.0,0));
        this.notas.add(new Nota(15.0,0));
        this.notas.add(new Nota(10.0,0));
        this.notas.add(new Nota(5.0,0));
    }
    @Override
    public String toString(){
        return ""+this.notas;
    }
}
class Conta{
  public Double saldo;
  
  public Conta(){
      this.saldo = 0.0;
  }
  
  public Conta(Double saldo){
      this.saldo = saldo;
  }
  
  public void sacar(Double valor){
      if(valor <= 0){
          throw new IllegalArgumentException("Não foi possível sacar, valor inválido!");
      }
      if(!(valor > saldo)){
         this.saldo = this.saldo - valor;
      }
      throw new IllegalArgumentException("Não foi possível sacar, saldo insuficiente!");
  }
  
  public void depositar(Double valor){
      if(valor <= 0){
          throw new IllegalArgumentException("Não foi possível depositar, valor inválido!");
      }
      this.saldo += valor;
  }
  
  public void saldo(){
      System.out.println("Seu saldo atual é: "+this.saldo);
  }
  
  @Override
  public String toString(){
      return ""+saldo;
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



