package jogo.utils;

public class IntMath {
    public static int pow(int base, int exp) {
        int ret = 1;
        while(exp-- > 0) ret *= base;
        return ret;
    }
}
