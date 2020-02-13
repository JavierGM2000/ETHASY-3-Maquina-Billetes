
package ethasy3maquinabilletes;

import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class SeleccionarOperacion extends GeneralPanel{
    private JLabel imgfondo;
     private JButton consultar,comprar,logout;
     private JLabel seleccioneOperacion,Bienvenido;
      private VentanaPrincipal Padre;
     public SeleccionarOperacion(int w,int h,VentanaPrincipal Parent){
             
          setLayout(null);
         Padre = Parent;
          //label
           seleccioneOperacion= new JLabel("Seleccione la operación que desee hacer:");
           seleccioneOperacion.setBounds(90,-10,650,250);
           seleccioneOperacion.setFont (seleccioneOperacion.getFont ().deriveFont (30f));
           add(seleccioneOperacion);

           Bienvenido= new JLabel("Bienvenido");
           Bienvenido.setBounds(10,10,600,40);
           Bienvenido.setFont (Bienvenido.getFont ().deriveFont (30f));
           add(Bienvenido);
           
          //Consultar
           consultar=new JButton("Consultar tus tickets");
           consultar.setBounds(90,170,250,250);
           consultar.setFont (consultar.getFont ().deriveFont (20f));
           consultar.addActionListener(this);
           add(consultar);
           
          //Comprar
           comprar=new JButton("Comprar ticket");
           comprar.setBounds(440,170,250,250);
           comprar.setFont (comprar.getFont ().deriveFont (20f));
           comprar.addActionListener(this);
           add(comprar);
           
           //logout
           logout=new JButton("Logout");
           logout.setBounds(600,20,120,50);
           logout.addActionListener(this);
           add(logout);
           
            ImageIcon fondo=ethasy3maquinabilletes.Main.ResizeImage("img\\FondoSeleccion.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, 0, 800, 600);
            add(imgfondo);
    }

     public void setBienvenida(Cliente Client)
    {
        Bienvenido.setText("Bienvenido:"+Client.Nombre+" "+Client.Apellido1+" "+ Client.Apellido2);
    }

    @Override
     public void actionPerformed(ActionEvent e){
        if(e.getSource()==logout)
        {
            Padre.PanelChanger(3, 1);
        }
        if(e.getSource()==comprar)
        {
            Padre.PanelChanger(3,4);
        }

     }
    }


