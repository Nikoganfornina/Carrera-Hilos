import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {

    private static final String DATABASE_URL = "jdbc:sqlite:database.db"; // Ruta de la base de datos

    // Método para crear la base de datos y la tabla
    public static void crearBaseDeDatos() {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                // Crear la tabla si no existe
                String createTableSQL = "CREATE TABLE IF NOT EXISTS partidas (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "coche_ganador TEXT NOT NULL," +
                        "tiempo INTEGER NOT NULL" +
                        ");";

                Statement stmt = connection.createStatement();
                stmt.execute(createTableSQL);
                System.out.println("Base de datos y tabla creadas correctamente.");
            }
        } catch (Exception e) {
            System.out.println("Error al crear la base de datos: " + e.getMessage());
        }
    }

    // Método para guardar una partida ganada en la base de datos
    public static void guardarPartidaGanada(String cocheGanador, int tiempo) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            if (connection != null) {
                // Insertar los datos de la partida
                String insertSQL = "INSERT INTO partidas (coche_ganador, tiempo) VALUES (?, ?)";
                var preparedStatement = connection.prepareStatement(insertSQL);
                preparedStatement.setString(1, cocheGanador);
                preparedStatement.setInt(2, tiempo);  // Por ejemplo, el tiempo en segundos

                preparedStatement.executeUpdate();
                System.out.println("Partida guardada: " + cocheGanador + " en " + tiempo + " segundos.");
            }
        } catch (Exception e) {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }
}
