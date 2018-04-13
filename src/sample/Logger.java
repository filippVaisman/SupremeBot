package sample;

import java.io.*;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class Logger {
    private File file;
    private Calendar calendar;

    public Logger(String filePath){
        file = new File(filePath);
    }

    Logger(){
        new File(System.getProperty("user.home")+"/AppData/Local/Supreme Bot").mkdir();
        file = new File(System.getProperty("user.home")+"/AppData/Local/Supreme Bot/log.html");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        calendar = Calendar.getInstance();
    }

    void log(String message){
        System.out.println(message);
        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String tmp="";
            String text="";

            while((tmp=bufferedReader.readLine())!=null){
                text+=tmp;
            }

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(text+message+"<br>");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void log(int message){
        System.out.println(message);
        try {
            FileWriter fw = new FileWriter(file);
            fw.append(message+"\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
