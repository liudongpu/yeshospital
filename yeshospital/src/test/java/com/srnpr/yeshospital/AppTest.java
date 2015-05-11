package com.srnpr.yeshospital;

import org.junit.Test;

import com.srnpr.yeshospital.helper.SpellHelper;
import com.srnpr.zapcom.basehelper.TestHelper;


/**
 * Unit test for simple App.
 */
public class AppTest  extends TestHelper
    
{
   
	@Test
	public void TestPY()
	{
		
		
		
		bLogTest(SpellHelper.converterToSpell("我是一只小绵羊 啊 yiyayiya"));
		
		
		
		
	}
	
	
}
