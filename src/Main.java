import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Window");

        ventana.setLayout(null);

        ventana.setLocation(300, 150);
        ventana.setSize(1000, 600);
        ventana.setResizable(false);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the window closes properly
        ventana.setUndecorated(true);

        // Resize the images
        ImageIcon setcoche1 = redimensionarImagen("images/car1.png", 0.22);
        ImageIcon setcoche2 = redimensionarImagen("images/car2.png", 0.2);
        ImageIcon setcoche3 = redimensionarImagen("images/car3.png", 0.2);
        ImageIcon setcoche4 = redimensionarImagen("images/car4.png", 0.2);

        ImageIcon Botonapso = redimensionarImagen("images/Boton_Empezar.png", 0.3);

        ImageIcon Botonapso2 = redimensionarImagen("images/exit_button.png", 0.4);

        ImageIcon setTittle = redimensionarImagen("images/Tittle.png", 0.8);

        JLabel coche1 = new JLabel(setcoche1);
        JLabel coche2 = new JLabel(setcoche2);
        JLabel coche3 = new JLabel(setcoche3);
        JLabel coche4 = new JLabel(setcoche4);

        JLabel Titlle = new JLabel(setTittle);

        JLabel Boton = new JLabel(Botonapso);

        JLabel Salir = new JLabel(Botonapso2);

        // Position the images
        posicionarImagen(coche1, 50, 250);
        posicionarImagen(coche2, 50, 320);
        posicionarImagen(coche3, 50, 390);
        posicionarImagen(coche4, 50, 470);

        posicionarImagen(Titlle, 300, 20);

        posicionarImagen(Boton, 100, 50);

        posicionarImagen(Salir, 750, 30);

        ventana.add(coche1);
        ventana.add(coche2);
        ventana.add(coche3);
        ventana.add(coche4);
        ventana.add(Titlle);

        ventana.add(Boton);

        ventana.add(Salir);

        Salir.addMouseListener(new java.awt.event.MouseAdapter() {
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

        agregarEfectoHover(Salir, "images/exit_button.png", 0.4 , 0.43);
        agregarEfectoClic(Salir, "images/exit_button.png", 0.4 , 0.43);

        agregarEfectoHover(Boton, "images/Boton_Empezar.png", 0.3 , 0.33);
        agregarEfectoClic(Boton, "images/Boton_Empezar.png", 0.3 , 0.35);

        agregarEfectoHover(Titlle, "images/Tittle.png", 0.8 , 0.85);
        addBackground(ventana, "images/FONDO.png");

        agregarEfectoHover(coche1, "images/car1.png", 0.22 , 0.23);
        agregarEfectoHover(coche2, "images/car2.png", 0.2 , 0.22);
        agregarEfectoHover(coche3, "images/car3.png", 0.2 , 0.22);
        agregarEfectoHover(coche4, "images/car4.png", 0.2 , 0.22);

        Boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.out.println("¡Carrera iniciada!");
                // Create threads for each car
                Thread car1Thread = new Thread(new Runcar(coche1, 50, 250));
                coche1.setText("Coche 1");
                Thread car2Thread = new Thread(new Runcar(coche2, 50, 320));
                coche2.setText("Coche 2");
                Thread car3Thread = new Thread(new Runcar(coche3, 50, 390));
                coche3.setText("Coche 3");
                Thread car4Thread = new Thread(new Runcar(coche4, 50, 470));
                coche4.setText("Coche 4");

                // Start the threads
                car1Thread.start();
                car2Thread.start();
                car3Thread.start();
                car4Thread.start();
            }
        });

        ventana.setVisible(true);

    }

    // Method to resize the image
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

    // Method to position the images
    public static void posicionarImagen(JLabel imagen, int x, int y) {
        imagen.setBounds(x, y, imagen.getIcon().getIconWidth(), imagen.getIcon().getIconHeight());
    }

    public static void addBackground(JFrame frame, String imagePath) {
        ImageIcon fondo = new ImageIcon(imagePath); // Load the background image

        JLabel backgroundLabel = new JLabel(fondo);

        backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());

        frame.add(backgroundLabel);
    }

    public static void agregarEfectoHover(JLabel label, String rutaImagen, double porcentajeOriginal, double porcentajeHover) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                label.setIcon(redimensionarImagen(rutaImagen, porcentajeHover)); // Aumentar tamaño al hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                label.setIcon(redimensionarImagen(rutaImagen, porcentajeOriginal)); // Volver al tamaño original
            }
        });
    }

    // Método para cambiar el tamaño al hacer clic
    public static void agregarEfectoClic(JLabel label, String rutaImagen, double porcentajeOriginal, double porcentajeClic) {
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                label.setIcon(redimensionarImagen(rutaImagen, porcentajeClic)); // Aumentar tamaño al hacer clic
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                label.setIcon(redimensionarImagen(rutaImagen, porcentajeOriginal)); // Volver al tamaño original
            }
        });
    }
}
