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
import model.executor;

/**
 *
 * @author user
 */
public class Servlet_Recieve_Create extends HttpServlet {
private executor run;
String output;
    private PrintWriter write_result;
@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        write_result=resp.getWriter();
//        createResponse("hello");    
        String file_data=req.getParameter("file");
        String file_type=req.getParameter("type");
//        createResponse(file_type);

            run=new executor();
            output = run.execute(file_data);
            createResponse(output);
        
    
}
public void createResponse(String response)
{
        write_result.println(response);
        write_result.close();

}

}
