
                import javax.swing.*;
                import java.awt.*;
                import java.awt.event.ActionEvent;
                import java.awt.event.ActionListener;

        public class PanelScrollExample extends JFrame {

            private JPanel panelPrincipal;
            private JScrollPane scrollPane;
            private int contadorPaneles = 0;

            PanelScrollExample() {
                setSize(400, 400);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Ejemplo de Panel con Scroll");

                // Panel principal
                panelPrincipal = new JPanel();
                panelPrincipal.setBackground(Color.yellow);
                panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));

                // Botón para agregar paneles
                JButton botonAgregar = new JButton("Agregar Panel");
                botonAgregar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        agregarPanel();
                    }
                });

                // Panel de botón
                JPanel panelBoton = new JPanel();
                panelBoton.add(botonAgregar);

                // Panel contenedor
                JPanel panelContenedor = new JPanel();
                panelContenedor.setBackground(Color.blue);
                panelContenedor.setLayout(new BorderLayout());
                panelContenedor.add(panelPrincipal, BorderLayout.CENTER);

                // JScrollPane
                scrollPane = new JScrollPane(panelContenedor);
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

                // Panel principal del JFrame
                JPanel panelPrincipalFrame = new JPanel();
                panelPrincipalFrame.setLayout(new BorderLayout());
                panelPrincipalFrame.add(panelBoton, BorderLayout.NORTH);
                panelPrincipalFrame.add(scrollPane, BorderLayout.CENTER);

                add(panelPrincipalFrame);
                setVisible(true);
            }

            private void agregarPanel() {
                JPanel nuevoPanel = new JPanel();
                nuevoPanel.setBackground(Color.lightGray);
                nuevoPanel.setPreferredSize(new Dimension(300, 50));

                JLabel etiqueta = new JLabel("Panel " + contadorPaneles);
                etiqueta.setFont(new Font("Arial", Font.BOLD, 16));
                nuevoPanel.add(etiqueta);

                panelPrincipal.add(nuevoPanel);
                panelPrincipal.revalidate();
                contadorPaneles++;
            }

            public static void main(String[] args) {
                new PanelScrollExample();
            }
        }
