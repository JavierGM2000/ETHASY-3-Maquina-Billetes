package ethasy3maquinabilletes;


import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
String hora;
int Origen;
int LLegada;
double precio;
int Linea;
int NumBus;
int CodRecorrido;
VentanaPrincipal Padre;
    Billete(VentanaPrincipal Parent, String inDNI,Date inDate, String inFecha, int inOrigen,int inLegada,double inPrecio,int inLinea,int inBus,String inHora,int inCodRecorrido)
    {
        try {
            Padre=Parent;
            Statement stBil = Parent.mycon.createStatement();
            String query ="SELECT CodBil FROM Billetes order by CodBil";
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
            hora=inHora;
            CodRecorrido = inCodRecorrido;
            
        } catch (SQLException ex) {
            Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
        @Override
        public String toString()
        {
        String Result="\n";
    try {
        String sql="SELECT Nombre FROM parada WHERE CodParad=?";
        PreparedStatement prep = Padre.mycon.prepareStatement(sql);
        prep.setInt(1, Origen);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh-mm");
        ResultSet rs = prep.executeQuery();
        rs.first();
        
        Result +=" Numero Billete: "+NumB+'\n';
        Result +=" DNI: "+DNI+'\n';
        Result +=" FechaSalida: "+fechaS+'\n';
        Result +=" Hora salida: "+hora+'\n';
        Result +=" Origen: "+Origen+'-'+rs.getString(1)+'\n';
        prep.setInt(1, LLegada);
        rs = prep.executeQuery();
        rs.first();
        Result +=" Llegada: "+LLegada+'-'+rs.getString(1)+'\n';
        Result +=" Linea: "+Linea+'\n';
        Result +=" NumBus: "+NumBus+'\n';
        
        
        
    } catch (SQLException ex) {
        Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
    }
        return Result;
        }
        
        double toDouble()
        {
            double roundedFloat = Math.round(precio * 100.0) / 100.0;
            return roundedFloat;
            
        }
        
        
        void InserEnBaseDatos()
        {
    try {
        DateFormat dateFormatF = new SimpleDateFormat("yyyy-MM-dd");
        String sqlBillete="INSERT INTO billetes VALUES("+NumB+",\'"+DNI+"\',"
                + "\'"+dateFormatF.format(fechaC)+"\',\'"+fechaS+
                "\',\'"+hora+"\',"+Origen+","+LLegada+","
                +Linea+","+NumBus+","+precio+")";
        Statement sts= Padre.mycon.createStatement();
        sts.executeUpdate(sqlBillete);
        
        String sqlRecorridos= "UPDATE recorridos SET PlazasOcupadas=PlazasOcupadas+1 "
                + "WHERE cod_Recorrido=\'"+CodRecorrido+"\' AND Dia=\'"+fechaS+"\'";
        sts.execute(sqlRecorridos);
        
        
    } catch (SQLException ex) {
        Logger.getLogger(Billete.class.getName()).log(Level.SEVERE, null, ex);
    }
        }

        void imprimirTicket() throws SQLException
        {
        try {
            String sql="SELECT Nombre FROM parada WHERE CodParad=?";
            PreparedStatement prep = Padre.mycon.prepareStatement(sql);
            prep.setInt(1, Origen);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  hh-mm");
            ResultSet rs = prep.executeQuery();
            rs.first();
            FileWriter MyLog;
            String Out= DNI+dateFormat.format(fechaC)+".txt";
            MyLog = new FileWriter(Out.trim());
            MyLog.append("Numero Billete:"+NumB+'\n');
            MyLog.append("DNI:"+DNI+'\n');
            MyLog.append("FechaSalida:"+fechaS+'\n');
            MyLog.append("Hora salida:"+hora+'\n');
            MyLog.append(" Origen: "+Origen+'-'+rs.getString(1)+'\n');
            prep.setInt(1, LLegada);
            rs = prep.executeQuery();
            rs.first();
            MyLog.append(" Llegada: "+LLegada+'-'+rs.getString(1)+'\n');
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

