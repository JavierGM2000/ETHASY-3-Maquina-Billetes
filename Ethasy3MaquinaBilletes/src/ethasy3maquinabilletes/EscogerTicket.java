
package ethasy3maquinabilletes;

import ethasy3maquinabilletes.Main.GeneralPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.CheckBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class EscogerTicket extends GeneralPanel {
    private JComboBox lineaComboBox,horaComboBox,diaComboBox;
    private JLabel lineaLabel,salidaLabel,llegadaLabel,diaLabel,horaLabel,numeroAutobusLabel,plazasLibresLabel;
    private JScrollPane salidaScroll,llegadaScroll;
    private JButton volver,continuar;
    private JPanel SalidasPanel,LlegadasPanel;
    private int currentPos,codLlegada,codSalida,codLinea,posicionSalida,posicionLlegada,isVuelta;
    private JCheckBox checkboxIdaVuelta;
    private String nombreSalida,nombreLlegada;
    private boolean pulsado=false;
    
    
    ArrayList<JButton> BotonesSalida = new ArrayList();
    ArrayList<JButton> BotonesLlegada = new ArrayList();
    ArrayList<Integer> CodigosParadas = new ArrayList();
    ArrayList<Integer> CodigosLineas = new ArrayList();
    
    
        String url="jdbc:mysql://localhost:3306/ethasy3test";
        Connection mycon;
 
         EscogerTicket(int w,int h,Main.VentanaPrincipal Parent) throws SQLException, ClassNotFoundException
        {
           mycon = DriverManager.getConnection(url, "root", "");
         
           
          setLayout(null);
          
          lineaComboBox= new JComboBox();
          lineaComboBox.setBounds(180,70,400,20);
            Statement mysts= mycon.createStatement();
            String sql="select * from linea";
            ResultSet rs = mysts.executeQuery(sql);
            while(rs.next())
            {
               lineaComboBox.addItem(rs.getString("Nombre"));
               CodigosLineas.add(rs.getInt("CodLinea"));
            }
          
          add(lineaComboBox);
          lineaComboBox.addActionListener(this);
          
  
          checkboxIdaVuelta= new JCheckBox("¿Quieres que el viaje sea de Ida y Vuelta?");
          checkboxIdaVuelta.setBounds(180,130,350,30);
          add(checkboxIdaVuelta);
          
          lineaLabel= new JLabel("Linea:");
          lineaLabel.setBounds(180,40,80,20);
          lineaLabel.setFont (lineaLabel.getFont ().deriveFont (20f));
          add(lineaLabel);  
          
      
          SalidasPanel = new JPanel();
          SalidasPanel.setLayout(null);
          SalidasPanel.setPreferredSize(new Dimension(500,500));
          SalidasPanel.setBackground(Color.white);
          
          //SCROLL SALIDAS
          salidaScroll = new JScrollPane(SalidasPanel);
          salidaScroll.setBounds(30, 230, 200, 250);
          salidaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          add(salidaScroll);
          
           
          
          salidaLabel= new JLabel("Salida:");
          salidaLabel.setBounds(30, 100, 200, 200);
          salidaLabel.setFont (salidaLabel.getFont ().deriveFont (20f));
          add(salidaLabel);  
          
      
          LlegadasPanel = new JPanel();
          LlegadasPanel.setLayout(null);
          LlegadasPanel.setPreferredSize(new Dimension(500,500));
          LlegadasPanel.setBackground(Color.white);
         
          llegadaLabel= new JLabel("Llegada:");
          llegadaLabel.setBounds(270, 100, 200, 200);
          llegadaLabel.setFont (llegadaLabel.getFont ().deriveFont (20f));
          add(llegadaLabel);  
          
          llegadaScroll = new JScrollPane(LlegadasPanel);
          llegadaScroll.setBounds(270, 230, 200, 250);
          llegadaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          add(llegadaScroll);
          
          diaComboBox= new JComboBox();
          diaComboBox.setBounds(570, 260, 100, 30);
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(date);
          
             calendario.add(Calendar.DATE, 0);  
             Date diaHoy = calendario.getTime();
             diaComboBox.addItem(dateFormat.format(diaHoy));  
             
            for(int i=0;i<14;i++){
              
              calendario.add(Calendar.DATE, 1);  
              Date diaDespues = calendario.getTime();
              diaComboBox.addItem(dateFormat.format(diaDespues));  

            }
          diaComboBox.addActionListener(this);
          add(diaComboBox);
          
          
        
          diaLabel= new JLabel("Día:");
          diaLabel.setBounds(525, 175, 200, 200);
          diaLabel.setFont (diaLabel.getFont ().deriveFont (20f));
          add(diaLabel); 
          
          volver=new JButton("Volver");
          volver.setBounds(50, 500, 100, 50);
          add(volver);
          
          continuar=new JButton("Continuar");
          continuar.setBounds(650, 500, 100, 50);
          add(continuar);
               
          horaComboBox= new JComboBox();
          horaComboBox.setBounds(580, 340, 100, 30);
          horaComboBox.addActionListener(this);
          add(horaComboBox);
          
          horaLabel= new JLabel("Hora:");
          horaLabel.setBounds(525, 340, 100, 30);
          horaLabel.setFont (horaLabel.getFont ().deriveFont (20f));
          add(horaLabel); 
          
          numeroAutobusLabel= new JLabel("Número Autobus:");
          numeroAutobusLabel.setBounds(525, 410, 200, 30);
          numeroAutobusLabel.setFont (numeroAutobusLabel.getFont ().deriveFont (20f));
          add(numeroAutobusLabel);
          
          plazasLibresLabel= new JLabel("Plazas libres:");
          plazasLibresLabel.setBounds(525, 450, 200, 30);
          plazasLibresLabel.setFont (plazasLibresLabel.getFont ().deriveFont (20f));
          add(plazasLibresLabel);
     }
     
     
      public void actionPerformed(ActionEvent e){
      
        if(e.getSource()==lineaComboBox){ 
        codLinea=CodigosLineas.get(lineaComboBox.getSelectedIndex());
            
        pulsado=false;    
            
        try {
        mycon = DriverManager.getConnection(url, "root", "");
        Statement mysts= mycon.createStatement();  
        String selectSalidas="SELECT p.Nombre,p.CodParad\n" +
        "FROM parada p,linea_parada lp,linea l\n" +
        "WHERE p.CodParad=lp.CodParad AND lp.CodLinea=l.CodLinea\n" +
        "AND l.Nombre=\'"+lineaComboBox.getSelectedItem()+"\'ORDER BY lp.posicion";
        
        ResultSet resSelectSalidas = mysts.executeQuery(selectSalidas);
       
        
        BotonesSalida.clear();
        SalidasPanel.removeAll();
        
        BotonesLlegada.clear();
        LlegadasPanel.removeAll();
        
        CodigosParadas.clear();
        
        int i=0;
        
        
        while(resSelectSalidas.next())
            {
            BotonesSalida.add(new JButton(resSelectSalidas.getString("Nombre")));
            BotonesSalida.get(i).setBounds(0, 20*i, 200, 20);
            SalidasPanel.add(BotonesSalida.get(i));
            BotonesSalida.get(i).addActionListener(this);
            BotonesSalida.get(i).setBackground(null);
            CodigosParadas.add(resSelectSalidas.getInt(2));
            
            BotonesLlegada.add(new JButton(resSelectSalidas.getString("Nombre")));
            BotonesLlegada.get(i).setBounds(0, 20*i, 200, 20);
            LlegadasPanel.add(BotonesLlegada.get(i));
            BotonesLlegada.get(i).addActionListener(this);
            BotonesLlegada.get(i).setBackground(null);
            
            i++;
            }
        SalidasPanel.setPreferredSize(new Dimension(500,i*20+20));
        this.repaint();
        SalidasPanel.updateUI();
        SalidasPanel.repaint();
        salidaScroll.updateUI();
        
        LlegadasPanel.setPreferredSize(new Dimension(500,i*20+20));
        this.repaint();
        LlegadasPanel.updateUI();
        LlegadasPanel.repaint();
        LlegadasPanel.updateUI();
        
           
        
        } catch (SQLException ex) {
            Logger.getLogger(EscogerTicket.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
               
         
         for(int x=0;x<BotonesSalida.size();x++){
            
               
           if(e.getSource()==BotonesSalida.get(x)){
                
                pulsado=true;
               
                for(int z=0;z<BotonesLlegada.size();z++){
                BotonesLlegada.get(z).setBackground(null);
                BotonesLlegada.get(z).setVisible(true);   
                }
                
                horaComboBox.removeActionListener(this);
                horaComboBox.removeAllItems();
                horaComboBox.addActionListener(this);
                
                
                       for(int y=0;y<BotonesSalida.size();y++){
                           
                           BotonesSalida.get(y).setBackground(null);
                           
                       }
                       
                       
                       nombreSalida=BotonesSalida.get(x).getText();
        
                       BotonesSalida.get(x).setBackground(Color.yellow);
                       LlegadasPanel.remove(BotonesSalida.get(x));
                       BotonesLlegada.get(x).setVisible(false);
                       
                    
                       codSalida=CodigosParadas.get(x);
                       
                       
                  
              
             }
        
         }
          
        
          for(int x=0;x<BotonesLlegada.size();x++){
               
           if(e.getSource()==BotonesLlegada.get(x)){
                   
                   
                   
                   for(int y=0;y<BotonesLlegada.size();y++){
                       BotonesLlegada.get(y).setBackground(null);
                       
                   }
                   
                   BotonesLlegada.get(x).setBackground(Color.yellow);
                   nombreLlegada=BotonesLlegada.get(x).getText();
                   
            
                
             if(pulsado==true){
                 
                 
                 
               codLlegada=CodigosParadas.get(x);
               
               try{
                   
                   try {    
                  
                   String sqlSalidaPos="SELECT Posicion FROM linea_parada WHERE CodParad=? AND CodLinea=?";
                   PreparedStatement mystsSalidaPos= mycon.prepareStatement(sqlSalidaPos);
                   mystsSalidaPos.setString(1, String.valueOf(codSalida));
                   mystsSalidaPos.setString(2, String.valueOf(codLinea));
                   ResultSet rsSalidaPos= mystsSalidaPos.executeQuery();
                  
                    while(rsSalidaPos.next())
                    {
                     posicionSalida=rsSalidaPos.getInt("Posicion");
                    }
                    
                   mystsSalidaPos.setString(1, String.valueOf(codLlegada));
                   mystsSalidaPos.setString(2, String.valueOf(codLinea));
                   rsSalidaPos= mystsSalidaPos.executeQuery();
                    while(rsSalidaPos.next())
                    {
                     posicionLlegada=rsSalidaPos.getInt("Posicion");
                    }
              
                   
               } catch (SQLException ex) {
                   Logger.getLogger(EscogerTicket.class.getName()).log(Level.SEVERE, null, ex);
               }
                   
                   if(posicionSalida<posicionLlegada){
                       isVuelta=0;
                   }else{
                       isVuelta=1;
                   }
                   
                       currentPos=x;
                       Statement mystsHora= mycon.createStatement();
                       String sqlHora="SELECT r.HoraSalida\n" +
                               "FROM recorridos r,autobus a,linea l\n" +
                               "WHERE r.CodAutobus=a.CodBus AND l.CodLinea=a.CodLinea\n" +
                               "AND l.Nombre=\'"+lineaComboBox.getSelectedItem()+"\' AND r.Dia=\'"+diaComboBox.getSelectedItem()+"\' AND isVuelta="+isVuelta+" ORDER BY HoraSalida";
                       ResultSet rsHora = mystsHora.executeQuery(sqlHora);
                       while(rsHora.next())
                           
                       {
                           
                           int minutos_total=rsHora.getInt("HoraSalida");
                           minutos_total+=5*(posicionSalida-1);
                           int horas=minutos_total/60;
                           int minutos=minutos_total-(horas*60);
                           if(horas>24){
                               horas-=24;
                           }
                           if(minutos>=10)
                               horaComboBox.addItem(String.valueOf(horas)+':'+String.valueOf(minutos));
                           else if(minutos>0)
                               horaComboBox.addItem(String.valueOf(horas)+":0"+String.valueOf(minutos));
                           else
                               horaComboBox.addItem(String.valueOf(horas)+":00");
                       } 
                       
                       } catch (SQLException ex) {
                       Logger.getLogger(EscogerTicket.class.getName()).log(Level.SEVERE, null, ex);
                       }
               
               
               
             }
           }
        
         }
          
          
        if(e.getSource()==horaComboBox){ 
            
              
                
                int char_pos=horaComboBox.getSelectedItem().toString().indexOf(":");
                int horas=Integer.parseInt(horaComboBox.getSelectedItem().toString().substring(0, char_pos))*60;
                int mins=Integer.parseInt(horaComboBox.getSelectedItem().toString().substring(char_pos+1));
                int totalmins=horas+mins-(5*currentPos);
                
            try {
               
                Statement mystsAutobus= mycon.createStatement();
                String sqlAutobus="SELECT CodAutobus FROM recorridos,autobus,linea WHERE recorridos.CodAutobus=autobus.CodBus AND autobus.CodLinea=linea.CodLinea AND recorridos.Dia=\'"+diaComboBox.getSelectedItem()+"\' AND recorridos.HoraSalida="+(totalmins)+" AND linea.Nombre=\'"+lineaComboBox.getSelectedItem()+"\'";  
                ResultSet rsAutobus = mystsAutobus.executeQuery(sqlAutobus);
                 while(rsAutobus.next())
                       {
                numeroAutobusLabel.setText("Número Autobus: "+rsAutobus.getString("CodAutobus"));
                       }
                     
            } catch (SQLException ex) {
                Logger.getLogger(EscogerTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Statement mystsPlazas= mycon.createStatement();
                String sqlPlazas="SELECT Plazas-PlazasOcupadas FROM recorridos,autobus,linea WHERE recorridos.CodAutobus=autobus.CodBus AND autobus.CodLinea=linea.CodLinea AND recorridos.Dia=\'"+diaComboBox.getSelectedItem()+"\' AND recorridos.HoraSalida="+(totalmins)+" AND linea.Nombre=\'"+lineaComboBox.getSelectedItem()+"\'";  
                ResultSet rsPlazas = mystsPlazas.executeQuery(sqlPlazas);
                 while(rsPlazas.next())
                       {
                plazasLibresLabel.setText("Plazas libres: "+rsPlazas.getString("Plazas-PlazasOcupadas"));
                       }
                     
            } catch (SQLException ex) {
                Logger.getLogger(EscogerTicket.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
          
      }

    
    
    /*public static void main(String[]args) throws SQLException, ClassNotFoundException {
    EscogerTicket ej=new EscogerTicket();
     ej.setBounds(0, 0, 800, 600);
     ej.setVisible(true);
     ej.setResizable(false);
     ej.setLocationRelativeTo(null);
     ej.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/


}
