/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executors;

import beans.ProgramResult;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author rahulbawa
 */
public abstract class ProgramExecutor {
    public static final String WORKING_DIR = System.getProperty("user.home")+"/Desktop/online-compiler/";
    public static final String PACKAGE_NAME = "user"+System.currentTimeMillis();
    public static final String MAIN_CLASS = "Main";
    public static final String OUTPUT_FORMAT = "<h1>OUTPUT:</h1> <br><p> <code>%s</code>";
    public abstract File setupEnvironment(String program) throws IOException;
    public abstract ProgramResult executeProgram(File program) throws IOException;
    public abstract String processResult(ProgramResult result);
    
    public final String start(String program) throws IOException {
        File p = setupEnvironment(program);
        ProgramResult result = executeProgram(p);
        return processResult(result);
    }
}
