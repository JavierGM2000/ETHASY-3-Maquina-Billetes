/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;

import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import javax.swing.*;
/**
 *
 * @author Suplente
 */
public class VentanaPagar extends GeneralPanel {

    JLabel Total, Pagado,Restante;
    double TotalF=0,PagadoF=0,CambiosF=0;
    VentanaPrincipal Padre;
    JButton B200,B100,B50,B20,B10,B5,M2,M1,M50,M20,M10,M5,M02,M01,volver;
    ImageIcon B200I,B100I,B50I,B20I,B10I,B5I,M1I,M50I,M20I,M10I,M5I,M02I,M01I;
    
    VentanaPagar(int w,int h,VentanaPrincipal Parent)
    {
        Padre=Parent;
        this.setLayout(null);
        Total= new JLabel("Total:");
        Total.setBounds(150,50,600,40);
        Total.setFont (Total.getFont ().deriveFont (30f));
        add(Total);

        Pagado= new JLabel("Pagado:");
        Pagado.setBounds(113,90,600,40);
        Pagado.setFont (Pagado.getFont ().deriveFont (30f));
        add(Pagado);

        Restante= new JLabel("Restante:");
        Restante.setBounds(93,130,600,40);
        Restante.setFont (Restante.getFont ().deriveFont (30f));
        add(Restante);
        
            B200I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\200.png");
            B200 = new JButton(B200I);
            B200.setBounds(50,250,90,50);
            add(B200);
            B200.addActionListener(this);

            B100I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\100.png");
            B100 = new JButton(B100I);
            B100.setBounds(50,305,90,50);
            add(B100);
            B100.addActionListener(this);

            B50I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\50.png");
            B50 = new JButton(B50I);
            B50.setBounds(50,360,90,50);
            add(B50);
            B50.addActionListener(this);

            B20I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\20.png");
            B20 = new JButton(B20I);
            B20.setBounds(145,250,90,50);
            add(B20);
            B20.addActionListener(this);

            B10I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\10.png");
            B10 = new JButton(B10I);
            B10.setBounds(145,305,90,50);
            add(B10);
            B10.addActionListener(this);

            B5I=new ImageIcon("src\\ethasy3maquinabilletes\\img\\5.png");
            B5 = new JButton(B5I);
            B5.setBounds(145,360,90,50);
            add(B5);
            B5.addActionListener(this);

            M2= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\M2.png",50,50));
            M2.setBounds(240, 245, 50, 50);
            M2.setLayout(null);
            M2.setOpaque(false);
            M2.setContentAreaFilled(false);
            M2.setBorderPainted(true);
            add(M2);
            M2.addActionListener(this);

            M1= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\M1.png",50,50));
            M1.setBounds(240, 304, 50, 50);
            M1.setLayout(null);
            M1.setOpaque(false);
            M1.setContentAreaFilled(false);
            M1.setBorderPainted(true);
            add(M1);
            M1.addActionListener(this);

            M50= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\M50.png",50,50));
            M50.setBounds(240, 360, 50, 50);
            M50.setLayout(null);
            M50.setOpaque(false);
            M50.setContentAreaFilled(false);
            M50.setBorderPainted(true);
            add(M50);
            M50.addActionListener(this);

            M20= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\M20.png",50,50));
            M20.setBounds(305, 245, 50, 50);
            M20.setLayout(null);
            M20.setOpaque(false);
            M20.setContentAreaFilled(false);
            M20.setBorderPainted(true);
            add(M20);
            M20.addActionListener(this);

            M10= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\M10.png",50,50));
            M10.setBounds(305, 300, 50, 50);
            M10.setLayout(null);
            M10.setOpaque(false);
            M10.setContentAreaFilled(false);
            M10.setBorderPainted(true);
            add(M10);
            M10.addActionListener(this);

            M5= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\C5.png",50,50));
            M5.setBounds(307, 360, 50, 50);
            M5.setLayout(null);
            M5.setOpaque(false);
            M5.setContentAreaFilled(false);
            M5.setBorderPainted(true);
            add(M5);
            M5.addActionListener(this);

            M02= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\c2.png",50,50));
            M02.setBounds(373, 360, 50, 50);
            M02.setLayout(null);
            M02.setOpaque(false);
            M02.setContentAreaFilled(false);
            M02.setBorderPainted(true);
            add(M02);
            M02.addActionListener(this);

            M01= new JButton(ethasy3maquinabilletes.Main.ResizeImage("src\\ethasy3maquinabilletes\\img\\C1.png",50,50));
            M01.setBounds(373, 303, 50, 50);
            M01.setLayout(null);
            M01.setOpaque(false);
            M01.setContentAreaFilled(false);
            M01.setBorderPainted(true);
            add(M01);
            M01.addActionListener(this);
            
            volver= new JButton("Volver");
            volver.setBounds(50,450,100,50);
            add(volver);
            volver.addActionListener(this);
            
    }

    @Override
    void ClearText() {
        TotalF=Padre.currentBil.toDouble();
        Total.setText("Total: "+Padre.currentBil.toDouble());
        PagadoF=0;
        Pagado.setText("Pagado: 0.00€");
        CambiosF=0;
        Restante.setText("Restanet: 0.00€");
    }

    
    
