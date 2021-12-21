
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

    public static void main(String[] args) {
    
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
        return " "+this.suspeito+" "+this.arma+ " "+this.local;
    }
}
class Suspeito{
    public int id;
    public string nome;
    public Suspeito(){
    }
    public Suspeito(String nome){
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
    @Override
    public String toString(){
        return "ID: "+this.id+", local: "+this.nome;
    }
}
