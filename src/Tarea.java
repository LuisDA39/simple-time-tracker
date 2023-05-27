import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class Tarea extends Tiempo {
    String nombre;
    String tiempoTotal;
    Date fechaIncio;
    Date fechaFin;

    Tarea() {}

    Tarea(String nombre, String tiempoTotal, Date fechaIncio, Date fechaFin) {
        this.nombre = nombre;
        this.tiempoTotal = tiempoTotal;
        this.fechaIncio = fechaIncio;
        this.fechaFin = fechaFin;
    }

    public JPanel crearPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(null);
        panel.setPreferredSize((new Dimension(290, 75)));
        JLabel etiquetaNombre = new JLabel(nombre);
        etiquetaNombre.setFont(new Font("Segoe UI", Font.BOLD, 17));
        JLabel etiquetaTiempo = new JLabel("(" + tiempoTotal + ")");
        JLabel etiquetaFechaI = new JLabel("Inicio: " + fechaIncio.toString());
        JLabel etiquetaFechaF = new JLabel("Fin: " + fechaFin.toString());

        panel.add(etiquetaNombre);
        panel.add(etiquetaTiempo);
        panel.add(etiquetaFechaI);
        panel.add(etiquetaFechaF);
        return panel;
    }

}
