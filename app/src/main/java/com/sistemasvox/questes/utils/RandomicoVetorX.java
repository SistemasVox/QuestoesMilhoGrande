package com.sistemasvox.questes.utils;

import android.content.Context;

import com.sistemasvox.questes.bancoDeDados.DataBaseAcess;

import java.util.ArrayList;
import java.util.Random;

public class RandomicoVetorX {
    private static int TAM;
    private static int[] jogo;
    static ArrayList<Integer> jogoA = new ArrayList<>();

    public RandomicoVetorX(int tamTotal, int tam) {
        this.TAM = tamTotal;
        this.jogo = new int[tam];
    }



    public static ArrayList<Integer> gerarJogoAleatorio() {
        for (int i = 0; i < jogo.length; i++) {
            jogo[i] = Integer.parseInt(gerarNumAleatorio());
            while (verificaIgual(i, jogo[i], jogo)) {
                jogo[i] = Integer.parseInt(gerarNumAleatorio());
            }
        }
        shellSort(jogo);
        jogoA.clear();
        jogoA = convertArray(jogo);
        return jogoA;
    }

    public static ArrayList<Integer> convertArray(int[] jogo2) {
        for (int i = 0; i < jogo2.length; i++) {
            jogoA.add(Integer.valueOf(jogo2[i]));
        }
        return jogoA;
    }

    public static boolean verificaIgual(int p, int n, int[] jogo) {
        for (int k = 0; k < jogo.length; k++) {
            if ((k != p) && (jogo[k] == n)) {
                return true;
            }
        }
        return false;
    }

    protected static String gerarNumAleatorio() {
        Random radom = new Random();
        return String.valueOf(radom.nextInt(TAM));
    }

    public static int interRand(int i, int j) {
        Random radom = new Random();
        return (i + (radom.nextInt((j + 1) - i)));
    }

    public static void shellSort(int[] nums) {
        int h = 1;
        int n = nums.length;
        while (h < n) {
            h = h * 3 + 1;
        }
        h /= 3;
        while (h > 0) {
            for (int i = h; i < n; i++) {
                int c = nums[i];
                int j = i;
                while ((j >= h) && (nums[(j - h)] > c)) {
                    nums[j] = nums[(j - h)];
                    j -= h;
                }
                nums[j] = c;
            }
            h /= 2;
        }
    }

    public static void shellSort(ArrayList<Integer> nums) {
        int h = 1;
        int n = nums.size();
        while (h < n) {
            h = h * 3 + 1;
        }
        h /= 3;
        while (h > 0) {
            for (int i = h; i < n; i++) {
                int c = nums.get(i);
                int j = i;
                while ((j >= h) && (nums.get(j - h) > c)) {
                    nums.set(j, nums.get(j - h));
                    j -= h;
                }
                nums.set(j, c);
            }
            h /= 2;
        }
    }
}