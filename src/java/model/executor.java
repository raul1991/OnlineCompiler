 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.*;

/**
 *
 * @author user
 */
public class executor {
    private final String file_path_win = "\\Desktop\\javaExamples\\";
    private final String file_path_win_users = "\\Desktop\\javaExamples\\users";
    private final String file_path_linux = "/Desktop/javaExamples/";
    private final String file_path_linux_users = "/Desktop/javaExamples/users";
    private final String PATH_TO_SCRIPT = "/Users/rahulbawa/java/OnlineCompiler/scripts";
    private long random_sno;
    private StringBuilder error;
    private StringBuilder output;
    private final String pwd = System.getProperty("user.home");
    private String OS;

    public String execute(String data) throws IOException {
        OS = System.getProperties().getProperty("os.name");
        System.out.println(OS);

        makeFileinDir(data);
        if (getRandom_sno() != 0) {
            String file_path = null;
            if ("Linux".equals(OS) || "Mac OS X".equals(OS)) {
                file_path = file_path_linux;
                Process p = Runtime.getRuntime().exec(PATH_TO_SCRIPT+"/script.sh");
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                BufferedReader reader_error = new BufferedReader(new InputStreamReader(p.getErrorStream()));
                PrintWriter writer = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
                String str = reader.readLine();
                System.out.println("line---" + str);

                /**
                 * Path to examples directory
                 */
                writer.println(pwd + file_path_linux);
                writer.flush();

                /**
                 * Path to users directory
                 */
                str = reader.readLine();
                System.out.println("line---" + str);
                writer.println(pwd + file_path_linux_users + getRandom_sno() + "/");
                writer.flush();


                /**
                 * package name
                 */
                str = reader.readLine();
                System.out.println("line---" + str);
                writer.println(getRandom_sno());
                writer.flush();





                str = null;
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
                reader.close();
                reader_error.close();
                writer.close();
                return "Error :"+error.toString() + "<br/> Output :" + output.toString();

            } else if ("windows".equals(System.getProperties().getProperty("os.name"))) {
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
        return "Error :"+error.toString() + "<br/>Output :<br/>" + output.toString();
    }

    /**
     * TODO make sure to have os independent path separators
     *
     * @param data
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void makeFileinDir(String data) throws FileNotFoundException, IOException {
        long random_sno = System.currentTimeMillis();
        if (OS.equals("Windows")) {
            File parentfolder = new File(System.getProperty("user.home") + "/Desktop/javaExamples/users" + String.valueOf(random_sno));
            boolean flag = parentfolder.mkdir();
            File child_file = new File(parentfolder, "Main.java");
            if (flag) {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(child_file));
                writer.write("package  users" + random_sno + ";" + "\n" + data);
                writer.close();
                setRandom_sno(random_sno);
            } else {
                setRandom_sno(0);
            }
        } else {

            Process p = Runtime.getRuntime().exec(PATH_TO_SCRIPT+"/mkdir.sh");
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(p.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            writer.println(pwd + file_path_linux);
            writer.flush();
            writer.println("users" + random_sno);
            writer.flush();
            data = data.replace('\n', ' ');
            writer.write("package users" + random_sno + ";" + data);
            writer.flush();

            writer.close();
            setRandom_sno(random_sno);
        }
    }

    /**
     * @return the random_sno
     */
    public long getRandom_sno() {
        return random_sno;
    }

    /**
     * @param random_sno the random_sno to set
     */
    public void setRandom_sno(long random_sno) {
        this.random_sno = random_sno;
    }
}
