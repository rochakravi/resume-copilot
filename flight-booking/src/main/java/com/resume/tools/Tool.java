package com.resume.tools;


import java.util.Map;

public interface Tool {

    String name();

    String execute(Map<String, Object> input);
}
