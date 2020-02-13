/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Suplente
 */
public class Main {

    static char LetrasDNI[]=new char[]{'t','r','w','a','g','m','y','f','p','d',
                                'x','b','n','j','z','s','q','v','h',
                                'l','c','k','e'};
    static int DiasMes[] = new int[]{31,28,31,30,31,30,31,30,31,30,31};

    public static ImageIcon ResizeImage(String Path, int width, int height)
    {
        BufferedImage img=null;
        try {
            img = ImageIO.read(new File(Path));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    static public class VentanaPrincipal extends JFrame
    {
        private Cliente Current;
        private float cobrar;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        FileWriter MyLog;
        String url="jdbc:mysql://localhost:3306/ethasy3test";
        Connection mycon;
        GeneralPanel[] ListaPanel= new GeneralPanel[8];
        VentanaPrincipal() throws SQLException, ClassNotFoundException, IOException
        {
            mycon = DriverManager.getConnection(url, "root", "");
            Date fecha= new Date();
            /*for(int i=0;i<14;i++)
            {
            CalcularTiempoPorLine(fecha);
            fecha = addDays(fecha,1);
            }*/
            setLayout(null);
            this.setBackground(Color.red);
            //this.setBounds(0, 0, 600, 800);
            ListaPanel[0]=new BienvenidaVentana(800,600,this);
            ListaPanel[0].setBounds(0, 0, 800, 600);
            ListaPanel[0].setVisible(true);
            add(ListaPanel[0]);

            ListaPanel[1]=new Login(800,600,this);
            ListaPanel[1].setBounds(0, 0, 800, 600);
            ListaPanel[1].setVisible(false);
            add(ListaPanel[1]);
            
            ListaPanel[2]=new Registrar(800,600,this);
            ListaPanel[2].setBounds(0, 0, 800, 600);
            ListaPanel[2].setVisible(false);
            add(ListaPanel[2]);
            
                    
            ListaPanel[5]=new EscogerTicket(800,600,this);
            ListaPanel[5].setBounds(0, 0, 800, 600);
            ListaPanel[5].setVisible(false);
            add(ListaPanel[5]);

            ListaPanel[3]=new SeleccionarOperacion(800,600,this);
            ListaPanel[3].setBounds(0, 0, 800, 600);
            ListaPanel[3].setVisible(false);
            add(ListaPanel[3]);

            ListaPanel[4]=new EscogerTicket(800,600,this);
            ListaPanel[4].setBounds(0, 0, 800, 600);
            ListaPanel[4].setVisible(true);
            add(ListaPanel[4]);

            ListaPanel[6]=new VentanaPagar(800,600,this);
            ListaPanel[6].setBounds(0, 0, 800, 600);
            ListaPanel[6].setVisible(false);
            add(ListaPanel[6]);

            
        }

        public void setCliente(Cliente New)
        {
            Current = New;
        }
        public Cliente getCliente()
        {
            return Current;
        }


        void CalcularTiempoPorLine(Date fecha) throws SQLException
        {
            Date date = fecha;
            SimpleDateFormat formattter = new SimpleDateFormat("yyyy-MM-dd");
            int hora_inicio=7*60;
            int t_paradas=5;
            int max_lim=24*60;
            int tiempo;
            Statement mysts2 = mycon.createStatement();
            Statement mysts = mycon.createStatement();
            String sql = "select CodLinea,count(*)*"+t_paradas+" as Mins from linea_parada group by CodLinea;";
            ResultSet rs = mysts2.executeQuery(sql);
            ResultSet rs2;
            ResultSet rs3;
            int codRecor=1;
            String consulta="INSERT INTO recorridos"
                       + " values (?,?,?,?,0);";
            PreparedStatement sentencia= mycon.prepareStatement(consulta);
            while(rs.next())
            {
                int num_buses;
                int hora_por_bus=hora_inicio;
                String sql2 = "select CodBus from autobus where CodLinea="+rs.getInt(1)+";";
                String sql3 = "select count(CodBus) from autobus where CodLinea="+rs.getInt(1)+";";
                rs2= mysts.executeQuery(sql3);
                rs2.first();
                num_buses=rs2.getInt(1);
                rs2= mysts.executeQuery(sql2);
                int current_bus=1;
                while(rs2.next())
                {
                    
                    int veces=(max_lim-(hora_inicio+30*((current_bus)-1)))/rs.getInt(2);
                    for(int x=0;x<veces;x++)
                    {
                    sentencia.setString(1, String.valueOf(codRecor++));
                    sentencia.setString(2, String.valueOf(rs2.getInt(1)));
                    sentencia.setString(3, formattter.format(date));
                    sentencia.setString(4, String.valueOf(((hora_inicio+30*((current_bus)-1)))+rs.getInt(2)*x));
                    sentencia.executeUpdate();
                    }
                    current_bus++;
                }

                current_bus=0;
            }
        }

        void LogThing(ArrayList<String> ToLog)
        {
            try {
                MyLog = new FileWriter("Logger.txt",true);
                Date date = new Date();
                MyLog.write("Action:"+formatter.format(date)+'\n');
                for (int i = 0; i < ToLog.size(); i++) {
                    MyLog.write(ToLog.get(i)+'\n');
                }
                MyLog.write("-------------------------------------"+'\n');
                MyLog.close();

            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        void PanelChanger(int desde,int a_cual)
        {
            ListaPanel[desde].setVisible(false);
            ListaPanel[a_cual].ClearText();
            ListaPanel[a_cual].setVisible(true);
            if(a_cual==3)
            {
                ((SeleccionarOperacion)ListaPanel[a_cual]).setBienvenida(Current);
            }
        }
    }
     static public class GeneralPanel extends JPanel implements ActionListener
     {

         void ClearText()
         {
         }

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

     }
    
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // TODO code application logic here
        /*MEGATEST*/
        VentanaPrincipal Princip= new VentanaPrincipal();
        Princip.setBounds(0,0,800,600);
        Princip.setVisible(true);
        Princip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Princip.setResizable(false);
    }
}
