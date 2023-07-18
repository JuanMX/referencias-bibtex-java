import javax.swing.JOptionPane; 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.*;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager.*;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.io.*;


public class referenciasBibTex2 extends JFrame{
	
	private int largoTextI = 18, largoTextD = 19;

	private JButton masLibroB, masArticuloB, guardarB;
	private FlowLayout esquema;
	private Container contenedor;
	
	private GridLayout gridCampos, gridCamposD;
	private GridBagLayout gridCentral;
	private GridBagConstraints restricciones;

	private JPanel p1, panelDerecha, panelCentral;
	
	private JLabel titleLabelL, authorLabelL, publisherLabelL, yearLabelL, etiquetaLabelL;
	private JLabel titleLabelA, authorLabelA, journalLabelA, yearLabelA, numberLabelA, pagesLabelA, volumeLabelA, publisherLabelA, etiquetaLabelA;
	
	private JTextField titleTextL, authorTextL, publisherTextL, yearTextL, etiquetaTextL;
	private JTextField titleTextA, authorTextA, journalTextA, yearTextA, numberTextA, pagesTextA, volumeTextA, publisherTextA, etiquetaTextA;
	
	private JTextArea referenciasText;
	
	private String titleL, authorL, publisherL, yearL, etiquetaL;
	private String titleA, authorA, journalA, yearA, numberA, pagesA, volumeA, publisherA, etiquetaA;
	
	
	public referenciasBibTex2(){
	
		super("Generador de referencias BibTex v1.1");
		
		//cuadriculas (grid) de los campos de texto y etiquetas
		gridCampos = new GridLayout(6, 2, 1, 1);
		gridCamposD = new GridLayout(10, 2, 1, 1);
		
		//los 3 botones con texto de ayuda
		masLibroB = new JButton("Agregar Libro");
		masLibroB.setToolTipText("Si dejas los campos vacios agrega una plantilla sin llenar");
		masArticuloB = new JButton ("Agregar Articulo");
		masArticuloB.setToolTipText("Si dejas los campos vacios agrega una plantilla sin llenar");
		guardarB = new JButton ("Guardar en un archivo");
		
		//campos de texto y etiquetas para capturar datos de libros
		etiquetaTextL = new JTextField( largoTextI );
		etiquetaTextL.setToolTipText("Es la etiqueta que usa LaTeX para citar. Ejemplo \\cite{Etiqueta}");
		titleTextL = new JTextField( largoTextI );
		authorTextL = new JTextField( largoTextI ); 
		publisherTextL = new JTextField( largoTextI );
		yearTextL = new JTextField( largoTextI );
		
		etiquetaLabelL = new JLabel ("Etiqueta");
		titleLabelL = new JLabel ("Title"); 
		authorLabelL = new JLabel ("Author"); 
		publisherLabelL = new JLabel ("Publisher");
		yearLabelL = new JLabel ("Year");
		
		//campos de texto y etiquetas para capturar datos de articulos
		etiquetaTextA = new JTextField( largoTextD );
		etiquetaTextA.setToolTipText("Es la etiqueta que usa LaTeX para citar. Ejemplo \\cite{Etiqueta}");
		titleTextA = new JTextField( largoTextD ); 
		authorTextA = new JTextField( largoTextD ); 
		journalTextA = new JTextField( largoTextD ); 
		yearTextA = new JTextField( largoTextD );
		numberTextA = new JTextField( largoTextD );
		pagesTextA = new JTextField( largoTextD );
		volumeTextA = new JTextField( largoTextD );
		publisherTextA = new JTextField( largoTextD );
		
		etiquetaLabelA = new JLabel ("Etiqueta");
		titleLabelA = new JLabel ("Title");
		authorLabelA = new JLabel ("Author");
		journalLabelA = new JLabel ("Journal"); 
		yearLabelA = new JLabel ("Year");
		numberLabelA = new JLabel ("Number");
		pagesLabelA = new JLabel ("Pages XXX--YYY");
		volumeLabelA = new JLabel ("Volume");
		publisherLabelA = new JLabel ("Publisher");
		
		//area de texto donde se agragan las referencias bibliograficas, esta deshabilitado editar su contenido con entrada de usuario
		referenciasText = new JTextArea( 21, 41 );
		referenciasText.setEditable( false );
		
		//grid bag central para acomodar el area de texto y el boton de guardar archivo
		gridCentral = new GridBagLayout();

        //3 paneles diferentes. En p1 se agregan los campos para capturar datos de libros, panelDerecha para articulos y panelCentral el area de texto y boton guardar
		p1 = new JPanel();
        p1.setLayout(gridCampos);
        
        panelDerecha = new JPanel();
        panelDerecha.setLayout(gridCamposD);
        
        panelCentral = new JPanel(); 
        panelCentral.setLayout(gridCentral);
        restricciones = new GridBagConstraints();

		restricciones.fill = GridBagConstraints.BOTH;
		agregarComponente( new JScrollPane(referenciasText), 0, 0, 100, 99 ); //con este metodo se agrega el area de texto con un scroll al panel
		
		guardarB.setBackground(new Color( 182, 239, 226 )); //el cambio de color es para que se distinga este boton de los otros dos
        restricciones.fill = GridBagConstraints.CENTER;
        agregarComponente( guardarB, 100, 50, 400, 1 ); //con este metodo se agrega el boton de guardar
		
		/*Por si se requiere cambiar el color de los botones para agregar libros y articulos*/
		//masLibroB.setBackground(new Color( 246, 182, 119 ));
		//masArticuloB.setBackground(new Color( 246, 182, 119 ));
		
		//agregar los campos de texto y etiquetas a los paneles que corresponden	
      	p1.add(etiquetaLabelL);
      	p1.add(etiquetaTextL);
      	
      	p1.add( titleLabelL);
      	p1.add( titleTextL );
      	
      	p1.add( authorLabelL );
      	p1.add( authorTextL );
      	
      	p1.add( publisherLabelL );
      	p1.add( publisherTextL );
      	
      	p1.add( yearLabelL );
      	p1.add( yearTextL );
      	
      	p1.add(masLibroB);
      	
      	
      	
      	panelDerecha.add( etiquetaLabelA );
      	panelDerecha.add( etiquetaTextA );
      	
      	panelDerecha.add( titleLabelA );
      	panelDerecha.add( titleTextA );
      	
      	panelDerecha.add( authorLabelA );
      	panelDerecha.add( authorTextA );
      	
      	panelDerecha.add( journalLabelA );
      	panelDerecha.add( journalTextA );
      	
      	panelDerecha.add( yearLabelA );
      	panelDerecha.add( yearTextA );
      	
      	panelDerecha.add( numberLabelA );
      	panelDerecha.add( numberTextA );
      	
      	panelDerecha.add( pagesLabelA );
      	panelDerecha.add( pagesTextA );
      	
      	panelDerecha.add( volumeLabelA );
      	panelDerecha.add( volumeTextA );
      	
      	panelDerecha.add( publisherLabelA );
      	panelDerecha.add( publisherTextA );
      	
		
		panelDerecha.add(masArticuloB);
		
		//asi estan acomodados los paneles en todo el frame
		add( p1, BorderLayout.WEST );
		add( panelCentral, BorderLayout.CENTER);
		add( panelDerecha, BorderLayout.EAST );
		
		//crea un objeto de una clase interna anonima que se agraga como escuhador a los botones, esta clase esta en este archivo	
        ManejadorBoton manejadorBotones = new ManejadorBoton();
        masLibroB.addActionListener(manejadorBotones);
        masArticuloB.addActionListener(manejadorBotones);
        guardarB.addActionListener(manejadorBotones);

		Toolkit toolkitIcono = Toolkit.getDefaultToolkit();
		Image ventanaIcono = toolkitIcono.getImage("./Imagenes/icon.png");
		setIconImage(ventanaIcono);
                
	}
	//metodo para agregar componentes a un grid bag, se le pasa como parametro las restricciones de cada componente
	private void agregarComponente( Component componente, int fila, int columna, int anchura, int altura )
	{
		restricciones.gridx = columna; // establece gridx
		restricciones.gridy = fila; // establece gridy
		restricciones.gridwidth = anchura; // establece gridwidth
		restricciones.gridheight = altura; // establece gridheight
		gridCentral.setConstraints( componente, restricciones ); // establece restricciones
		panelCentral.add( componente ); // agrega el componente
	} 

