import javax.swing.*;
import java.awt.*;

public class ResultadosFrame {

    private static JFrame ventanaResultados;

    public static void mostrarResultados(String ganador, long tiempo) {
        if (ventanaResultados == null) {
            ventanaResultados = new JFrame("Resultados de la Carrera");
            ventanaResultados.setSize(500, 500);
            ventanaResultados.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ventanaResultados.setLocationRelativeTo(null);
            ventanaResultados.setLayout(new BorderLayout());
            ventanaResultados.setUndecorated(true);
        }

        ventanaResultados.getContentPane().removeAll();

        String agregarfondo = "images/Marco_Winner.png";
        ImageIcon imagenRedimensionada = redimensionarImagen(agregarfondo, 1.8);  // Redimensionar a un 180%
        addBack(imagenRedimensionada);


        JPanel panelResultados = new JPanel();
        panelResultados.setLayout(null);
        panelResultados.setOpaque(false);

        JLabel etiqueta = new JLabel("¡" + ganador + "ha ganado!");
        etiqueta.setFont(new Font("Monospace", Font.BOLD, 26));
        etiqueta.setForeground(Color.black);
        etiqueta.setBounds(70, 100, 400, 50);
        panelResultados.add(etiqueta);

        JLabel tiempoLabel = new JLabel("Tiempo: " + tiempo + " ms");
        tiempoLabel.setFont(new Font("Monospace", Font.PLAIN, 24));
        tiempoLabel.setForeground(Color.black);  // Color blanco
        tiempoLabel.setBounds(180, 160, 400, 50);
        panelResultados.add(tiempoLabel);



        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> ventanaResultados.setVisible(false));
        botonCerrar.setBounds(200, 230, 100, 40); // Posición centrada
        panelResultados.add(botonCerrar);

        ventanaResultados.add(panelResultados, BorderLayout.CENTER);

        ventanaResultados.revalidate();
        ventanaResultados.repaint();

        ventanaResultados.setVisible(true);
    }

    // Método para agregar el fondo con la imagen redimensionada
    private static void addBack(ImageIcon imagen) {

        JLabel fondo = new JLabel(imagen);
        fondo.setBounds(0, 0, 500, 500);
        JPanel panelFondo = new JPanel(null);
        panelFondo.setOpaque(false);
        panelFondo.add(fondo, BorderLayout.CENTER);

        ventanaResultados.setContentPane(panelFondo);
        ventanaResultados.getContentPane().setLayout(new BorderLayout());
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


}
