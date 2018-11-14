package com.cg.mba.test;

import org.junit.Assert;
import org.junit.Test;

import com.cg.mba.exception.MbaException;
import com.cg.mba.service.MbaServiceImpl;

public class TestClassJPA {
	
	@Test
    public void testName_1() throws MbaException{
    
        String name="Akshay";
        MbaServiceImpl service=new MbaServiceImpl();
        boolean result= service.validateName(name);
        Assert.assertEquals(true,result);
    }
    @Test
    public void testName_2() throws MbaException{
    
        String name="john";
        MbaServiceImpl service=new MbaServiceImpl();
        boolean result= service.validateName(name);
        Assert.assertEquals(false,result);
    }
    
    
    @Test
    public void testMobile_1() throws MbaException{
    
        String mobNo="RON123558";
        MbaServiceImpl service=new MbaServiceImpl();
        boolean result= service.validateMoileNo(mobNo);
        Assert.assertEquals(false,result);
    }
    @Test
    public void testMobile_2() throws MbaException{
    
        String mobNo="9421734025";
        MbaServiceImpl service=new MbaServiceImpl();
        boolean result= service.validateMoileNo(mobNo);
        Assert.assertEquals(true,result);
    }


}
