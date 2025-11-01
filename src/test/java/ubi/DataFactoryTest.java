package ubi;


import org.junit.Before;
import org.junit.Test;
import ubi.data.DataFactory;
import ubi.operations.Operation;

import static org.junit.Assert.*;

public class DataFactoryTest {
    DataFactory dataFactory;

    @Before
    public void setUp() {
        dataFactory = new DataFactory();
    }

    @Test
    public void take() {
        dataFactory.take(100);
        assertEquals("counter is not correct", 100, DataFactory.getCounter());

    }

    @Test
    public void apply() {
        Operation op = (a) -> a * 5;
        dataFactory.apply("test", op);
        assertEquals("counter is not correct", 1, DataFactory.getCounter());
    }

    @Test
    public void getCol() {
        Operation op = (a) -> a * 5;
        Operation op1 = (a) -> a * 5 + a * 2;
        Operation op2 = (a) -> a * 5 + a;
        Operation op3 = (a) -> a * 5 - a;

        dataFactory.apply("test", op);
        dataFactory.apply("test1", op1);
        dataFactory.apply("test2", op2);
        dataFactory.apply("test3", op3);
        Operation opT0 = dataFactory.getCol("test");
        Operation opT1 = dataFactory.getCol("test1");
        Operation opT2 = dataFactory.getCol("test2");
        Operation opT3 = dataFactory.getCol("test3");
        assertNotNull("empty operation", opT0);
        assertNotNull("empty operation", opT1);
        assertNotNull("empty operation", opT2);
        assertNotNull("empty operation", opT3);
        assertEquals("wrong operation", op, opT0);
        assertEquals("wrong operation", op1, opT1);
        assertEquals("wrong operation", op2, opT2);
        assertEquals("wrong operation", op3, opT3);


    }
}