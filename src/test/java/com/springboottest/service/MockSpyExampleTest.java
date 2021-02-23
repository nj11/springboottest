package com.springboottest.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MockSpyExampleTest {

    @Mock
    private List<String> mockList;

    @Spy
    private List<String> spyList = new ArrayList();

    @Test
    public void testMockList() {
        //by default, calling the methods of mock object will do nothing
        mockList.add("test");
        assertNull(mockList.get(0));
    }

    @Test
    public void testSpyList() {
        //spy object will call the real method when not stub
        spyList.add("test");
        assertEquals("test", spyList.get(0));
    }


    @Test
    public void testMockWithStub() {
        //try stubbing a method
        String expected = "Mock 100";
        when(mockList.get(100)).thenReturn(expected);
        assertEquals(expected, mockList.get(100));
    }


    @Test
    public void testSpyWithStub() {
        //stubbing a spy method will result the same as the mock object
        String expected = "Spy 100";
        //take note of using doReturn instead of when
        doReturn(expected).when(spyList).get(100);
        assertEquals(expected, spyList.get(100));
    }

    // Difference between mocking, stubbing and spying
    // Difference between mocking, stubbing and spying
    @Test
    public void differenceBetweenMockingSpyingAndStubbing() {

        //Here, we had initial real object list,
        //in which we added one element and expected size to be one.
        List list = new ArrayList();
        list.add("abc");
        assertEquals(1, list.size());

        //we spy real object.
        List spyList = spy(list);
        //So we declared that we stubbed method - size() on spy object which will return 10
        // , no matter what is actual size.
        when(spyList.size()).thenReturn(10);
        assertEquals(10, spyList.size());

        //In a nutshell, you will spy real object and stub some of the methods.
        //Mockito warns that partial mocking isn't a good practice and you should revise
        // your Object Oriented architecture. Spy (or partial mocking) is recommended to test legacy code.
    }

}
