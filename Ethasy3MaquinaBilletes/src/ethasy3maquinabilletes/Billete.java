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
int Origen;
int LLegada;
double precio;
int Linea;
int NumBus;
VentanaPrincipal Padre;
    Billete(VentanaPrincipal Parent, String inDNI,Date inDate, String inFecha, int inOrigen,int inLegada,double inPrecio,int inLinea,int inBus)
    {
        try {
            Padre=Parent;
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
            MyLog.append("Linea:"+Linea+'\n');
            MyLog.append("NumBus:"+NumBus+'\n');
            MyLog.append("Preccio:"+precio+'\n');



            MyLog.close();

        } catch (IOException ex) {
            Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public void CalcularPrecio()
        {
        try {
            double Lat1;
            double Long1;
            double Lat2;
            double Long2;
            String sql = "SELECT Latitud,Longitud FROM parada where CodParad=?";
            PreparedStatement mysts = Padre.mycon.prepareStatement(sql);
            mysts.setString(1, String.valueOf(Origen));
            ResultSet rsLatLon = mysts.executeQuery();
            rsLatLon.first();
            Lat1 = rsLatLon.getDouble(1);
            Long1 =rsLatLon.getDouble(2);
            mysts.setString(1, String.valueOf(LLegada));
            rsLatLon = mysts.executeQuery();
            rsLatLon.first();
            Lat2 = rsLatLon.getDouble(1);
            Long2 =rsLatLon.getDouble(2);
            double distancia= calcularDistancia((float)Lat1, (float)Long2, (float)Lat2, (float)Long2);
            double consumo;
            sql= "SELECT Consumo FROM autobus where CodBus=?";
            mysts = Padre.mycon.prepareStatement(sql);
            mysts.setString(1, String.valueOf(NumBus));
            rsLatLon= mysts.executeQuery();
            rsLatLon.first();
            consumo=rsLatLon.getDouble(1);
            precio=0.8*1.2*consumo*distancia;

        } catch (SQLException ex) {
            Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        public static double calcularDistancia(float Lat, float Long, float latitud, float longitud) {
            double radioTierra = 6371;//en kilometros
            double dlat = Math.toRadians(Lat - latitud);
            double dlng = Math.toRadians(Long - longitud);
            double sindLat = Math.sin(dlat / 2);
            double sindLng = Math.sin(dlng / 2);
            double va1 = Math.pow(sindLat, 2)
            + Math.pow(sindLng, 2) * Math.cos(Math.toRadians(Lat)) * Math.cos(Math.toRadians(latitud));
            double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
            double distancia = radioTierra * va2;
            return distancia; // distancia en kms entre dos puntos(paradaInicio-paradaFin)
    }
}

