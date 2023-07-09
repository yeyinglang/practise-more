package org.example.type;

public class StudentType<T extends String> implements ChildType<Number> {
    public Integer t;
    public T demoF;

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
