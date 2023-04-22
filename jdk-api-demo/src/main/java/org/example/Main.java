package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> kk = new ArrayList<String>();
        List<Object> a = kk.stream().filter(x -> x.contains("a")).map(x -> x + 1).collect(Collectors.toList());

    }
}
