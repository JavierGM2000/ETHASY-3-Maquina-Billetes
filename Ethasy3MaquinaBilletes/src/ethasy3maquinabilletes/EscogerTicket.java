
package ethasy3maquinabilletes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ItemSelectable;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class EscogerTicket extends JFrame implements ActionListener{
    private JComboBox lineaComboBox,horaComboBox;
    private JLabel lineaLabel,salidaLabel,llegadaLabel,diaLabel,horaLabel;
    private JTextArea diaTextArea;
    private JScrollPane salidaScroll,llegadaScroll;
    private JButton volver;
    private JPanel SalidasPanel,LlegadasPanel;
    
    ArrayList<JButton> BotonesSalida = new ArrayList();
    ArrayList<JButton> BotonesLlegada = new ArrayList();
    
        String url="jdbc:mysql://localhost:3306/reto3db";
        Connection mycon;
 
         EscogerTicket() throws SQLException, ClassNotFoundException
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
            }
          
          add(lineaComboBox);
          lineaComboBox.addActionListener(this);
          
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
          llegadaLabel.setBounds(570, 100, 200, 200);
          llegadaLabel.setFont (llegadaLabel.getFont ().deriveFont (20f));
          add(llegadaLabel);  
          
          llegadaScroll = new JScrollPane(LlegadasPanel);
          llegadaScroll.setBounds(570, 230, 200, 250);
          llegadaScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
          add(llegadaScroll);
          
          diaTextArea= new JTextArea("");
          diaTextArea.setBounds(300, 230, 200, 250);
          add(diaTextArea); 
          diaTextArea.setEditable(false);
          
          
          diaLabel= new JLabel("Día:");
          diaLabel.setBounds(300, 100, 200, 200);
          diaLabel.setFont (diaLabel.getFont ().deriveFont (20f));
          add(diaLabel); 
          
          volver=new JButton("Volver");
          volver.setBounds(670, 500, 100, 50);
          add(volver);
               
          horaComboBox= new JComboBox();
          horaComboBox.setBounds(370, 510, 100, 30);
          horaComboBox.addItem("8:00");
          horaComboBox.addItem("9:00");
          horaComboBox.addItem("10:00");
          horaComboBox.addItem("11:00");
          horaComboBox.addItem("12:00");
          horaComboBox.addItem("13:00");
          horaComboBox.addItem("14:00");
          horaComboBox.addItem("15:00");
          horaComboBox.addItem("16:00");
          horaComboBox.addItem("17:00");
          horaComboBox.addItem("18:00");
          horaComboBox.addItem("19:00");
          horaComboBox.addItem("20:00");
          horaComboBox.addItem("21:00");
          horaComboBox.addItem("22:00");
          horaComboBox.addItem("23:00");
          horaComboBox.addItem("24:00");
          add(horaComboBox);
          
          horaLabel= new JLabel("Hora:");
          horaLabel.setBounds(300, 510, 100, 30);
          horaLabel.setFont (horaLabel.getFont ().deriveFont (20f));
          add(horaLabel); 
          
          
          
          
     }
     
     
      public void actionPerformed(ActionEvent e){
      
        if(e.getSource()==lineaComboBox){ 
        try {
        mycon = DriverManager.getConnection(url, "root", "");
        Statement mysts= mycon.createStatement();  
        String selectSalidas="SELECT p.Nombre\n" +
        "FROM parada p, linea l, linea_parada lp\n" +
        "WHERE p.Cod_Parada=lp.Cod_Parada AND l.Cod_Linea=lp.Cod_Linea\n" +
        "AND l.Nombre=\'"+lineaComboBox.getSelectedItem()+"\'ORDER BY lp.Num_Parada";
        
        ResultSet resSelectSalidas = mysts.executeQuery(selectSalidas);
       
        
        BotonesSalida.clear();
        SalidasPanel.removeAll();
        
        BotonesLlegada.clear();
        LlegadasPanel.removeAll();
        
        int i=0;
        
        
        while(resSelectSalidas.next())
            {
            BotonesSalida.add(new JButton(resSelectSalidas.getString("Nombre")));
            BotonesSalida.get(i).setBounds(0, 20*i, 200, 20);
            SalidasPanel.add(BotonesSalida.get(i));
            BotonesSalida.get(i).addActionListener(this);
            BotonesSalida.get(i).setBackground(null);
            
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
               
               for(int y=0;y<BotonesSalida.size();y++){
                   
                   BotonesSalida.get(y).setBackground(null);
                  
               }
               BotonesSalida.get(x).setBackground(Color.yellow);
                 
             }
        
         }
         
          for(int x=0;x<BotonesLlegada.size();x++){
               
           if(e.getSource()==BotonesLlegada.get(x)){
               
               for(int y=0;y<BotonesLlegada.size();y++){
                   BotonesLlegada.get(y).setBackground(null);
               }
               BotonesLlegada.get(x).setBackground(Color.yellow);
                 
             }
        
         }
          
      }

    
    
    public static void main(String[]args) throws SQLException, ClassNotFoundException {
    EscogerTicket ej=new EscogerTicket();
     ej.setBounds(0, 0, 800, 600);
     ej.setVisible(true);
     ej.setResizable(false);
     ej.setLocationRelativeTo(null);
    }


}
