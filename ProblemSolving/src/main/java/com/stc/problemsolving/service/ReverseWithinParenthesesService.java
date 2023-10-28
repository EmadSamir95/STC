package com.stc.problemsolving.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Stack;

@Service
public class ReverseWithinParenthesesService implements IReverseWithinParenthesisService{
    @Override
    public String reverse(String input) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> bracketsMap = getBracketsMap();
        StringBuilder output = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (stack.isEmpty()) {
                if (bracketsMap.containsKey(c)) {
                    stack.push(c);
                    output.append(c);
                } else {
                    output.append(c);
                }
            } else {
                if (bracketsMap.containsValue(c)) {
                    while (!stack.isEmpty()) {
                        char letter = stack.pop();
                        if(!bracketsMap.containsKey(letter))
                            output.append(letter);
                    }
                    output.append(c);
                } else {
                    stack.push(c);
                }
            }
        }
        return output.toString();
    }

    private HashMap<Character,Character> getBracketsMap(){
        HashMap<Character,Character> bracketsMap = new HashMap<>();
        bracketsMap.put('(',')');
        return bracketsMap;
    }
}
