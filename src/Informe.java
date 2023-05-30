import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Informe {
    ArrayList<Tarea> tareas;

    Informe() {
        tareas = new ArrayList<>();
    }

    public void agregar(Tarea tarea) { // Agrega tareas el arraylist
        tareas.add(tarea);
    }

    public void descargar() { // Descarga el informe de las tareas
        StringBuilder sb = new StringBuilder();

        for (Tarea tarea1 : tareas) { // guarda la información de cada tarea en el arraylist
            String labelText = "-> " + tarea1.nombre + " (" + tarea1.tiempoTotal + ")\n"
                    + "Fecha de inicio: " + tarea1.fechaIncio + "\nFecha de fin: " + tarea1.fechaFin + "\n";
            sb.append(labelText).append("\n");
        }

        String textToWrite = sb.toString();

        JFileChooser fileChooser = new JFileChooser(); // Abre el mini-explorador de archivos
        fileChooser.setDialogTitle("Guardar Informe");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getPath(); // Guarda la dirección donde se va a escribir
            if (!filePath.endsWith(".txt")) { // Agrega la extensión al archivo
                filePath += ".txt";
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(textToWrite); // Escribe el texto en el archivo
                JOptionPane.showMessageDialog(null, "Informe guardado con éxito :)");
            } catch (IOException error) {
                error.printStackTrace(); // En caso de error, muestra que hubo un error
                JOptionPane.showMessageDialog(null, "Error. No se pudo guardar :(");
            }
        }
    }
}
