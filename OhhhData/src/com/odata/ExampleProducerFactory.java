package com.odata;

import java.util.Properties;

import org.core4j.Enumerable;
import org.core4j.Func;
import org.core4j.Funcs;
import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.ODataProducerFactory;
import org.odata4j.producer.inmemory.InMemoryProducer;

public class ExampleProducerFactory implements ODataProducerFactory {

    @Override
    public ODataProducer create(Properties arg0) {
         InMemoryProducer producer = new InMemoryProducer("example");

         // expose this jvm's thread information (Thread instances) as an entity-set called "Threads"
         producer.register(Thread.class, Long.class, "Threads", new Func<Iterable<Thread>>() {
             public Iterable<Thread> apply() {
                 ThreadGroup tg = Thread.currentThread().getThreadGroup();
                 while (tg.getParent() != null)
                     tg = tg.getParent();
                 Thread[] threads = new Thread[1000];
                 int count = tg.enumerate(threads, true);
                 return Enumerable.create(threads).take(count);
             }
         }, Funcs.method(Thread.class, Long.class, "getId"));

         return producer;

    }

}