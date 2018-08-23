package snatcher;

import java.io.File;
import java.beans.FeatureDescriptor;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonWriter {
  public static void write(String json, SimpleDateFormat sdf, String fe) {
    BufferedWriter bw = null;
    FileWriter fw = null; 
    try {
      String mycontent = json;
      Date date = new Date();
      File fileDir = new File("temp/");
      File file = new File("temp/tweetArchive-" + sdf.format(date) + fe);
      if (!fileDir.exists()) {
        fileDir.mkdir();
      }
      if (!file.exists()) {
        file.createNewFile();
      }

      fw = new FileWriter(file, true);
      bw = new BufferedWriter(fw);
      bw.write(mycontent);
      bw.newLine();
      
      // System.out.println("\nFile written successfully to /temp");

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null)
          bw.close();
        if (fw != null)
          fw.close();
      } catch (Exception ex) {
        System.out.println("Error in closing the Writers" + ex);
      }
    }
  }
}