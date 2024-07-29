package logSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import javax.swing.filechooser.FileSystemView;
import java.time.LocalDateTime;
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

    /*
     * Obtiene la ruta de la carpeta de documentos del usuario.
     * Para los raros que tengan dos discos y lo tengan en A: (como yo).
     */
    private static String documentsPath(){
        return FileSystemView.getFileSystemView().getDefaultDirectory().getAbsolutePath();
    }

    /*
     * Crea un archivo de registro en la carpeta de documentos del usuario.
     */
    private static boolean createFile(){
        boolean created = false;
        try {
            System.out.println("Buscando carpeta de documentos...");
            path = documentsPath();
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            
            System.out.println("Creando archivo de registro...");
            String dateFormat = date.format(fileDateFormat);
            path += "/CopaAmericaLog/" + dateFormat + ".log";
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            created = file.createNewFile();
            writer = new BufferedWriter(new FileWriter(file, true));
        } catch (Exception e) {
            System.out.println("Error: " + e.getLocalizedMessage()+ "\n" + e.getMessage());
        }
        return created;
    }

    /*
     * Verifica si el archivo de registro existe.
     */
    private static boolean fileExists(){
        boolean existe = false;
        if (new File(path).exists()) {
            existe = true;
        }
        return existe;
    }

    /**
     * Escribe un mensaje en el archivo de registro incluyendo fecha y hora del mensaje.
     * @param message un String con el mensaje a escribir en el archivo de registro.
     */
    public static void write(String message){
        if (fileExists()) {
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter fileDateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
            String dateTime = date.format(fileDateFormat);
            writeAux("["+dateTime+"]"+message);
        }else{
            System.out.println("El archivo de registro fue borrado o movido de lugar.");
            System.out.println("Creando nuevo archivo de registro...");
            if (createFile() ) {
                writeAux(message);
            }
        }
    }

    /*
     * El autentico metodo encargado de escribir en el archivo de registro.
     */
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
