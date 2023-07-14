package com.dcankayrak.productservice.core;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Slugify {
    public String slugify(String word, char prefix) {
        String result = clearUrl(word);
        result = replaceTheTurkishToEnglish(result);
        result += "-" + prefix + "-" + createRandomNumber();
        return result;
    }
    public String replaceTheTurkishToEnglish(String slug){
        String result = slug;
        char[] oldValue = new char[]{' ', 'ö', 'Ö', 'ü', 'Ü', 'ç', 'Ç', 'İ', 'ı', 'Ğ', 'ğ', 'Ş', 'ş'};
        char[] newValue = new char[]{'-', 'o', 'O', 'u', 'U', 'c', 'C', 'I', 'i', 'G', 'g', 'S', 's'};
        for (int i = 0; i < oldValue.length; i++) {
            result = result.replace(oldValue[i], newValue[i]);
        }
        return result;
    }
    public String clearUrl(String slug) {
        String result = slug.trim().toLowerCase().replaceAll("[^a-zA-Z0-9\\s]", "")
                .replaceAll("\\s", "-");
        return result;
    }

    public Integer createRandomNumber() {
        Random random = new Random();
        Integer randomNumber = random.nextInt(90000000) + 10000000;
        return randomNumber;
    }
}
