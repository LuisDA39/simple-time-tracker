import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class TimeTracker extends JFrame implements ActionListener {
    // Componentes: Panel Lateral
    JPanel panelLateral;
    JPanel panelLateralNorte;
    JLabel etiquetaTituloPL;
    JPanel panelLateralCentro;
    JPanel panelLateralSur;
    JButton botonDescargar;

    // Componentes: Panel Principal
    JPanel panelPrincipal;
    JPanel panelPrincipalNorte;
    JPanel panelPNorte1;
    JLabel etiquetaTituloPP;
    JPanel panelPNorte2;
    JTextField areaNombreTarea;
    JPanel panelPrincipalCentro;
    JPanel panelPrincipalSur;
    JButton botonIniciarPausar; // *
    JButton botonGuardar;  //*

    // Tiempo
    Tiempo tiempo = new Tiempo();
    Tarea tarea = new Tarea();
    Informe informe = new Informe();
    Date inicio;
    Date fin;

    TimeTracker() {
        // Frame ------------------------------------------------------------------------
        super("TimeTracker");
        setSize(900, 600);
        setIconImage(new ImageIcon("src/Iconos/logoApp.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(60,80));

        // Panel Lateral ----------------------------------------------------------------
        panelLateral = new JPanel();
        panelLateral.setPreferredSize(new Dimension(300, getHeight()));
        panelLateral.setBackground(null);
        panelLateral.setLayout(new BorderLayout(10, 10));
        add(panelLateral, BorderLayout.WEST);

        panelLateralNorte = new JPanel();
        panelLateralNorte.setPreferredSize(new Dimension(panelLateral.getWidth(), 50));
        panelLateralNorte.setBackground(null);
        panelLateralNorte.setLayout(new BorderLayout(5, 5));
        panelLateral.add(panelLateralNorte, BorderLayout.NORTH);

        etiquetaTituloPL = new JLabel("LISTA DE TAREAS");
        etiquetaTituloPL.setFont(new Font("Segoe UI", Font.BOLD, 25));
        etiquetaTituloPL.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaTituloPL.setHorizontalAlignment(SwingConstants.CENTER);
        panelLateralNorte.add(etiquetaTituloPL, BorderLayout.CENTER);

        panelLateralSur = new JPanel();
        panelLateralSur.setPreferredSize(new Dimension(panelLateral.getWidth(), 70));
        panelLateralSur.setBackground(null);
        panelLateral.add(panelLateralSur, BorderLayout.SOUTH);

        botonDescargar = new JButton("Descargar informe");
        botonDescargar.setFocusable(false);
        botonDescargar.addActionListener(this);
        botonDescargar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        botonDescargar.setIcon(new ImageIcon("src/Iconos/IconoDescargar.png"));
        panelLateralSur.add(botonDescargar);

        panelLateralCentro = new JPanel();
        panelLateralCentro.setPreferredSize(new Dimension(panelLateral.getWidth() - 10, panelLateral.getHeight() - 110));
        panelLateralCentro.setBackground(null);
        panelLateral.add(panelLateralCentro, BorderLayout.CENTER);

        // Panel Principal --------------------------------------------------------------
        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(600, getHeight())); //584, overlap
        panelPrincipal.setBackground(null);
        panelPrincipal.setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.EAST);

        panelPrincipalNorte = new JPanel();
        panelPrincipalNorte.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 150));
        panelPrincipalNorte.setBackground(null);
        panelPrincipalNorte.setLayout(new GridLayout(2, 1, 7, 10));
        panelPrincipal.add(panelPrincipalNorte, BorderLayout.NORTH);

        panelPNorte1 = new JPanel();
        panelPNorte1.setBackground(null);
        panelPrincipalNorte.add(panelPNorte1);

        etiquetaTituloPP = new JLabel("Time Tracker");
        etiquetaTituloPP.setFont(new Font("Segoe UI", Font.BOLD, 50));
        etiquetaTituloPP.setIcon(new ImageIcon("src/Iconos/IconoTimeTracker.png"));
        etiquetaTituloPP.setIconTextGap(15);
        panelPNorte1.add(etiquetaTituloPP, BorderLayout.WEST);

        panelPNorte2 = new JPanel();
        panelPNorte2.setBackground(null);
        panelPrincipalNorte.add(panelPNorte2);

        areaNombreTarea = new JTextField(" Ingresa el nombre de la tarea . . .");
        areaNombreTarea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaNombreTarea.setPreferredSize(new Dimension(550, 50));
        panelPNorte2.add(areaNombreTarea);

        panelPrincipalCentro = new JPanel();
        panelPrincipalCentro.setBackground(null);
        panelPrincipalCentro.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 300));
        panelPrincipalCentro.setLayout(new BorderLayout());
        panelPrincipal.add(panelPrincipalCentro, BorderLayout.CENTER);

        // Etiqueta tiempo
        tiempo.etiquetaTiempo = new JLabel();
        tiempo.etiquetaTiempo.setText(tiempo.hours_string + ":" + tiempo.minutes_string + ":" + tiempo.seconds_string);
        tiempo.etiquetaTiempo.setFont(new Font("Segoe UI", Font.PLAIN, 120));
        tiempo.etiquetaTiempo.setVerticalAlignment(SwingConstants.CENTER);
        tiempo.etiquetaTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipalCentro.add(tiempo.etiquetaTiempo, BorderLayout.CENTER);

        panelPrincipalSur = new JPanel();
        panelPrincipalSur.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 180));
        panelPrincipalSur.setBackground(null);
        panelPrincipalSur.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
        panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);

        botonIniciarPausar = new JButton();
        botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png"));
        botonIniciarPausar.setBackground(null);
        botonIniciarPausar.setBorderPainted(false);
        botonIniciarPausar.setContentAreaFilled(false);
        botonIniciarPausar.addActionListener(this);
        botonIniciarPausar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (tiempo.started) {
                    botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/pausePresionado.png"));
                } else {
                    botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playPresionado.png"));
                }
            }
        });
        botonIniciarPausar.setPreferredSize(new Dimension(130,130));
        botonIniciarPausar.setFocusable(false);
        panelPrincipalSur.add(botonIniciarPausar);

        botonGuardar = new JButton();
        botonGuardar.setIcon(new ImageIcon("src/Iconos/guardarNormal.png"));
        botonGuardar.setOpaque(true);
        botonGuardar.setBackground(null);
        botonGuardar.setEnabled(false);
        botonGuardar.setBorderPainted(false);
        botonGuardar.setContentAreaFilled(false);
        botonGuardar.addActionListener(this);
        botonGuardar.setPreferredSize(new Dimension(130,130));
        botonGuardar.setFocusable(false);
        botonGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (botonGuardar.isEnabled()) {
                    botonGuardar.setIcon(new ImageIcon("src/Iconos/guardarPresionado.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonGuardar.setIcon(new ImageIcon("src/Iconos/guardarNormal.png"));
            }
        });
        panelPrincipalSur.add(botonGuardar);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TimeTracker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciarPausar) {
            if (!tiempo.started) {
                botonGuardar.setEnabled(true);
                tiempo.started = true;
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/pause.png"));

                inicio = new Date();

                tiempo.start();

            } else {
                tiempo.started = false;
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png"));
                tiempo.stop();
            }

        }

        if (e.getSource() == botonGuardar) {
            tiempo.started = false;
            botonGuardar.setEnabled(false);

            fin = new Date();

            String tiempoTotal = tiempo.hours_string + ":" + tiempo.minutes_string + ":" + tiempo.seconds_string;

            if (areaNombreTarea.getText().equals("") || areaNombreTarea.getText().equals(" Ingresa el nombre de la tarea . . .")) {
                tarea = new Tarea("Sin nombre", tiempoTotal, inicio, fin);

            } else {
                tarea = new Tarea(areaNombreTarea.getText(), tiempoTotal, inicio, fin);
            }

            informe.agregar(tarea);
            panelLateralCentro.add(tarea.crearPanel());

            botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png"));

            areaNombreTarea.setText(null);
            tiempo.reset();
        }

        if (e.getSource() == botonDescargar) {
            informe.descargar();
        }
    }
}
