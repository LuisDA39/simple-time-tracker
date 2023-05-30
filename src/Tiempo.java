import javax.swing.*;

public class Tiempo { // con ayuda de: https://youtu.be/0cATENiMsBE
    int elapsedTime = 0;
    int segundos = 0;
    int minutos = 0;
    int horas = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", segundos);
    String minutes_string = String.format("%02d", minutos);
    String hours_string = String.format("%02d", horas);

    JLabel etiquetaTiempo;

    void start() { // Incia el contador
        timer.start();
    }

    void stop() { // Detiene el contador
        timer.stop();
    }

    void reset() {  // Reinicia el contador
        timer.stop();
        elapsedTime = 0;
        segundos = 0;
        minutos = 0;
        horas = 0;
        seconds_string = String.format("%02d", segundos);
        minutes_string = String.format("%02d", minutos);
        hours_string = String.format("%02d", horas);
        etiquetaTiempo.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }

    // Funcionalidad del contador
    Timer timer = new Timer(1000, e -> {
        elapsedTime = elapsedTime + 1000;
        horas = (elapsedTime/3600000);
        minutos = (elapsedTime/60000) % 60;
        segundos = (elapsedTime/1000) % 60;
        seconds_string = String.format("%02d", segundos);
        minutes_string = String.format("%02d", minutos);
        hours_string = String.format("%02d", horas);
        etiquetaTiempo.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    });

}