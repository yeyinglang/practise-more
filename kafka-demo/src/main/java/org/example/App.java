package org.example;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsResult;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        KafkaAdminClient kafkaAdminClient = KafkaAdminClient.create(null);
        ListTopicsResult listTopicsResult = kafkaAdminClient.listTopics();
        listTopicsResult.listings().get(10L);
        System.out.println( "Hello World!" );
    }
}