	public static void main( String args[] )
	{
		/*Este try-catch es para poner a la interfaz grafia una "skin" de "nimbus" (hace que se vea "bonita" la interfaz).
		Esta manera de hacerlo se encuentra en la pagina de oracle
		https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
		*/
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
				    UIManager.setLookAndFeel(info.getClassName());
				    break;
				}
			}
		} 
		catch (Exception e) {
		// If Nimbus is not available, you can set the GUI to another look and feel.
		}

		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = pantalla.getScreenSize();
		int anchoPantalla = tamanoPantalla.width;
		int altoPantalla = tamanoPantalla.height / 2;
		referenciasBibTex2 sistema = new referenciasBibTex2();
		sistema.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//sistema.setBackground(new Color(242,241,240));
		sistema.setSize( anchoPantalla, altoPantalla );
		sistema.setVisible( true );
		sistema.setResizable( true );/*Esta parte desactiva la opcion de redimencionar la ventana*/
		sistema.setLocationRelativeTo(null);
		
	}
    private class ManejadorBoton implements ActionListener{
	
        public void actionPerformed( ActionEvent evento ){
            
            //este bloque es lo que hace si se presiona el boton que agrega libros
            if (evento.getSource() == masLibroB){
            	
            	/*En este bolque se puede poner otra condicional si un campo en especifico esta vacio o usar expresiones regulares*/
            	etiquetaL = etiquetaTextL.getText();
            	titleL = titleTextL.getText();
            	authorL = authorTextL.getText(); 
            	publisherL = publisherTextL.getText(); 
            	yearL = yearTextL.getText();
            	
            	//esta es la "plantilla" de un referencia de libros que se agrega al area de texto 
            	referenciasText.append("@Book{"+etiquetaL+",\n");
				referenciasText.append("     Title\t= {"+titleL+"},\n");
				referenciasText.append("     Author\t= {"+authorL+"},\n"); 
				referenciasText.append("     Publisher\t= {"+publisherL+"},\n");
				referenciasText.append("     Year\t= {"+yearL+"},\n");
				referenciasText.append("}\n\n");
				
				//cuando se presiona el boton de agregar libros y se agrega al area de texto, los campos de donde se toma la informacion se vacian 
                etiquetaTextL.setText("");
                titleTextL.setText("");
            	authorTextL.setText(""); 
            	publisherTextL.setText(""); 
            	yearTextL.setText("");

            }
            //este bloque es lo que hace si se presiona el boton que agrega articulos
            else if (evento.getSource() == masArticuloB){
            	
            	/*En este bolque se puede poner otra condicional si un campo en especifico esta vacio o usar expresiones regulares*/
            	etiquetaA = etiquetaTextA.getText();
            	titleA = titleTextA.getText();
            	authorA = authorTextA.getText();
            	journalA = journalTextA.getText();
            	yearA = yearTextA.getText();
            	numberA = numberTextA.getText();
            	pagesA = pagesTextA.getText();
            	volumeA = volumeTextA.getText();
            	publisherA = publisherTextA.getText();
            	etiquetaA = etiquetaTextA.getText();
            	
            	//esta es la "plantilla" de un referencia de libros que se agrega al area de texto 
            	referenciasText.append("@Article{"+etiquetaA+",\n");
				referenciasText.append("     Title\t= {"+titleA+"},\n");
				referenciasText.append("     Author\t= {"+authorA+"},\n"); 
				referenciasText.append("     Journal\t= {"+journalA+"},\n");
				referenciasText.append("     Year\t= {"+yearA+"},\n");
				referenciasText.append("     Number\t= {"+numberA+"},\n");
				referenciasText.append("     Pages\t= {"+pagesA+"},\n");
				referenciasText.append("     Volume\t= {"+volumeA+"},\n\n");
				referenciasText.append("     Publisher\t= {"+publisherA+"},\n");
				referenciasText.append("}\n\n");
            	
                //cuando se presiona el boton de agregar articulos y se agrega al area de texto, los campos de donde se toma la informacion se vacian
                etiquetaTextA.setText("");
                titleTextA.setText("");
                authorTextA.setText("");
                journalTextA.setText("");
                yearTextA.setText("");
				numberTextA.setText("");
				pagesTextA.setText("");
				volumeTextA.setText("");
				publisherTextA.setText("");
				
            }
            
            //este bloque es lo que hace si se presiona el boton que guarda en un archivo
            else if (evento.getSource() == guardarB){
				
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Especifica donde guardar");//titulo de ventana del JFileChooser
				
				/*Permite ver archivos con extencion especifica*/
				//fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt"));
				fileChooser.setFileFilter(new FileNameExtensionFilter("BibTeX (*.bib)","bib"));
				
				int seleccion = fileChooser.showSaveDialog(null);
				 
				/*Tal vez hay mas maneras de escribir archivos pero aqui se hace con un File, FileWriter, BufferedWriter y PrintWriter*/
				if (seleccion == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser.getSelectedFile();
					File f;
					String rutaAbsolutaArchivo = fileToSave.getAbsolutePath()  + ".bib";
            		f = new File(rutaAbsolutaArchivo);
            		
            		try{
						FileWriter w = new FileWriter(f/*,true*/);//si se descomenta el "true" lo que hace es: si ya existe un archivo y se quiere escribir en el, escribe al final del archivo.
						BufferedWriter bw = new BufferedWriter(w);
						PrintWriter wr = new PrintWriter(bw);  
					
						wr.append( referenciasText.getText() );
						
						wr.close();
						bw.close();
						
						referenciasText.setText("");//al final de que se guarda el archivo se vacia el contenido del area de texto

						JOptionPane.showMessageDialog( null, rutaAbsolutaArchivo, "Guardo en:", JOptionPane.PLAIN_MESSAGE );
					}catch(IOException e){};
            	
				}
            }
                
        }
    }
}
