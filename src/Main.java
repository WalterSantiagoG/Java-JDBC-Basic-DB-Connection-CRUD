import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection myConn = null; //Carga lo necesario para establecer conexión con la BD: URL de la BD, usuario y contraseñas
        Statement myStant = null; //Se utiliza para enviar consultas SQL simples sin parámetros
        Statement myStantU = null; //Se utiliza para enviar consultas SQL simples sin parámetros
        Statement myStantD = null; //Se utiliza para enviar consultas SQL simples sin parámetros
        PreparedStatement myPStant = null; //Nos permite crear consultas SQL con parametros variables.
        ResultSet myRes = null; //Para obtener los resultados de una consulta SQL una BD
        ResultSet myResU = null; //Para obtener los resultados de una consulta SQL una BD
        ResultSet myResD = null; //Para obtener los resultados de una consulta SQL una BD
        try {
            //Se sube a GitHub por fines educativos, en producción no se cargan archivos que contengan credenciales
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bdjava","root","");
            System.out.println("Genial, nos conectamos");

            //-------Leer datos -------
            //myStant = myConn.createStatement();
            //myRes = myStant.executeQuery("SELECT * FROM employees"); //ExecuteQuery( ): Ejecuta una consulta SQL en la base de datos y devuelve un objeto ResultSet con los resultados

            //next( ): Avanzar al siguiente registro en un conjunto de resultados
            //get( ): Para obtener valores de los registros recuperados de la BD, existen varios métodos
            //getString( ): Para recuperar cadenas de texto. Tenemos tambien: getInt, getDouble, getFloat, getBoolean, getDate, getTime, getTimestamp
            //while(myRes.next()){
            //    System.out.println(myRes.getString("first_name"));
            //}

            //-------Insertar datos -------
            //String sql = ("INSERT INTO employees (first_name, pa_surname) values (?,?)");
            //myPStant = myConn.prepareStatement(sql);
            //myPStant.setString(1,"Johana");
            //myPStant.setString(2,"Dorantes");

            //int rowsAffected = myPStant.executeUpdate(); //executedUpdate( ): Ejecutar sentencia SQL que modifica los datos en la BD y devuelve el número de filas afectadas.
            //if (rowsAffected > 0){
            //    System.out.println("Se ha creado un nuevo empleado");
            //}

            //-------Actualizar datos -------
            myStantU = myConn.createStatement();
            int rowsAffectedU = myStantU.executeUpdate("UPDATE employees " + "set email='johannador1@mail.com' "+"WHERE first_name = 'Johana'"); //executedUpdate( ): Ejecutar sentencia SQL que modifica los datos en la BD y devuelve el número de filas afectadas.

            System.out.println("Empleados actuales");
            myResU = myStantU.executeQuery("SELECT * FROM employees order by pa_surname");
            while (myResU.next()){
                System.out.println(myResU.getString("first_name") + ", " + myResU.getString("email"));
            }

            //-------Eliminar datos -------
            myStantD = myConn.createStatement();
            int rowsAffectedD = myStantD.executeUpdate("DELETE FROM employees " + "WHERE first_name = 'Johana'"); //executedUpdate( ): Ejecutar sentencia SQL que modifica los datos en la BD y devuelve el número de filas afectadas.

            System.out.println("Empleados despues de eliminar");
            myResD = myStantD.executeQuery("SELECT * FROM employees order by pa_surname");
            while (myResD.next()){
                System.out.println(myResD.getString("first_name") + ", " + myResD.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Algo salio mal");
        }
    }
}