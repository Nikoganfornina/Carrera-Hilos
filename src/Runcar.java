import javax.swing.*;
import java.util.*;

public class Runcar implements Runnable {
    private JLabel carLabel;
    private int currentX;
    private int startY;
    private static final int FINISH_LINE = 850;
    static boolean hasWinner = false;
    private static final Object lock = new Object();
    static boolean carreraActiva = true;

    // Lista estática para almacenar las posiciones finales (sin repeticiones)
    static final Map<String, Integer> posicionesFinales = new HashMap<>();

    // Tiempo del inicio
    private long startTime;

    public Runcar(JLabel carLabel, int startX, int initialX, int startY) {
        this.carLabel = carLabel;
        this.currentX = startX;
        this.startY = startY;
    }

    @Override
    public void run() {
        Random random = new Random();
        startTime = System.currentTimeMillis();  // Guardar el tiempo de inicio

        while (currentX < FINISH_LINE && carreraActiva) {
            currentX += random.nextInt(10) + 6;

            carLabel.setLocation(currentX, startY);

            try {
                Thread.sleep(23);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                // Cuando un coche cruza la meta por primera vez, registra el tiempo y el ganador
                if (currentX >= FINISH_LINE && !hasWinner) {
                    long endTime = System.currentTimeMillis();  // Tiempo al llegar a la meta
                    long timeTaken = endTime - startTime;  // Tiempo en milisegundos

                    hasWinner = true;  // Marca al primer coche que cruza la meta como ganador
                    carreraActiva = false;  // Detiene la carrera

                    // Mostrar los resultados
                    SwingUtilities.invokeLater(() -> {
                        ResultadosFrame.mostrarResultados(carLabel.getText(), timeTaken);
                    });
                    System.out.println(carLabel.getText() + " ha cruzado la meta en " + timeTaken + " ms");
                }
            }
        }
    }
}
