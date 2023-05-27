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
    JButton botonDetener;  //*

    // Tiempo
    Tiempo tiempo = new Tiempo();
    Tarea tarea = new Tarea();

    TimeTracker() {
        // Frame ------------------------------------------------------------------------
        super("TimeTracker");
        setSize(900, 600);
        setIconImage(new ImageIcon("src/Iconos/clock.png").getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(60,80));

        // Panel Lateral ----------------------------------------------------------------
        panelLateral = new JPanel();
        panelLateral.setPreferredSize(new Dimension(300, getHeight()));
        panelLateral.setBackground(new Color(169, 180, 194));
        panelLateral.setLayout(new BorderLayout(10, 10));
        add(panelLateral, BorderLayout.WEST);

        panelLateralNorte = new JPanel();
        panelLateralNorte.setPreferredSize(new Dimension(panelLateral.getWidth(), 50));
        panelLateralNorte.setBackground(new Color(183,212,123));
        panelLateralNorte.setLayout(new BorderLayout(5, 5));
        panelLateral.add(panelLateralNorte, BorderLayout.NORTH);

        etiquetaTituloPL = new JLabel("LISTA DE TAREAS");
        etiquetaTituloPL.setFont(new Font("Segoe UI", Font.BOLD, 25));
        etiquetaTituloPL.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaTituloPL.setHorizontalAlignment(SwingConstants.CENTER);
        panelLateralNorte.add(etiquetaTituloPL, BorderLayout.CENTER);

        panelLateralSur = new JPanel();
        panelLateralSur.setPreferredSize(new Dimension(panelLateral.getWidth(), 70));
        panelLateralSur.setBackground(new Color(183,212,123));
        panelLateral.add(panelLateralSur, BorderLayout.SOUTH);

        botonDescargar = new JButton("Descargar informe");
        botonDescargar.setFocusable(false);
        botonDescargar.addActionListener(this);
        botonDescargar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        botonDescargar.setIcon(new ImageIcon("src/Iconos/IconoDescargar.png"));
        panelLateralSur.add(botonDescargar);

        panelLateralCentro = new JPanel();
        panelLateralCentro.setPreferredSize(new Dimension(panelLateral.getWidth() - 10, panelLateral.getHeight() - 110));
        panelLateralCentro.setBackground(new Color(123, 111, 255));
        panelLateral.add(panelLateralCentro, BorderLayout.CENTER);

        // Panel Principal --------------------------------------------------------------
        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(584, getHeight()));
        panelPrincipal.setBackground(new Color(123, 11, 54));
        panelPrincipal.setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.EAST);

        panelPrincipalNorte = new JPanel();
        panelPrincipalNorte.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 150));
        panelPrincipalNorte.setBackground(new Color(124, 45, 187));
        panelPrincipalNorte.setLayout(new GridLayout(2, 1, 7, 10));
        panelPrincipal.add(panelPrincipalNorte, BorderLayout.NORTH);

        panelPNorte1 = new JPanel();
        panelPNorte1.setBackground(new Color(0, 145, 234));
        panelPrincipalNorte.add(panelPNorte1);

        etiquetaTituloPP = new JLabel("Time Tracker");
        etiquetaTituloPP.setFont(new Font("Segoe UI", Font.BOLD, 50));
        etiquetaTituloPP.setIcon(new ImageIcon("src/Iconos/wall-clock.png"));
        etiquetaTituloPP.setIconTextGap(15);
        panelPNorte1.add(etiquetaTituloPP, BorderLayout.WEST);

        panelPNorte2 = new JPanel();
        panelPNorte2.setBackground(new Color(123, 145, 0));
        panelPrincipalNorte.add(panelPNorte2);

        areaNombreTarea = new JTextField("Ingresa el nombre de la tarea . . .");
        areaNombreTarea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaNombreTarea.setPreferredSize(new Dimension(550, 50));
        panelPNorte2.add(areaNombreTarea);

        panelPrincipalCentro = new JPanel();
        panelPrincipalCentro.setBackground(new Color(123, 231, 122));
        panelPrincipalCentro.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 300));
        panelPrincipalCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 0, (panelPrincipalCentro.getHeight()/2) + 15));
        panelPrincipal.add(panelPrincipalCentro, BorderLayout.CENTER);

        // Etiqueta tiempo
        tiempo.etiquetaTiempo = new JLabel();
        tiempo.etiquetaTiempo.setText(tiempo.hours_string + ":" + tiempo.minutes_string + ":" + tiempo.seconds_string);
        tiempo.etiquetaTiempo.setFont(new Font("Segoe UI",Font.PLAIN,130));
        panelPrincipalCentro.add(tiempo.etiquetaTiempo);

        panelPrincipalSur = new JPanel();
        panelPrincipalSur.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 180));
        panelPrincipalSur.setBackground(new Color(12, 21, 132));
        panelPrincipalSur.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));
        panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);

        botonIniciarPausar = new JButton();
        botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/play-button.png"));
        botonIniciarPausar.setBackground(null);
        botonIniciarPausar.setBorderPainted(false);
        botonIniciarPausar.setContentAreaFilled(false);
        botonIniciarPausar.addActionListener(this);
        botonIniciarPausar.setPreferredSize(new Dimension(130,130));
        botonIniciarPausar.setFocusable(false);
        panelPrincipalSur.add(botonIniciarPausar);

        botonDetener = new JButton();
        botonDetener.setIcon(new ImageIcon("src/Iconos/stop-button (1).png"));
        botonDetener.setOpaque(true);
        botonDetener.setBackground(null);
        botonDetener.setBorderPainted(false);
        botonDetener.setContentAreaFilled(false);
        botonDetener.addActionListener(this);
        botonDetener.setPreferredSize(new Dimension(130,130));
        botonDetener.setFocusable(false);
        botonDetener.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                botonDetener.setIcon(new ImageIcon("src/Iconos/stop-button.png"));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                botonDetener.setIcon(new ImageIcon("src/Iconos/stop-button (1).png"));
            }
        });
        panelPrincipalSur.add(botonDetener);

        setVisible(true);
    }


    public static void main(String[] args) {
        new TimeTracker();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botonIniciarPausar) {
            if (!tiempo.started) {
                tiempo.started = true;
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/video-pause-button (2).png"));

                tarea.fechaIncio = new Date();

                areaNombreTarea.setEnabled(false);

                tiempo.start();


            } else {
                tiempo.started = false;
                areaNombreTarea.setEnabled(true);
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/play-button.png"));
                tiempo.stop();
            }

        }

        if (e.getSource() == botonDetener) {
            tiempo.started = false;

            tarea.fechaFin = new Date();

            botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/play-button.png"));

            areaNombreTarea.setText(null);
            tiempo.reset();

        }
    }


}
