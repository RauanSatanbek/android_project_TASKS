package com.example.rauansatanbek.tasks;

import java.util.ArrayList;

/**
 * Created by Rauan Satanbek on 24.02.2017.
 */

public class Test {
    public static void main(String args[]) {
        ArrayList<Integer> m = new ArrayList<>();
        m.add(1);
        m.add(2);
        m.add(3);
        m.add(4);
        print(m);
        m.remove(2);

        System.out.println("---------------------------------");
        print(m);
    }

    static void print(ArrayList<Integer> m) {
        for(int i = 0; i < m.size(); i++) {
            System.out.println("index = " + i + " value = " + m.get(i));
        }
    }
}
