/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;

import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.text.DecimalFormat;
import javax.swing.*;
/**
 *
 * @author Suplente
 */
public class VentanaPagar extends GeneralPanel {

    JLabel Total, Pagado,Restante;
    float TotalF=0,PagadoF=0,CambiosF=0;
    VentanaPagar(int w,int h,VentanaPrincipal Parent)
    {
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
    }

    void ActualizarTexto()
    {
        DecimalFormat df= new DecimalFormat("0.00");
        Total.setText("Total: "+df.format(TotalF)+"€");
        Pagado.setText("Insertado: "+df.format(PagadoF)+"€");
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

                PagadoF=0;
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
}
