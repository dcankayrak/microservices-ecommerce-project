package com.dcankayrak.productservice.core;

import org.springframework.stereotype.Component;
import java.util.Random;

@Component
public class Slugify {
    public String slugify(String word)
    {
        String result = word.trim().toLowerCase();
        char[] oldValue = new char[] { ' ','ö', 'Ö', 'ü', 'Ü', 'ç', 'Ç', 'İ', 'ı', 'Ğ', 'ğ', 'Ş', 'ş' };
        char[] newValue = new char[] { '-','o', 'O', 'u', 'U', 'c', 'C', 'I', 'i', 'G', 'g', 'S', 's' };
        for (int i = 0; i < oldValue.length; i++)
        {
            result = result.replace(oldValue[i], newValue[i]);
        }
        result += "-p-"+createRandomNumber();
        return result;
    }

    public Integer createRandomNumber(){
        Random random = new Random();
        Integer randomNumber = random.nextInt(90000000) + 10000000;
        return randomNumber;
    }
}
