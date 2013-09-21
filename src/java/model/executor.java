 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class executor {

    private String[] cmds;
    private String file_path_win = "\\Desktop\\javaExamples\\";
    private String file_path_win_users = "\\Desktop\\javaExamples\\users";
    private String file_path_linux = "/Desktop/javaExamples/";
    private String file_path_linux_users = "/Desktop/javaExamples/users";
    private int random_sno;
    private StringBuilder error;
    private StringBuilder output;
    private String pwd = System.getProperty("user.home");
    private String OS;

    public String execute(String data) throws IOException {
        OS = System.getProperties().getProperty("os.name");
        System.out.println(OS);
        createLog(OS);
        makeFileinDir(data);
        if (getRandom_sno() != 0) {
            String file_path = null;
            createLog("got the os");
            if ("Linux".equals(OS)) {
                file_path = file_path_linux;
                Process p = Runtime.getRuntime().exec("script.sh");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader reader_error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
                String str = reader.readLine();
//                System.out.println("" + str);
                createLog("started the log");
                if (str.startsWith("Please")) {
//            showDialog("inside first please");
                    createLog("entered the path to examples");
                    writer.println(pwd + file_path_linux+"/");
                    writer.close();
                }
                
                
                str = reader.readLine();
//                System.out.println("" + str);
                if (str.startsWith("Please")) {
//            showDialog("inside first please 2");
//                    createLog("entered the path to users");
//                    System.out.println(pwd + file_path_linux_users + getRandom_sno()+"/");
                    writer.println(pwd + file_path_linux_users + getRandom_sno()+"/");
                    writer.close();
                }
                
                 str = reader.readLine();
//                System.out.println("" + str);
                if (str.startsWith("Please")) {
//            showDialog("inside first please 3");
//                    createLog("entered the random number");
                    writer.println(getRandom_sno());
                    writer.close();
                }
                
                
                
               

                str = null;
//            System.out.println("##########################################");
                String error_line = null;
                String result_line = null;
                error = new StringBuilder();
                output = new StringBuilder();
                while ((error_line = reader_error.readLine()) != null) {
                    error.append(error_line).append("</br>");
                }
                while ((result_line = reader.readLine()) != null) {
                    output.append(result_line).append("</br>");
                }
                System.out.print(error.toString() + "/" + output.toString());
//        System.out.println("##########################################");
                reader.close();
                reader_error.close();
                writer.close();
                return error.toString() + "/" + output.toString();

            } else if ("windows".equals(System.getProperties().getProperty("os.name"))) {
//                file_path=file_path_win;
//                Process p=Runtime.getRuntime().exec("cmd /c"+"cd "+pwd+file_path+getRandom_sno()+"&&"+"javac Main.java"+"&&"+" "+"set CLASSPATH=.;"+pwd+"\\Desktop\\javaExamples;"+" "+"&&"+"java users"+getRandom_sno()+"."+"Main");
                return compileExecute();
            } else {
                return null;
            }


        } else {
            return "BLANK";
        }

    }

    /**
     * utility method for windows os
     *
     * @return
     * @throws IOException
     */
    public String compileExecute() throws IOException {
        Process p = Runtime.getRuntime().exec("cmd /c" + "cd " + pwd + file_path_win_users + getRandom_sno() + "&&" + "javac Main.java" + "&&" + " " + "set CLASSPATH=.;" + pwd + "\\Desktop\\javaExamples;" + " " + "&&" + "java users" + getRandom_sno() + "." + "Main");
        BufferedReader in_error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        BufferedReader in_no_error = new BufferedReader(new InputStreamReader(p.getInputStream()));
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));

        String error_line = null;
        String result_line = null;
        error = new StringBuilder();
        output = new StringBuilder();
        while ((error_line = in_error.readLine()) != null) {
            error.append(error_line).append("</br>");
        }
        while ((result_line = in_no_error.readLine()) != null) {
            output.append(result_line).append("</br>");
        }
        System.out.print(error.toString() + "/" + output.toString());
        return error.toString() + "/" + output.toString();
    }
//    else
//            return "could not make the file";

    /**
     * TODO make sure to have os independent path separators
     *
     * @param data
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void makeFileinDir(String data) throws FileNotFoundException, IOException {
        int random_sno = new Random().nextInt(9999);
        if (OS.equals("Windows")) {
            File parentfolder = new File(System.getProperty("user.home") + "/Desktop/javaExamples/users" + String.valueOf(random_sno));
            boolean flag = parentfolder.mkdir();
            File child_file = new File(parentfolder, "Main.java");
            if (flag) {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(child_file));
                writer.write("package  users" + random_sno + ";" + "\n" + data);
                System.out.print("done!!!" + random_sno);
                writer.close();
                setRandom_sno(random_sno);
            } else {
                setRandom_sno(0);
            }
        } else {

            Process p = Runtime.getRuntime().exec("mkdir.sh");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            writer.println(pwd + file_path_linux);
//            createLog(pwd + file_path_linux);
//            writer.close();
            writer.println("users" + random_sno);
//            createLog("users" + random_sno);
//            writer.close();
            writer.println("package users" + random_sno + ";" + data);
            writer.close();
            setRandom_sno(random_sno);
        }
    }

    /**
     * @return the random_sno
     */
    public int getRandom_sno() {
        createLog("inside getRandSno");
        return random_sno;
    }

    /**
     * @param random_sno the random_sno to set
     */
    public void setRandom_sno(int random_sno) {
        this.random_sno = random_sno;
    }

    public void createLog(String checkpoint) {
        try {
            PrintWriter writer = new PrintWriter("/home/raul/Desktop/outN.txt");
            writer.write(checkpoint);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(executor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("exception" + ex);
        }
    }
}
