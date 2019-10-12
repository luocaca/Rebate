package me.luocaca.rebate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class FileTextReader {


    public static String getFileText(String path) throws Exception {


        File file = new File(path);


        FileInputStream fileInputStream = null;

        fileInputStream = new FileInputStream(file);


//            new ByteArrayInputStream(string.getBytes())

        InputStreamReader inputreader
                = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputreader);


        String lineString = "";

        StringBuffer stringBuffer = new StringBuffer();

        while ((lineString = bufferedReader.readLine()) != null) {
            stringBuffer.append(lineString);
        }

        return stringBuffer.toString();


    }


}
