package practicum;

import java.util.Random;

public class RandomString {
    static final String AB = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    static final String NM = "1234567890";
    static Random rnd = new Random();

    public static String randomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
    public static String randomPhone(){
        StringBuilder sb = new StringBuilder(9);
        for(int i = 0; i < 9; i++)
            sb.append(NM.charAt(rnd.nextInt(NM.length())));
        return "+79" + sb;
    }
}
