package ethasy3maquinabilletes;


import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class Billete {
int NumB;
String DNI;
Date fechaC;
String fechaS;
String Origen;
String LLegada;
double precio;
int Linea;
int NumBus;
    Billete(VentanaPrincipal Parent, String inDNI,Date inDate, String inFecha, String inOrigen,String inLegada,double inPrecio,int inLinea,int inBus)
    {
        try {

            Statement stBil = Parent.mycon.createStatement();
            String query ="SELECT CodBil FROM Billetes";
            ResultSet rs = stBil.executeQuery(query);
            if(rs.last())
            {
                int LastCod = rs.getInt(1)+1;
                NumB=LastCod;
            }
            else
            {
                NumB=1;
            }
            DNI = inDNI;
            fechaC= inDate;
            fechaS= inFecha;
            Origen= inOrigen;
            LLegada=inLegada;
            precio=inPrecio;
            Linea=inLinea;
            NumBus=inBus;

        } catch (SQLException ex) {
            Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        void imprimirTicket()
        {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-hh-mm");
            FileWriter MyLog;
            String Out= DNI+dateFormat.format(fechaC)+".txt";
            MyLog = new FileWriter(Out.trim());
            MyLog.append("Numero Billete:"+NumB+'\n');
            MyLog.append("DNI:"+DNI+'\n');
            MyLog.append("FechaSalida:"+fechaS+'\n');
            MyLog.append("Origen:"+Origen+'\n');
            MyLog.append("Llegada:"+LLegada+'\n');
            MyLog.append("Precio:"+precio+'\n');
            MyLog.append("Linea:"+Linea+'\n');
            MyLog.append("NumBus"+NumBus+'\n');
            MyLog.close();

        } catch (IOException ex) {
            Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}
