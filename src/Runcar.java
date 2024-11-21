import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Runcar implements Runnable {
    private JLabel carLabel;
    private int currentX;
    private int startY;
    private static final int FINISH_LINE = 850;
    private static boolean hasWinner = false;
    private static final Object lock = new Object();
    static boolean carreraActiva = true; // Aquí se define

    public Runcar(JLabel carLabel, int startX, int initialX, int startY) {
        this.carLabel = carLabel;
        this.currentX = startX;
        this.startY = startY;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (currentX < FINISH_LINE && carreraActiva) {
            currentX += random.nextInt(10) + 6;

            carLabel.setLocation(currentX, startY);

            try {
                Thread.sleep(23);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                if (currentX >= FINISH_LINE && !hasWinner) {
                    hasWinner = true;
                    carreraActiva = false; // Detener la carrera para los demás
                    System.out.println("¡Tenemos un ganador!");
                }
            }
        }
        synchronized (lock) {
            System.out.println(carLabel.getText() + " : " + currentX);
        }
    }
}






