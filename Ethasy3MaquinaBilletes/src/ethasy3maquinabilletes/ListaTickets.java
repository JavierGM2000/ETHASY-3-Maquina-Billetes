/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;
import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Tarde
 */
public class ListaTickets extends GeneralPanel{


    private JButton vol;
    private JLabel lista;
    private JTextField P;
    private JTextArea inf;
    private JScrollPane scroll;
    private VentanaPrincipal Padre;
    private ArrayList<Billete> ListBilletes;
    private final JLabel imgfondo;


    public ListaTickets(int w,int h,VentanaPrincipal Parent){
        setLayout(null);
        Padre=Parent;
        lista=new JLabel("Lista Tickets");
        lista.setBounds(40,-10,300,100);
        lista.setFont(lista.getFont().deriveFont(30f));
        add(lista);

        inf=new JTextArea("ABCD");
        inf.setBounds(0,0,500,340);
        inf.setFont(inf.getFont().deriveFont(18f));
        inf.setBorder(new LineBorder(Color.BLACK));
        inf.setLineWrap(true);
        inf.setEditable(false);
        //add(inf);
        
        scroll = new JScrollPane(inf);
        scroll.setBounds(150,80,500,340);
        add(scroll);

        vol=new JButton("Volver");
        vol.setBounds(550,10,100,50);
        add(vol);
        vol.addActionListener(this);

         ImageIcon fondo=ethasy3maquinabilletes.Main.ResizeImage("img\\FondoSeleccion.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, 0, 800, 600);
            add(imgfondo);

    }

    @Override
    void ClearText() {
        try {
            String out="";
            ListBilletes=Billete.GetBilletes(Padre,Padre.getCliente().DNI);
            for(int i=0;i<ListBilletes.size();i++)
            {
                out+=ListBilletes.get(i).toString();
                out+="\n-------------------------\n";
            }
            inf.setText(out);
            
            inf.setPreferredSize(new Dimension(500,280*ListBilletes.size()));
            inf.updateUI();
            inf.repaint();
            scroll.updateUI();
        } catch (SQLException ex) {
            Logger.getLogger(ListaTickets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==vol)
        {
            Padre.PanelChanger(7, 3);
        }
    }

}
