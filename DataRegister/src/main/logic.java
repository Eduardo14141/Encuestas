package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class logic {
    Scanner scanner = new Scanner(System.in);
    String options = "abcdef";
    int[][][] encuesta = new int[4][10][7];
    String[] topics = {"Educación", "Sexualidad", "Alcoholismo y drogadicción", "Recreación"};
    
    public void read_users_answer(){
        int iteration = 1;
        String _continue;
        do{
            System.out.println("Encuesta #"+iteration);
            int topic = 0;
            while(topic < 4){
                System.out.println("Tema: "+ topics[topic]);
                for (int question = 0; question < 10; question++) {
                    System.out.print("Pregunta "+ (question + 1) + ".- ");
                    String answer = scanner.nextLine();
                    if(options.contains(answer.toLowerCase()) && ! answer.equals(""))
                        encuesta[topic][question][options.indexOf(answer.toLowerCase())]++;
                    else
                        encuesta[topic][question][6]++;
                }
                topic++;
            }
            System.out.println("¿Cotinuar con otra encuesta?, presione \"n\" para salir");
            _continue = scanner.nextLine();
        }while(!"n".equalsIgnoreCase(_continue));
        System.out.println("Preparando ...");
        System.out.println("Ingresa cómo quieres que se llame el archivo, no ingreses .csv, ya se agrega por defecto");
        String file_name = scanner.nextLine();
        writeCSV(encuesta, file_name);
    }
    public void writeCSV(int[][][] encuestas_write, String file_name){
        PrintWriter pw = null;
        try{
            pw = new PrintWriter(new File(file_name+".csv"));
        }catch(FileNotFoundException e){
            System.out.println("Hubo un problema al guardar los datos de la última encuesta");
            System.out.println(e.getMessage());
        }
        StringBuilder builder = new StringBuilder();
        String columns = "Sección, pregunta, a, b, c, d, e, f, sin contestar";
        builder.append(columns+"\n");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                builder.append(topics[i] + "," + (j + 1) + ",");
                for (int k = 0; k < 7; k++) {
                    builder.append(encuestas_write[i][j][k]+",");
                }
                builder.append("\n");
            }
        }
        pw.write(builder.toString());
        pw.close();
    }
}
