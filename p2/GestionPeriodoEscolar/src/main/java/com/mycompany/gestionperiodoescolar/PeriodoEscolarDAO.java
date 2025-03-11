package com.mycompany.gestionperiodoescolar;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase se encarga de realizar operaciones CRUD sobre la tabla PeriodoEscolar.
 * Utiliza los procedimientos almacenados en MySQL.
 */
public class PeriodoEscolarDAO {

    // Nombres de los procedimientos almacenados
    private static final String SQL_INSERT = "call insertar_periodo(?, ?, ?, ?)";
    private static final String SQL_SELECT = "call consultar_periodos()";
    private static final String SQL_DELETE = "call eliminar_periodo(?)";
    private static final String SQL_SELECT_BY_ID = "call buscar_periodo(?)";
    private static final String SQL_UPDATE = "call actualizar_periodo(?, ?, ?, ?, ?)";

    /**
     * Inserta un nuevo Periodo Escolar.
     */
    public void create(PeriodoEscolar periodo) {
        try (Connection conn = PeriodoEscolarDTO.getConexion();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {

            stmt.setString(1, periodo.getNombre());
            stmt.setDate(2, new Date(periodo.getFechaInicio().getTime())); // java.util.Date -> java.sql.Date
            stmt.setDate(3, new Date(periodo.getFechaTermino().getTime()));
            stmt.setBoolean(4, periodo.isStatus());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Periodo Escolar insertado exitosamente.");
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el periodo escolar.");
            e.printStackTrace();
        }
    }

    /**
     * Consulta todos los Periodos Escolares.
     */
    public List<PeriodoEscolar> select() {
        List<PeriodoEscolar> listaPeriodos = new ArrayList<>();

        try (Connection conn = PeriodoEscolarDTO.getConexion();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PeriodoEscolar periodo = new PeriodoEscolar();

                periodo.setIdPeriodoEscolar(rs.getInt("idPeriodoEscolar"));
                periodo.setNombre(rs.getString("nombre"));
                periodo.setFechaInicio(rs.getDate("fechaInicio"));
                periodo.setFechaTermino(rs.getDate("fechaTermino"));
                periodo.setStatus(rs.getBoolean("status"));

                listaPeriodos.add(periodo);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar los periodos escolares.");
            e.printStackTrace();
        }

        return listaPeriodos;
    }

    /**
     * Consulta un Periodo Escolar por su ID.
     */
    public PeriodoEscolar selectById(int id) {
        PeriodoEscolar periodo = null;

        try (Connection conn = PeriodoEscolarDTO.getConexion();
             PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    periodo = new PeriodoEscolar();
                    periodo.setIdPeriodoEscolar(rs.getInt("idPeriodoEscolar"));
                    periodo.setNombre(rs.getString("nombre"));
                    periodo.setFechaInicio(rs.getDate("fechaInicio"));
                    periodo.setFechaTermino(rs.getDate("fechaTermino"));
                    periodo.setStatus(rs.getBoolean("status"));
                } else {
                    System.out.println("No se encontró el Periodo Escolar con ID: " + id);
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al buscar el periodo escolar por ID.");
            e.printStackTrace();
        }

        return periodo;
    }

    /**
     * Actualiza los datos de un Periodo Escolar existente.
     */
    public void update(PeriodoEscolar periodo) {
        try (Connection conn = PeriodoEscolarDTO.getConexion();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {

            stmt.setInt(1, periodo.getIdPeriodoEscolar());
            stmt.setString(2, periodo.getNombre());
            stmt.setDate(3, new Date(periodo.getFechaInicio().getTime()));
            stmt.setDate(4, new Date(periodo.getFechaTermino().getTime()));
            stmt.setBoolean(5, periodo.isStatus());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Periodo Escolar actualizado exitosamente.");
            } else {
                System.out.println("No se encontró el Periodo Escolar con ID: " + periodo.getIdPeriodoEscolar());
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al actualizar el periodo escolar.");
            e.printStackTrace();
        }
    }

    /**
     * Elimina (desactiva) un Periodo Escolar por su ID.
     */
    public void delete(int id) {
        try (Connection conn = PeriodoEscolarDTO.getConexion();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Periodo Escolar eliminado (desactivado) exitosamente.");
            } else {
                System.out.println("No se encontró el Periodo Escolar con ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el periodo escolar.");
            e.printStackTrace();
        }
    }

}
