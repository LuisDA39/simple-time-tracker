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
    JScrollPane scrollPane;
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
    JButton botonIniciarPausar;
    JButton botonGuardar;

    // Tiempo, tareas
    Tiempo tiempo = new Tiempo();
    Tarea tarea = new Tarea();
    Informe informe = new Informe();
    Date inicio;
    Date fin;

    TimeTracker() {
        // Configuración del frame
        super("TimeTracker");
        setSize(900, 600);
        setIconImage(new ImageIcon("src/Iconos/logoApp.png").getImage()); // Asigna un icono a la aplicación
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null); // Para que aparezca en el centro de la pantalla
        setLayout(new BorderLayout(60,80));
        setBackground(null);

        // Panel Lateral ----------------------------------------------------------------
        panelLateral = new JPanel();
        panelLateral.setPreferredSize(new Dimension(300, getHeight()));
        panelLateral.setBackground(new Color(230, 230, 230));
        panelLateral.setLayout(new BorderLayout(10, 10));
        add(panelLateral, BorderLayout.WEST);

        // Configuración del panel que va al norte del PanelLateral y donde se encuentra etiquetaTituloPL
        panelLateralNorte = new JPanel();
        panelLateralNorte.setPreferredSize(new Dimension(panelLateral.getWidth(), 50));
        panelLateralNorte.setBackground(null);
        panelLateralNorte.setLayout(new BorderLayout(5, 5));
        panelLateral.add(panelLateralNorte, BorderLayout.NORTH);

        // Configuración de la etiqueta que está hasta arriba del panelLateral y dentro del PanelLateralNorte
        etiquetaTituloPL = new JLabel("LISTA DE TAREAS");
        etiquetaTituloPL.setFont(new Font("Segoe UI", Font.BOLD, 25));
        etiquetaTituloPL.setVerticalAlignment(SwingConstants.CENTER);
        etiquetaTituloPL.setHorizontalAlignment(SwingConstants.CENTER);
        panelLateralNorte.add(etiquetaTituloPL, BorderLayout.CENTER);

        // Configuración del panelLateralCentro (el que va dentro del scrollPane)
        panelLateralCentro = new JPanel();
        panelLateralCentro.setLayout(new BoxLayout(panelLateralCentro, BoxLayout.Y_AXIS));
        panelLateralCentro.setBackground(new Color(230, 230, 230));
        panelLateral.add(panelLateralCentro, BorderLayout.CENTER);

        // Configuración del scrollPane (para agregar una scrollBar a la lista de tareas)
        scrollPane = new JScrollPane(panelLateralCentro);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Quitar scrollbar horizontal
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); // Scrollbar aparece cuando es necesario
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Elimina el borde del scrollPane
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(12, 10)); // Tamaño del scrollbar
        scrollPane.getVerticalScrollBar().setUnitIncrement(10); // Aumenta la "velocidad" con la que sube o baja
        panelLateral.add(scrollPane);

        // Configuración del panelLateralSur, donde va el botónDescargar para descargar el informe
        panelLateralSur = new JPanel();
        panelLateralSur.setPreferredSize(new Dimension(panelLateral.getWidth(), 70));
        panelLateralSur.setBackground(null);
        panelLateral.add(panelLateralSur, BorderLayout.SOUTH);

        // Configuración del botónDescargar, para descargar el informe
        botonDescargar = new JButton("Descargar informe");
        botonDescargar.setFocusable(false); // para evitar que salga un marco alrededor
        botonDescargar.addActionListener(this);
        botonDescargar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        botonDescargar.setIcon(new ImageIcon("src/Iconos/IconoDescargar.png")); // Asigna un icono al botón
        botonDescargar.setEnabled(false); // botón desactivado inicialmente
        panelLateralSur.add(botonDescargar);

        // Panel Principal --------------------------------------------------------------
        panelPrincipal = new JPanel();
        panelPrincipal.setPreferredSize(new Dimension(584, getHeight()));
        panelPrincipal.setBackground(null);
        panelPrincipal.setLayout(new BorderLayout());
        add(panelPrincipal, BorderLayout.EAST);

        // Configuración del panelPrincipalNorte, donde van el nombre de la aplicación ("Time Tracker") y el campo de texto
        panelPrincipalNorte = new JPanel();
        panelPrincipalNorte.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 150));
        panelPrincipalNorte.setBackground(null);
        panelPrincipalNorte.setLayout(new GridLayout(2, 1, 7, 10)); // GridLayout con espacios
        panelPrincipal.add(panelPrincipalNorte, BorderLayout.NORTH);

        // Configuración del panelPNorte1, que va dentro del panelPrincipalNorte y contiene el nombre de la aplicación ("Time Tracker")
        panelPNorte1 = new JPanel();
        panelPNorte1.setBackground(null);
        panelPrincipalNorte.add(panelPNorte1);

        // Configuración de la etiqueta que muestra el nombre de la aplicación ("Time Tracker")
        etiquetaTituloPP = new JLabel("Time Tracker");
        etiquetaTituloPP.setFont(new Font("Segoe UI", Font.BOLD, 50));
        etiquetaTituloPP.setIcon(new ImageIcon("src/Iconos/IconoTimeTracker.png"));
        etiquetaTituloPP.setIconTextGap(15); // Aumenta el espacio entre el icono y el texto
        panelPNorte1.add(etiquetaTituloPP, BorderLayout.WEST);

        // Configuración del panelPNorte2, que va dentro del panelPrincipalNorte y contiene el campo de texto
        panelPNorte2 = new JPanel();
        panelPNorte2.setBackground(null);
        panelPrincipalNorte.add(panelPNorte2);

        // Configuración del campo de texto, para ingresar el nombre de las tareas
        areaNombreTarea = new JTextField(" Ingresa el nombre de la tarea . . .");
        areaNombreTarea.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        areaNombreTarea.setForeground(new Color(69, 69, 69)); // Color del texto
        areaNombreTarea.setPreferredSize(new Dimension(550, 50));
        areaNombreTarea.addKeyListener(new KeyAdapter() { // Para agregar funcionalidad cuando se recibe una acción del teclado
            @Override
            public void keyTyped(KeyEvent e) { // El texto por defecto se borra si se escribe algo
                if (areaNombreTarea.getText().equals(" Ingresa el nombre de la tarea . . ."))
                    areaNombreTarea.setText("");

            }

            @Override
            public void keyPressed(KeyEvent e) { // El texto por defecto se borra cuando se presiona una tecla
                if (areaNombreTarea.getText().equals(" Ingresa el nombre de la tarea . . ."))
                    areaNombreTarea.setText("");
            }
        });
        panelPNorte2.add(areaNombreTarea);

        // Configuración del panelPrincipalCentro, donde va contenido la etiqueta con el contador
        panelPrincipalCentro = new JPanel();
        panelPrincipalCentro.setBackground(null);
        panelPrincipalCentro.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 300));
        panelPrincipalCentro.setLayout(new BorderLayout());
        panelPrincipal.add(panelPrincipalCentro, BorderLayout.CENTER);

        // Configuración de la etiquetaTiempo, el contador en sí
        tiempo.etiquetaTiempo = new JLabel();
        tiempo.etiquetaTiempo.setText(tiempo.hours_string + ":" + tiempo.minutes_string + ":" + tiempo.seconds_string);
        tiempo.etiquetaTiempo.setFont(new Font("Segoe UI", Font.PLAIN, 120));
        tiempo.etiquetaTiempo.setVerticalAlignment(SwingConstants.CENTER);
        tiempo.etiquetaTiempo.setHorizontalAlignment(SwingConstants.CENTER);
        panelPrincipalCentro.add(tiempo.etiquetaTiempo, BorderLayout.CENTER);

        // Configuración del panelPrincipalSur, donde se encuentran los botones para Iniciar/Pausar y guardar
        panelPrincipalSur = new JPanel();
        panelPrincipalSur.setPreferredSize(new Dimension(panelPrincipal.getWidth(), 180));
        panelPrincipalSur.setBackground(null);
        panelPrincipalSur.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 20));
        panelPrincipal.add(panelPrincipalSur, BorderLayout.SOUTH);

        // Configuración del botónIniciarPausar, que inicia y pausa el contador
        botonIniciarPausar = new JButton();
        botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png")); // Asigna un icono al botón
        botonIniciarPausar.setBackground(null);
        botonIniciarPausar.setBorderPainted(false); // Esconde el borde del botón
        botonIniciarPausar.setContentAreaFilled(false); // Al presionarlo, no parpadea
        botonIniciarPausar.addActionListener(this);
        botonIniciarPausar.setPreferredSize(new Dimension(90,90));
        botonIniciarPausar.setFocusable(false); // Esconde un marco azul que sale al presionarlo
        botonIniciarPausar.addMouseListener(new MouseAdapter() { // Agrega funcionalidad a la hora de usar el mouse
            @Override
            public void mousePressed(MouseEvent e) { // Si se presiona el botón, se cambia de icono play/pausa
                if (tiempo.started) { // Si el contador está iniciado, se asignará el icono de pausa
                    botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/pausePresionado.png"));
                } else {
                    botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playPresionado.png"));
                }
            }
        });
        panelPrincipalSur.add(botonIniciarPausar);

        // Configuración del botón guardar
        botonGuardar = new JButton();
        botonGuardar.setIcon(new ImageIcon("src/Iconos/guardarNormal.png"));
        botonGuardar.setBackground(null);
        botonGuardar.setEnabled(false);
        botonGuardar.setBorderPainted(false); // Esconde el borde del botón
        botonGuardar.setContentAreaFilled(false); // Al presionarlo, no parpadea
        botonGuardar.addActionListener(this);
        botonGuardar.setPreferredSize(new Dimension(90,90));
        botonGuardar.setFocusable(false); // Esconde un marco azul que sale al presionarlo
        botonGuardar.addMouseListener(new MouseAdapter() { // Agrega funcionalidad al momento de usar el mouse
            @Override
            public void mousePressed(MouseEvent e) { // El icono cambia de tamaño dependiendo si está presionado o no
                if (botonGuardar.isEnabled()) { // Si se presiona el mouse y está activado, se asigna un icono presionado
                    botonGuardar.setIcon(new ImageIcon("src/Iconos/guardarPresionado.png"));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) { // Si se deja de presionar, se asigna el icono normal
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
        if (e.getSource() == botonIniciarPausar) { // Al presionar el botónIniciarPausar ...
            if (!tiempo.started) {
                botonGuardar.setEnabled(true); // Activa el botón de guardar
                tiempo.started = true;
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/pause.png")); // Cambia el icono por el de pausa

                inicio = new Date(); // Guarda una fecha de inicio

                tiempo.start(); // Inicia el contador

            } else {
                tiempo.started = false;
                botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png")); // Cambia el icono por el de play
                tiempo.stop(); // Detiene el contador
            }

        }

        if (e.getSource() == botonGuardar) { // Al presionar el botónGuardar ...
            tiempo.started = false;
            botonGuardar.setEnabled(false); // Se desactiva el botón

            fin = new Date(); // Guarda una fecha de fin

            String tiempoTotal = tiempo.hours_string + ":" + tiempo.minutes_string + ":" + tiempo.seconds_string;

            // Si no se asignó nombre a la tarea o sigue estando el texto por defecto, la tarea se guarda como "sin nombre"
            if (areaNombreTarea.getText().equals("") || areaNombreTarea.getText().equals(" Ingresa el nombre de la tarea . . .")) {
                tarea = new Tarea("Tarea sin nombre", tiempoTotal, inicio, fin);

            } else {
                tarea = new Tarea(areaNombreTarea.getText(), tiempoTotal, inicio, fin);
            }

            informe.agregar(tarea); // agrega la tarea al arraylist de tareas
            panelLateralCentro.add(tarea.crearPanel());  // Agrega un panel con la info. de la tarea al panelLateralCentro
            panelLateralCentro.add(Box.createVerticalStrut(11)); // Agrega un espacio entre paneles de tareas

            botonIniciarPausar.setIcon(new ImageIcon("src/Iconos/playNormal.png")); // Cambia el icono por el de play

            areaNombreTarea.setText(" Ingresa el nombre de la tarea . . ."); // Vuelve a colocar
            botonDescargar.setEnabled(true); // Activa el botónDescargar, para evitar que se descarguen reportes vacíos

            tiempo.reset(); // Reinicia el contador
        }

        if (e.getSource() == botonDescargar) { // Al presionar el botónDescargar ...
            informe.descargar(); // Se descarga el informe
        }
    }
}
