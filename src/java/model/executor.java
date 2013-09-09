/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.util.Random;

/**
 *
 * @author user
 */
public class executor {
    private  String[] cmds;
    private String file_path="\\Desktop\\javaExamples\\users";
    private int random_sno;
    private StringBuilder error;
    private StringBuilder output;
    private String pwd=System.getProperty("user.home");        
    public String execute(String data) throws IOException
    {   
        makeFileinDir(data);
        if(getRandom_sno()!=0){
        
        
        Process p=Runtime.getRuntime().exec("cmd /c"+"cd "+pwd+file_path+getRandom_sno()+"&&"+"javac Main.java"+"&&"+" "+"set CLASSPATH=.;"+pwd+"\\Desktop\\javaExamples;"+" "+"&&"+"java users"+getRandom_sno()+"."+"Main");
        BufferedReader in_error=new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedReader in_no_error=new BufferedReader(new InputStreamReader(p.getInputStream()));
        String error_line = null;  
       String result_line=null; 
       error=new StringBuilder();
       output=new StringBuilder();
while ((error_line = in_error.readLine()) != null) {  
                  error.append(error_line+"</br>");
}  
while ((result_line = in_no_error.readLine()) != null) {  
                  output.append(result_line+"</br>");
}  
System.out.print(error.toString()+"/"+output.toString());
    return error.toString()+"/"+output.toString();
}
    else
            return "could not make the file";
    }

    private void makeFileinDir(String data) throws FileNotFoundException, IOException {
        int random_sno=new Random().nextInt(9999);
        File parentfolder=new File(System.getProperty("user.home")+"\\Desktop\\javaExamples\\users"+String.valueOf(random_sno));
        boolean flag=parentfolder.mkdir();
        File child_file=new File(parentfolder,"Main.java");
        if(flag)
        {
        OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(child_file));
        writer.write("package  users"+random_sno+";"+"\n"+data);
        System.out.print("done!!!"+random_sno);
        writer.close();
                setRandom_sno(random_sno);
        }
        else
                setRandom_sno(0);
}

    /**
     * @return the random_sno
     */
    public int getRandom_sno() {
        return random_sno;
    }

    /**
     * @param random_sno the random_sno to set
     */
    public void setRandom_sno(int random_sno) {
        this.random_sno = random_sno;
    }

}
