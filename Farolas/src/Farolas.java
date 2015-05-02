import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame; 
import javax.swing.JOptionPane;
import javax.swing.JLabel; 
import javax.swing.JButton;
import javax.swing.JComboBox; 

public class Farolas extends JFrame implements ActionListener{
	
	private JLabel label1, label2, label3, label4, label5, label6, label11, label12, label13, label14, label15, label16; //Etiquetas (No están con array por querer usar una numeración más clara para ordenarlas)
	private JComboBox calle[] = new JComboBox[5]; //Comboboxes
	
	JButton farola[][] = new JButton[5][15]; //Botones farolas
	private boolean farolas [][] = new boolean [5][15]; //Variables de control de farolas
	
	private static JButton establecer; //Establecer límites
	
	int limc[] = {0,0,0,0,0}; //Límites por calles
	int enc[] = {0,0,0,0,0}; //Encendidas por calles
	int i, x, y=160, z=340; //Coordenadas para creación de botones
	
	public Farolas() {
        setLayout(null);
        
        //Creacion de etiquetas
        label1=new JLabel("Iturribide");                                
        label1.setBounds(30,50,150,40); 
        add(label1);
        
        label2=new JLabel("Gran Via");
        label2.setBounds(30,100,150,40);
        add(label2);
        
        label3=new JLabel("Licenciado Poza");
        label3.setBounds(30,150,150,40);
        add(label3);
        
        label4=new JLabel("Iparraguirre");
        label4.setBounds(30,200,150,40);
        add(label4);
        
        label5=new JLabel("Colon de Larreategui");
        label5.setBounds(30,250,150,40);
        add(label5);
        
        label6=new JLabel("Numero máximo de farolas encendidas simultaneamente en la calle:");
        label6.setBounds(30,10,500,40);
        label6.setFont(new java.awt.Font("Tahoma",1,12));
        add(label6);
        
        label11=new JLabel("Iturribide");                                
        label11.setBounds(30,350,150,40); 
        add(label11);
        
        label12=new JLabel("Gran Via");
        label12.setBounds(30,400,150,40);
        add(label12);
        
        label13=new JLabel("Licenciado Poza");
        label13.setBounds(30,450,150,40);
        add(label13);
        
        label14=new JLabel("Iparraguirre");
        label14.setBounds(30,500,150,40);
        add(label14);
        
        label15=new JLabel("Colon de Larreategui");
        label15.setBounds(30,550,150,40);
        add(label15);
        
        label16=new JLabel("Encendido/Apagado de farolas independientes:");
        label16.setBounds(30,300,500,40);
        label16.setFont(new java.awt.Font("Tahoma",1,12));
        add(label16);
        
        //Creación de Comboboxes
        calle[0]=new JComboBox();
        calle[0].setBounds(160,50,80,30);
		add(calle[0]);
		
		calle[1]=new JComboBox();
		calle[1].setBounds(160,100,80,30);
		add(calle[1]);
		
		calle[2]=new JComboBox();
		calle[2].setBounds(160,150,80,30);
		add(calle[2]);
		
		calle[3]=new JComboBox();
		calle[3].setBounds(160,200,80,30);
		add(calle[3]);
		
		calle[4]=new JComboBox();
		calle[4].setBounds(160,250,80,30);
		add(calle[4]);
		
		//Contenido de ComboBoxes
		for(int n=0; n<16; n++) 
        {
        calle[0].addItem(n);
        calle[1].addItem(n);
        calle[2].addItem(n);
        calle[3].addItem(n);
        calle[4].addItem(n);
        }     
        
		//Añadido de botón Establecer límites
		establecer=new JButton("Establecer Límites"); 
		establecer.setBounds(300,50,200,230);
        add(establecer);        
        establecer.addActionListener(this);
		
        //Añadido de botones de farolas
        for(i=0; i<5; i++) 
        {
        	for(x=0; x<15; x++)
        	{
        		farola[i][x] = new JButton();
        		farola[i][x].setText(Integer.toString(x));
        		farola[i][x].setBounds(y,z,50,50);
        		add(farola[i][x]);
        		farola[i][x].addActionListener(this);
        		y=y+50;
        	}
        	y=160;
        	z=z+50;
        }       
	}
	
	public void actionPerformed(ActionEvent j)
	{
		Color encendido = new Color(255,0,0);
		
		//Al pulsar establecer límites se modifican los límites por cada calle
		for(int f=0; f<5; f++)
		{
			limc[f]=(int)calle[f].getSelectedItem();
		}
		
		//En cada pulsación de farola se comprueba el estado de la aplicación para decidir la acción
		for(int h=0;h<5;h++)
		{
			for(int t=0;t<15;t++)
			{
				if (j.getSource()==farola[h][t]&&farolas[h][t]==false&&enc[h]<limc[h])
				{
					farola[h][t].setBackground(encendido);
					enc[h]++;
					farolas[h][t]=true;
				}
				else if (j.getSource()==farola[h][t]&&farolas[h][t]==true)
				{
					farola[h][t].setBackground(null);
					enc[h]--;
					farolas[h][t]=false;
				}
				else if (j.getSource()==farola[h][t]&&farolas[h][t]==false&&enc[h]>=limc[h])
				{
					JOptionPane.showMessageDialog(null,"Se ha superado el límite establecido de farolas encendidas"); 
				}
			}
		}	
	}

	public static void main(String[] args) {

		Farolas grafica1 = new Farolas();
		grafica1.setTitle("Control de encendido de farolas");
        grafica1.setBounds(300,100,1000,700);
        grafica1.setVisible(true);
        grafica1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