    void ActualizarTexto()
    {
        DecimalFormat df= new DecimalFormat("0.00");
        Total.setText("Total: "+df.format(TotalF)+"€");
        Pagado.setText("Insertado: "+df.format(PagadoF)+"€");
        CambiosF=TotalF-PagadoF;
        Restante.setText("Insertado: "+df.format(CambiosF)+"€");
    }
    void CheckPago()
        {
            if(PagadoF>=TotalF)
            {
                if(PagadoF-TotalF<0.009)
                {
                    JOptionPane.showMessageDialog(null, "Ha insertado justo. Gracias por su compra!");
                }
                else
                {
                    CambioCents(PagadoF,TotalF);
                }
                Padre.currentBil.InserEnBaseDatos();
                PagadoF=0;
                Padre.PanelChanger(6, 3);
            }
        }

    public void CambioCents(double Recibido, double Total)
    {


        String OutPut="";
        int Eur = (int)(Recibido*100) - (int)(Total*100);
        int EurCopy = Eur;
        int Bil500;
        int Bil200;
        int Bil100;
        int Bil50;
        int Bil20;
        int Bil10;
        int Bil5;
        int Bil2;
        int Bil1;
        int Cent50;
        int Cent20;
        int Cent10;
        int Cent5;
        int Cent2;
        int Cent1;
        for(Bil500 = 0; 500*100 <= Eur; Bil500++ )
        {
            Eur -= 500*100;
        }
        for(Bil200 = 0; 200*100 <= Eur; Bil200++ )
        {
            Eur -= 200*100;
        }
        for(Bil100 = 0; 100*100 <= Eur; Bil100++ )
        {
            Eur -= 100*100;
        }
        for(Bil50 = 0; 50*100 <= Eur; Bil50++ )
        {
            Eur -= 50*100;
        }
        for(Bil20 = 0; 20*100 <= Eur; Bil20++ )
        {
            Eur -= 20*100;
        }
        for(Bil10 = 0; 10*100 <= Eur; Bil10++ )
        {
            Eur -= 10*100;
        }
        for(Bil5 = 0; 5*100 <= Eur; Bil5++ )
        {
            Eur -= 5*100;
        }
        for(Bil2 = 0; 2*100 <= Eur; Bil2++ )
        {
            Eur -= 2*100;
        }
        for(Bil1 = 0; 1*100 <= Eur; Bil1++ )
        {
            Eur -= 1*100;
        }
        for(Cent50 = 0; 0.5*100 <= Eur; Cent50++ )
        {
            Eur -= 0.5*100;
        }
        for(Cent20 = 0; 0.2*100 <= Eur; Cent20++ )
        {
            Eur -= 0.2*100;
        }
        for(Cent10 = 0; 0.1*100 <= Eur; Cent10++ )
        {
            Eur -= 0.1*100;
        }
        for(Cent5 = 0; 0.05*100 <= Eur; Cent5++ )
        {
            Eur -= 0.05*100;
        }
        for(Cent2 = 0; 0.02*100 <= Eur; Cent2++ )
        {
            Eur -= 0.02*100;
        }
        for(Cent1 = 0; 0.01*100 <= Eur; Cent1++ )
        {
            Eur -= 0.01*100;
        }
        OutPut+="Hay que devolver "+ (double)EurCopy/100 + "\u20AC\n";
        if((int)Bil200!=0)
            OutPut+=((int)Bil200 + " billetes de 200 euros\n");
        if((int)Bil100!=0)
            OutPut+=((int)Bil100 + " billetes de 100 euros\n");
        if((int)Bil50!=0)
        OutPut+=((int)Bil50 + " billetes de 50 euros\n");
        if((int)Bil20!=0)
        OutPut+=((int)Bil20 + " billetes de 20 euros\n");
        if((int)Bil10!=0)
        OutPut+=((int)Bil10 + " billetes de 10 euros\n");
        if((int)Bil5!=0)
        OutPut+=((int)Bil5 + " billetes de 5 euros\n");
        if((int)Bil2!=0)
        OutPut+=((int)Bil2 + " monedas de 2 euros\n");
        if((int)Bil1!=0)
        OutPut+=((int)Bil1 + " monedas de 1 euros\n");
        if((int)Cent50!=0)
        OutPut+=((int)Cent50 + " monedas de 50 centimos\n");
        if((int)Cent20!=0)
        OutPut+=((int)Cent20 + " monedas de 20 centimos\n");
        if((int)Cent10!=0)
        OutPut+=((int)Cent10 + " monedas de 10 centimos\n");
        if((int)Cent5!=0)
        OutPut+=((int)Cent5 + " monedas de 5 centimos\n");
        if((int)Cent2!=0)
        OutPut+=((int)Cent2 + " monedas de 2 centimos\n");
        if((int)Cent1!=0)
        OutPut+=((int)Cent1 + " monedas de 1 centimos\n");
        OutPut+="Gracias por su compra!";
        JOptionPane.showMessageDialog(null, OutPut);
    }
    
    public void actionPerformed(ActionEvent e) {
            

            if(e.getSource()==B200)
            {
                PagadoF+=200;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==B100)
            {
                PagadoF+=100;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==B50)
            {
                PagadoF+=50;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==B20)
            {
                PagadoF+=20;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==B10)
            {
                PagadoF+=10;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==B5)
            {
                PagadoF+=5;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M2)
            {
                PagadoF+=2;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M1)
            {
                PagadoF+=1;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M50)
            {
                PagadoF+=0.5;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M20)
            {
                PagadoF+=0.2;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M10)
            {
                PagadoF+=0.1;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M5)
            {
                PagadoF+=0.05;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M02)
            {
                PagadoF+=0.02;
                ActualizarTexto();
                CheckPago();
            }
            if(e.getSource()==M01)
            {
                PagadoF+=0.01;
                ActualizarTexto();
                CheckPago();
            }
        }
}
