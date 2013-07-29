package com.odata;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.core4j.Enumerable;
import org.core4j.Func;
import org.core4j.Funcs;
import org.odata4j.producer.ODataProducer;
import org.odata4j.producer.ODataProducerFactory;
import org.odata4j.producer.inmemory.InMemoryProducer;

import com.model.Employee;

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

         // expose an large list of integers as an entity-set called "Integers"
         producer.register(Integer.class, Integer.class, "Integers", new Func<Iterable<Integer>>() {
           public Iterable<Integer> apply() {
             return Enumerable.range(0, Integer.MAX_VALUE);
           }
         }, Funcs.method(Integer.class, Integer.class, "intValue"));

         // expose an large list of integers as an entity-set called "Employees"
         producer.register(Employee.class, Integer.class, "Employees", new Func<Iterable<Employee>>() {
           public Iterable<Employee> apply() {
        	   List<Employee> emp = new ArrayList<Employee>();
        	   emp.add(new Employee("Jack",20));
        	   emp.add(new Employee("Jill",18));
             return emp;
           }
         }, Funcs.method(Employee.class, Integer.class, "Employees"));

         return producer;

    }

}