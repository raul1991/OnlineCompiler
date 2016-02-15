/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;

/**
 *
 * @author rahulbawa
 */
public final class Utils {
    
    private Utils(){
        //Can't be instantiated
    }
    
    public static final boolean makeDirectory(String path) {
        return new File(path).mkdirs();
    }
    
    public static final boolean insertProgram(String content) {
        boolean isInserted = false;
        
        
        return isInserted;
    }
}
