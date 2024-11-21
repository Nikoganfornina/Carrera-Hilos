import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db"; // Ruta del archivo .db

    public static void crearBaseDeDatos() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                // Crear la tabla si no existe
                String createTableSQL = "CREATE TABLE IF NOT EXISTS partidas (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "coche_ganador TEXT NOT NULL," +
                        ");";

                Statement stmt = connection.createStatement();
                stmt.execute(createTableSQL);
                System.out.println("Base de datos y tabla creadas correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        }
    }
    public static void guardarPartidaGanada(String cocheGanador, int tiempo) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                String insertSQL = "INSERT INTO partidas (coche_ganador, tiempo) VALUES (?, ?)";
                var preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, cocheGanador);

                preparedStatement.executeUpdate();
                System.out.println("Partida guardada: " + cocheGanador );
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
}
}