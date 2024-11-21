import javax.swing.*;
import java.util.Random;

public class Runcar implements Runnable {
    private JLabel carLabel;
    private int currentX;
    private int startY;
    private static final int FINISH_LINE = 850;
    private static boolean hasWinner = false;
    private static final Object lock = new Object();

    public Runcar(JLabel carLabel, int startX, int initialX, int startY) {
        this.carLabel = carLabel;
        this.currentX = startX;
        this.startY = startY;
    }

    @Override
    public void run() {
        Random random = new Random();

        while (currentX < FINISH_LINE && !hasWinner) {
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
                    System.out.println("¡Tenemos un ganador!");
                    System.out.println("Posiciones finales:");
                }
            }
        }

        synchronized (lock) {
            System.out.println(carLabel.getText() + " : " + currentX);
        }
    }



}
