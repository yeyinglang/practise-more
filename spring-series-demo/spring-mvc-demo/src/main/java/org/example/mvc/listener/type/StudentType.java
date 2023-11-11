package org.example.mvc.listener.type;

public class StudentType implements ChildType<Integer> {
    public Integer t;

    public Integer getT() {
        return t;
    }
    public <M> void getMM(M m){
        System.out.println(m);
    }
    public void getMMA(String  m){
        System.out.println(m);
    }
}
