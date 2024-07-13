package logSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.filechooser.FileSystemView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Log {
    private static String path;
    private static File file;
    private static BufferedWriter writer;

    public static void initialize(){
        System.out.println("Inicializando sistema de registros...");
        if (createFile()) {
            System.out.println("Archivo de registro creado con éxito.");
        }else{
            System.out.println("Error al crear archivo de registro.");
        }
    }

    private static String documentsPath(){
        return FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath();
    }

    private static boolean createFile(){
        boolean created = false;
        try {
            System.out.println("Buscando carpeta de documentos...");
            path = documentsPath();
            DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            LocalDate date = LocalDate.now();
            System.out.println("Creando archivo de registro...");
            path += "/CopaAmericaLog/" + date.format(fileDateFormat) + ".txt";
            file = new File(path);
            created = file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file, true));
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage()+ "\n" + e.getMessage());
        }
        return created;
    }

    private static boolean fileExists(){
        boolean existe = false;
        if (new File(path).exists()) {
            existe = true;
        }
        return existe;
    }

    public static void write(String message){
        if (fileExists()) {
            writeAux(message);
        }else{
            System.out.println("El archivo de registro fue borrado o movido de lugar.");
            System.out.println("Creando nuevo archivo de registro...");
            if (createFile() ) {
                writeAux(message);
            }
        }
    }

    private static void writeAux(String message){
        if (writer != null) {
            try {
                writer.write(message);
                writer.newLine();
                writer.flush();
            } catch (Exception e) {
                System.out.println("Error: " + e.getLocalizedMessage()+ "\n" + e.getMessage());
            }
        }
    }
}
