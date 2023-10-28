package com.stc.problemsolving;

import static org.junit.jupiter.api.Assertions.*;

import com.stc.problemsolving.service.IReverseWithinParenthesisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReverseStringWithinParenthesesTests {

    @Autowired
    IReverseWithinParenthesisService reverseWithinParenthesisService;

    @Test
    public void testStringWithParentheses_InnerStringShouldBeReversed(){
        String input = "abcde(efk)hijkl";
        assertEquals("abcde(kfe)hijkl",reverseWithinParenthesisService.reverse(input));
    }

    @Test
    public void testStringWithoutParentheses_NothingShouldChange(){
        String input = "abdjnbasdf";
        assertEquals(input, reverseWithinParenthesisService.reverse(input));
    }

    @Test
    public void testStringWithCoupleOfParentheses_BothParenthesesLettersShouldBeReversed(){
        String input = "dd(df)a(ghhh)";
        assertEquals("dd(fd)a(hhhg)", reverseWithinParenthesisService.reverse(input));
    }

}
