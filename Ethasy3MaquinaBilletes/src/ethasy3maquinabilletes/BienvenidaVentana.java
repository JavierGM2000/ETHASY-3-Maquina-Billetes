/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;

import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Suplente
 */
public class BienvenidaVentana extends GeneralPanel
    {
        ImageIcon fondo;
        JLabel imgfondo;
        JButton Cambiar;
        VentanaPrincipal Padre;

        BienvenidaVentana(int w,int h,VentanaPrincipal Parent)
        {
            Padre=Parent;
            setLayout(null);
            this.setBackground(Color.BLUE);
            this.setPreferredSize(new Dimension(w, h));
            //JButton test= new JButton();
            fondo=ethasy3maquinabilletes.Main.ResizeImage("img\\FondoBienvenida.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, -25, 800, 600);
            add(imgfondo);

            Cambiar = new JButton();
            Cambiar.setBounds(0,0,800,600);
            Cambiar.setVisible(true);
            Cambiar.addActionListener(this);
            Cambiar.setLayout(null);
            Cambiar.setOpaque(false);
            Cambiar.setContentAreaFilled(false);
            add(Cambiar);
        }

    @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Cambiar)
            {
                Padre.PanelChanger(0, 1);
                System.out.println("Salir Bienvenida");
            }
        }

    
}
