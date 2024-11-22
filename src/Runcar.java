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

    static final Map<String, Integer> posicionesFinales = new HashMap<>();

    public Runcar(JLabel carLabel, int startX, int initialX, int startY ) {
        this.carLabel = carLabel;
        this.currentX = startX;
        this.startY = startY;

    }

    @Override
    public void run() {

        Random random = new Random();

        while (currentX < FINISH_LINE && carreraActiva) {
            currentX += random.nextInt(10) + 6;  // Movimiento aleatorio

            carLabel.setLocation(currentX, startY);

            try {
                Thread.sleep(23);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock) {
                // Registrar el resultado solo si el coche cruza la línea por primera vez
                if (currentX >= FINISH_LINE && !posicionesFinales.containsKey(carLabel.getText())) {
                    posicionesFinales.put(carLabel.getText(), currentX);

                }

                // Verificar si alguien ganó la carrera
                if (!hasWinner && currentX >= FINISH_LINE) {
                    hasWinner = true;
                    carreraActiva = false;  // Detener la carrera para los demás
                    System.out.println("¡Tenemos un ganador!");
                }

                // Si la carrera ha terminado y hay resultados
                if (!carreraActiva && posicionesFinales.size() > 0) {
                    List<Map.Entry<String, Integer>> resultadosOrdenados = new ArrayList<>(posicionesFinales.entrySet());
                    resultadosOrdenados.sort((a, b) -> b.getValue() - a.getValue());  // Ordenar por posición

                    // Obtener el ganador (primera posición) y su tiempo
                    String ganador = resultadosOrdenados.get(0).getKey();
                    Integer tiempo = resultadosOrdenados.get(0).getValue();  // Tiempo (posición final en X)




                    // Usar SwingUtilities para invocar el método de la interfaz de usuario
                    SwingUtilities.invokeLater(() -> ResultadosFrame.mostrarResultados(ganador, tiempo));

                }
            }
        }
    }
}
