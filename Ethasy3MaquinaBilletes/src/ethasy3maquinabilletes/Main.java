
package ethasy3maquinabilletes;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Main extends JFrame{
   
     private JButton consultar,comprar,logout;
     private JLabel seleccioneOperacion;
             
     public Main(){
             
          setLayout(null);
         
          //label
           seleccioneOperacion= new JLabel("Seleccione la operación que desee hacer:");
           seleccioneOperacion.setBounds(90,-10,650,250);
           seleccioneOperacion.setFont (seleccioneOperacion.getFont ().deriveFont (30f));
           add(seleccioneOperacion);
           
          //Consultar
           consultar=new JButton("Consultar tus tickets");
           consultar.setBounds(90,170,250,250);
           consultar.setFont (consultar.getFont ().deriveFont (20f));
           add(consultar);
           
          //Comprar
           comprar=new JButton("Comprar ticket");
           comprar.setBounds(440,170,250,250);
           comprar.setFont (comprar.getFont ().deriveFont (20f));
           add(comprar);
           
           //logout
           logout=new JButton("Logout");
           logout.setBounds(550,20,120,50);
           add(logout);
           
           
    }
     
     public static void main(String[] ar){
           
            Main seleccion=new Main();
            seleccion.setBounds(0, 0, 800, 600);
            seleccion.setVisible(true);
            seleccion.setTitle("Selección de operación");
        }

   
}
