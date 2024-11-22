import javax.swing.*;
import java.awt.*;

public class ResultadosFrame {

    private static JFrame ventanaResultados;

    // Mostrar solo el ganador y su tiempo
    public static void mostrarResultados(String ganador, long tiempo) {
        // Crear la ventana si no existe
        if (ventanaResultados == null) {
            ventanaResultados = new JFrame("Resultados de la Carrera");
            ventanaResultados.setSize(500, 500);
            ventanaResultados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaResultados.setLocationRelativeTo(null);
            ventanaResultados.setLayout(new BorderLayout());
            ventanaResultados.setUndecorated(true);
        }

        ImageIcon Botonapso2 = redimensionarImagen("images/exit_button.png", 0.2);

        JLabel Salir2 = new JLabel(Botonapso2);

        posicionarImagen(Salir2, 250, 350);

        Main.agregarEfectoHover(Salir2);
        Main.agregarEfectoClic(Salir2);

        Salir2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Thread.sleep(100);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        // Limpiar el contenido previo de la ventana
        ventanaResultados.getContentPane().removeAll();

        // Añadir el fondo con la imagen redimensionada
        String agregarfondo = "images/Marco_Winner.png";
        ImageIcon imagenRedimensionada = redimensionarImagen(agregarfondo, 1.8);  // Redimensionar a un 180%
        addBack(imagenRedimensionada);  // Añadir el fondo

        // Crear un panel para los resultados
        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(null); // Usamos null para el layout y poder usar setBounds
        panelResultados.setOpaque(false); // Asegura que el panel no tape el fondo

        // Mostrar el nombre del ganador y el tiempo
        JLabel etiqueta = new JLabel("¡" + ganador + " ha ganado!");
        etiqueta.setFont(new Font("Arial", Font.BOLD, 24));
        etiqueta.setForeground(Color.black);  // Color blanco para que se vea bien sobre el fondo oscuro
        etiqueta.setBounds(75, 100, 400, 50); // Centrado en el panel (ajusta valores según sea necesario)
        panelResultados.add(etiqueta);

        JLabel tiempoLabel = new JLabel("Tiempo: " + tiempo + " ms");
        tiempoLabel.setFont(new Font("Arial", Font.PLAIN, 22));
        tiempoLabel.setForeground(Color.black);  // Color blanco
        tiempoLabel.setBounds(160, 160, 400, 50); // Centrado en el panel (ajusta valores según sea necesario)
        panelResultados.add(tiempoLabel);



        // Añadir el panel de resultados a la ventana
        ventanaResultados.add(panelResultados, BorderLayout.CENTER);

        // Asegurarse de que los componentes se vuelvan a calcular y dibujar
        ventanaResultados.revalidate();
        ventanaResultados.repaint();

        ventanaResultados.add(Salir2);
        // Hacer visible la ventana de resultados
        ventanaResultados.setVisible(true);
    }



    // Método para agregar el fondo con la imagen redimensionada
    private static void addBack(ImageIcon imagen) {
        // Crear un JLabel con la imagen redimensionada
        JLabel fondo = new JLabel(imagen);
        fondo.setBounds(0, 0, 500, 500);  // Establecer tamaño adecuado
        JPanel panelFondo = new JPanel(null);
        panelFondo.setOpaque(false); // Asegura que no opaque los componentes que están encima
        panelFondo.add(fondo, BorderLayout.CENTER); // Añadir fondo al panel

        // Establecer el panelFondo como la capa base
        ventanaResultados.setContentPane(panelFondo);
        ventanaResultados.getContentPane().setLayout(new BorderLayout()); // Layout con BorderLayout
    }

    // Método para redimensionar la imagen
    public static ImageIcon redimensionarImagen(String rutaImagen, double porcentaje) {
        ImageIcon imagenOriginal = new ImageIcon(rutaImagen);

        int anchoOriginal = imagenOriginal.getIconWidth();
        int altoOriginal = imagenOriginal.getIconHeight();

        int nuevoAncho = (int) (anchoOriginal * porcentaje);
        int nuevoAlto = (int) (altoOriginal * porcentaje);

        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(
                nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH
        );

        return new ImageIcon(imagenRedimensionada);
    }
    public static void posicionarImagen(JLabel imagen, int x, int y) {
        imagen.setBounds(x, y, imagen.getIcon().getIconWidth(), imagen.getIcon().getIconHeight());
    }
}
