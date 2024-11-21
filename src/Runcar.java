import javax.swing.*;
import java.util.Random;

public class Runcar implements Runnable {
    private JLabel carLabel;
    private int currentX;
    private int startY;
    private static final int FINISH_LINE = 850; // Límite en el eje X
    private static boolean hasWinner = false;  // Controla si ya hay un ganador
    private static final Object lock = new Object(); // Para sincronización entre hilos

    public Runcar(JLabel carLabel, int startX, int startY) {
        this.carLabel = carLabel;
        this.currentX = startX; // Inicializar la posición actual
        this.startY = startY;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (currentX < FINISH_LINE && !hasWinner) {
            // Sumar un número aleatorio entre 1 y 6 al eje X
            currentX += random.nextInt(10) + 6 ;

            // Actualizar posición del JLabel
            carLabel.setLocation(currentX, startY);

            // Simular un pequeño retraso
            try {
                Thread.sleep(20); // Movimiento más suave
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Verificar si este coche ha ganado
            synchronized (lock) {
                if (currentX >= FINISH_LINE && !hasWinner) {
                    hasWinner = true;
                    System.out.println("¡Tenemos un ganador!");
                    System.out.println("Posiciones finales:");
                }
            }
        }

        // Imprimir la posición final del coche
        synchronized (lock) {
            System.out.println(carLabel.getText() + " : " + currentX);
        }
    }
}
