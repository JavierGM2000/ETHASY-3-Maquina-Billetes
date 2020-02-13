package ethasy3maquinabilletes;


import java.sql.*;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suplente
 */
public class Cliente {
    String DNI;
    String Nombre;
    String Apellido1;
    String Apellido2;
    char Sexo;
    String fechaNac;
    String Contraseña;

    Cliente(VentanaPrincipal Padre,String inDNI)
    {
        try {
            Statement mysts = Padre.mycon.createStatement();
            String getUser = "SELECT * FROM cliente where DNI=\'"+inDNI+"\';";
            ResultSet rsUser = mysts.executeQuery(getUser);
            rsUser.first();
            DNI = rsUser.getString(1);
            Nombre= rsUser.getString(2);
            Apellido1 = rsUser.getString(3);
            Apellido2 = rsUser.getString(4);
            Sexo = rsUser.getString(5).charAt(0);
            fechaNac = rsUser.getString(6);

        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
