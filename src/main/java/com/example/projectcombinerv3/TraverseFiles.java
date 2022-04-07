package com.example.projectcombinerv3;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TraverseFiles {

    private BlockingQueue<String> fileQueue = new ArrayBlockingQueue<>(200);
    String file1, takeFile1;

    public TraverseFiles() {
    }

    public void visitFile(File _path) {
        try {
            Files.walkFileTree(Paths.get(String.valueOf(_path)), new SimpleFileVisitor<Path>(){

                //setting up file visitor interface
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    file1 = file.toString();
                    //adding file paths to the blocking queue.
                    fileQueue.offer(file1);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("queue" + fileQueue);
    }

    public void writeToOneFile(){
        for(int i = 0; i <= fileQueue.size(); i++ ) {
            try {
                takeFile1 = fileQueue.poll(10L, TimeUnit.MILLISECONDS);
                if (takeFile1 == null) {
                    System.out.println("object (file path) were not obtained from the blocking queue.");
                }
                else if(takeFile1.length() > 0){
                    Path p1 = Paths.get(takeFile1);
                    System.out.println(p1);

                    //passing file path to ReadWrite class
                    ReadWrite rw = new ReadWrite();
                    rw.readFromA_File(p1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
