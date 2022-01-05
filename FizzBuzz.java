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
    
    public static List<Integer> divisor_3 = new ArrayList<>();
    public static List<Integer> divisor_5 = new ArrayList<>();
    public static List<Integer> nums = new ArrayList<>();
    
    public static void main(String[] args) {
        nums = iniciarLista();
        verificarDivisor();
    }
    
    private static List<Integer> iniciarLista(){
        List<Integer> nums = new ArrayList<>();
        for(int i = 1; i <= 100; i++){
            nums.add(i);
        }
        return nums;
    }
    
    private static void verificarDivisor(){
        int aux_divisor_3 = 0;
        int aux_divisor_5 = 0;
        
        for(Integer num: nums){
            if(num % 3 == 0){
                aux_divisor_3++;
            }
            if(num % 5 == 0){
                aux_divisor_5++;
            }
            
            if(aux_divisor_5 == 0){
                System.out.println("Fizz");
            }
            
            else if(aux_divisor_3 == 0){
                System.out.println("Buzz");
            }
            else{
                if(aux_divisor_3 > 0 && aux_divisor_5 > 0){
                    System.out.println(" FizzBuzz ");
                }
            }
            aux_divisor_3 = 0;
            aux_divisor_5 = 0;
        }
    }
    
