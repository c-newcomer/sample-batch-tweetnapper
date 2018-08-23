package snatcher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileScooter {

    public void copyFiles(String srcDir, String destDir, SimpleDateFormat sdf, String fe) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm");
        String currentTime = dateFormat.format(date);
        File folder = new File(srcDir);
        File destination = new File(destDir);

        if (!destination.exists()) {
            destination.mkdir();
        }

        if (folder.listFiles() != null) {
            File[] listOfFiles = folder.listFiles();
            for (int i = 0; i < listOfFiles.length; i++) {
                if (!listOfFiles[i].getName().endsWith(currentTime + fe)) {
                    Path from = Paths.get(listOfFiles[i].toURI());
                    Path to = Paths.get(new File(destDir + listOfFiles[i].getName()).toURI());
                    try {
                        Files.move(from, to, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception e) {
                        System.out.println("Uh oh.");
                    }
                }
            }
        }
    }

    public void concatFiles() {
        BufferedReader breader = null;
        BufferedWriter bw = null;
        try {

            File outputFileDir = new File("batch-ready");
            File outputFile = new File("batch-ready/tweetArchive-collection.txt");
            if (!outputFileDir.exists()) {
                outputFileDir.mkdir();
            }
            if (outputFile.exists()) {
                outputFile.delete();
                outputFile.createNewFile();
            }

            FileWriter fw = new FileWriter(outputFile, true);
            bw = new BufferedWriter(fw);

            File sourceFolder = new File("batch-source/");
            if (sourceFolder.listFiles() != null) {
                File[] listOfFiles = sourceFolder.listFiles();
                String temp = "";

                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt")) {
                        // System.out.println("Copying file " + listOfFiles[i].getName());
                        breader = new BufferedReader(new FileReader(listOfFiles[i]));
                        String line;
                        while ((line = breader.readLine()) != null) {
                            bw.write(line);
                            bw.newLine();
                        }
                        breader.close();
                    }
                }
                bw.write(temp);
                bw.close();
                if (breader != null) {
                    breader.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
