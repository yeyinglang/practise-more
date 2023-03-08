package org.example.reactor.async;

public class Server {
    public static void main(String[] args) {
        try(Reactor reactor = new Reactor(4)) {
            reactor.run();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
