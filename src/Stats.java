import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Stats {
    private static final String DATABASE_URL = "jdbc:sqlite:database.db";

    public static void mostrarVictorias() {
        // Crear la ventana de victorias
        JFrame ventanaVictorias = new JFrame("Partidas Ganadas por los Coches");
        ventanaVictorias.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaVictorias.setSize(400, 300);

        // Panel para contener las etiquetas
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Recuperamos y mostramos las victorias de cada coche
        String[] coches = {"Coche 1", "Coche 2", "Coche 3", "Coche 4"};
        for (String coche : coches) {
            String partidasGanadas = getPartidasGanadas(coche);
            JLabel label = new JLabel(coche + " - Partidas ganadas: " + partidasGanadas);
            panel.add(label);
        }

        // Añadir el panel al JFrame
        ventanaVictorias.add(panel);
        ventanaVictorias.setVisible(true);
    }

    // Método para obtener las partidas ganadas de la base de datos
    private static String getPartidasGanadas(String coche) {
        String partidasGanadas = "0";  // Valor por defecto si no se encuentra el coche en la base de datos

        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                String query = "SELECT COUNT(*) FROM partidas WHERE coche_ganador = ?";
                var preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, coche);

                var resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    partidasGanadas = resultSet.getString(1);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener las partidas ganadas: " + e.getMessage());
        }

        return partidasGanadas;
    }
}
