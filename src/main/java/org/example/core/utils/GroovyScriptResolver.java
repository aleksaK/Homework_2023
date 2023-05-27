package org.example.core.utils;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class GroovyScriptResolver {

    private final GroovyShell shell;

    @Autowired
    public GroovyScriptResolver(GroovyShell shell) {
        this.shell = shell;
    }

    public Object runMethodFromBaseScript(String methodName) {
        try {
            Script script = shell.parse(new File("src/main/java/org/example/core/scripts/base/", "BaseScript.groovy"));
            return script.invokeMethod(methodName, null);
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public Object runMethodFromScript(String scriptName, String methodName, Object arg) {
        try {
            Script script = shell.parse(new File("src/main/java/org/example/core/scripts/", scriptName));
            Script factors = shell.parse(new File("src/main/java/org/example/core/scripts/", "factors.groovy"));
            Binding binding = new Binding();
            binding.setVariable("factors", factors);
            script.setBinding(binding);
            return script.invokeMethod(methodName, arg);
        }
        catch (IOException e) {
            throw new RuntimeException();
        }
    }

}