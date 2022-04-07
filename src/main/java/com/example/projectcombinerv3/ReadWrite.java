package com.example.projectcombinerv3;

import java.io.*;
import java.nio.file.Path;

public class ReadWrite {

    public void readFromA_File(Path pathTaken){

        File convertedToFile = pathTaken.toFile();
        try {
            BufferedReader _reader = new BufferedReader(new FileReader(convertedToFile));

            String readTextLine;
            while((readTextLine = _reader.readLine()) != null){
                System.out.println(readTextLine);
                writeToA_File(readTextLine);        //----> writing the already red content to the file.
            }
            _reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToA_File(String fileContent){

        try {
            BufferedWriter _writer = new BufferedWriter(new FileWriter("output_file.csv", true));
            _writer.write("\n" + fileContent);
            _writer.close(); // #---- Always make sure to close the BufferWriter object. Otherwise, nothing will be written into the file ----#
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
