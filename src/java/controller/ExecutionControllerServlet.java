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
import executors.JavaExecutor;
import executors.ProgramExecutor;

/**
 *
 * @author user
 */
public class ExecutionControllerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write_result = resp.getWriter();

        String file_data=req.getParameter("file");
        String file_type=req.getParameter("type");
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
