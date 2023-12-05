package com.SpringBanking.api.utils;

import java.util.Random;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AccountGeneratorValue {
    private static final Random rand = new Random();
    
    public static String generateCbu(){
        StringBuilder cbu = new StringBuilder();
        for(int i=0; i<23; i++){
            cbu.append(String.valueOf(rand.nextInt(10))); 
        }
        return String.valueOf(cbu);
    }
    public static String generateAlias(String name){
        StringBuilder alias = new StringBuilder("Default." + name +".");
        for (int i = 0; i < rand.nextInt(4, 8); i++) {
            alias.append(rand.nextInt(10));
        }
        return String.valueOf(alias);
    }
}
