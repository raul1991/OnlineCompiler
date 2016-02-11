/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.JavaExecutor;
import model.Program;
import model.ProgramExecutor;
import model.ProgramResult;
import model.executor;

/**
 *
 * @author user
 */
public class Servlet_Recieve_Create extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write_result = resp.getWriter();

        String file_data=req.getParameter("file");
        String file_type=req.getParameter("type");

//        executor run = new executor();
//        String output = run.execute(file_data);
        String result;
        if(file_type.equalsIgnoreCase("html")) {
            result = file_data;
        }    
        else {
            //Execute the program
            ProgramExecutor executor = new JavaExecutor();
            result = executor.start(file_data);
        }  
        //Create response
        write_result.println(result);
        write_result.close();
    
}

}
