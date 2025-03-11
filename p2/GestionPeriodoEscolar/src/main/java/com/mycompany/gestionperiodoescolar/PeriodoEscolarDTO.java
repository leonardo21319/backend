package com.mycompany.gestionperiodoescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * PeriodoEscolarDTO
 * 
 * Esta clase se encarga de gestionar la conexión a la base de datos MySQL.
 * Es un equivalente a lo que en el proyecto anterior era ProgramaAcademicoDTO.
 * Aquí centralizamos la conexión para que pueda ser utilizada por el DAO.
 */
public class PeriodoEscolarDTO {

    // Atributo opcional: representa un objeto del modelo PeriodoEscolar
    PeriodoEscolar periodoEscolar;

    // Configuración de la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/7cm1";
    private static final String USUARIO = "root";      // Usuario de tu base de datos MySQL
    private static final String PASSWORD = "root";     // Contraseña de tu base de datos MySQL

    /**
     * Constructor vacío.
     * Puedes usarlo si necesitas instanciar el DTO con un objeto PeriodoEscolar.
     */
    public PeriodoEscolarDTO() {
        // Constructor sin lógica específica por ahora
    }

    /**
     * Método estático que devuelve la conexión a la base de datos.
     * Es llamado por el DAO para obtener un objeto Connection.
     *
     * @return Una conexión activa a la base de datos MySQL.
     * @throws SQLException Si falla la conexión.
     */
    public static Connection getConexion() throws SQLException {
        // Devuelve un objeto Connection usando los parámetros de conexión
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

}
