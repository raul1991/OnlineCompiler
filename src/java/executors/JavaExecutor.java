/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executors;

import utils.Utils;
import beans.ProgramResult;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author rahulbawa
 */
public class JavaExecutor extends ProgramExecutor{

    @Override
    public File setupEnvironment(String program) throws IOException {
        
        String usersDir = WORKING_DIR+PACKAGE_NAME;
        if(new File(usersDir).exists() || Utils.makeDirectory(usersDir)) {
            //Create the program file
            System.out.println("Setting up environment...");
            File programFile = new File(usersDir+File.pathSeparator+MAIN_CLASS+".java");
            PrintWriter writer = new PrintWriter(programFile);
            writer.write("package "+ PACKAGE_NAME+";\n");
            writer.write(program);
            writer.flush();
            writer.close();
            return programFile;
        }
        else {
            throw new IOException("Workspace could not be created.");
        }
    }

    @Override
    public ProgramResult executeProgram(File program) throws IOException {
        System.out.println("Executing the program now..."+ program.canRead()+ program.canWrite());

        String parent = program.getParentFile().getParentFile().getAbsolutePath();
        String command = "javac %s && java -cp %s %s.%s";
        String[] commands = new String[]{
            "/bin/bash",
            "-c",
            String.format(command,
            program.getAbsolutePath(),
            parent,
            PACKAGE_NAME,
            MAIN_CLASS),
        };
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.redirectErrorStream(true);
        
        Process executorProcess = builder.start();
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(executorProcess.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while((line = reader.readLine())!=null) {
            sb.append(line);
        }
        reader.close();
        System.out.println(sb);
        ProgramResult result = new ProgramResult();
        result.setOutput(sb.toString());
        
        return result;
    }

    @Override
    public String processResult(ProgramResult result) {
        return String.format(OUTPUT_FORMAT, result.getOutput());
    }
    
}
