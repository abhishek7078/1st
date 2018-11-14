package com.cg.psj.test;

import org.junit.Assert;
import org.junit.Test;

import com.cg.psj.exception.PsjException;
import com.cg.psj.service.PsjServiceImpl;

public class TestCaseClassJ {
	
	@Test
    public void testName_1() throws PsjException{
    
        String name="Akshay";
        PsjServiceImpl service=new PsjServiceImpl();
        boolean result= service.validateName(name);
        Assert.assertEquals(true,result);
    }
    @Test
    public void testName_2() throws PsjException{
    
        String name="john";
        PsjServiceImpl service=new PsjServiceImpl();
        boolean result= service.validateName(name);
        Assert.assertEquals(false,result);
    }
    
    
    @Test
    public void testMobile_1() throws PsjException{
    
        String mobNo="RON123558";
        PsjServiceImpl service=new PsjServiceImpl();
        boolean result= service.validateMoileNo(mobNo);
        Assert.assertEquals(false,result);
    }
    @Test
    public void testMobile_2() throws PsjException{
    
        String mobNo="9421734025";
        PsjServiceImpl service=new PsjServiceImpl();
        boolean result= service.validateMoileNo(mobNo);
        Assert.assertEquals(true,result);
    }


}
