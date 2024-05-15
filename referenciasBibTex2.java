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
import javax.swing.Icon;
import javax.swing.ImageIcon;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JRadioButtonMenuItem;
import java.util.ArrayList;
import javax.swing.ButtonGroup;

import java.text.SimpleDateFormat;  
import java.util.Date;  

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class referenciasBibTex2 extends JFrame{
	
	private int largoTextI = 18, largoTextD = 19;

	private JButton masLibroB, masArticuloB, guardarB;
	private FlowLayout esquema;
	private Container contenedor;
	
	private GridLayout gridCampos, gridCamposD;
	//private GridBagLayout gridCentral;
	private BorderLayout gridCentral;
	private GridBagConstraints restricciones;

	private JPanel p1, panelDerecha, panelCentral;
	
	private JLabel titleLabelL, authorLabelL, publisherLabelL, yearLabelL, etiquetaLabelL;
	private JLabel titleLabelA, authorLabelA, journalLabelA, yearLabelA, numberLabelA, pagesLabelA, volumeLabelA, publisherLabelA, etiquetaLabelA;
	
	private JTextField titleTextL, authorTextL, publisherTextL, yearTextL, etiquetaTextL;
	private JTextField titleTextA, authorTextA, journalTextA, yearTextA, numberTextA, pagesTextA, volumeTextA, publisherTextA, etiquetaTextA;
	
	private JTextArea referenciasText;
	
	private String titleL, authorL, publisherL, yearL, etiquetaL;
	private String titleA, authorA, journalA, yearA, numberA, pagesA, volumeA, publisherA, etiquetaA;
	
	private Icon iconoGuardar, iconoLibro, iconoArticulo, iconoFolder, iconoInformation, iconojuanmx, iconoEncontradoEscribir, iconoError;

	private JMenuBar barMenu;
	private JMenu menuAyuda;
	private JMenuItem menuItemAcercaDe;

	private JMenu menuLookAndFeel;
	private JRadioButtonMenuItem lookAndFeels[];
	private ButtonGroup lookAndFeelsButtonGroup;

	private static final String tituloVentanaPrincipal = "Generador de referencias BibTeX v1.5";
	
	Image ventanaIcono;
	static ArchivoDeConfiguracion config = new ArchivoDeConfiguracion();
	
	public referenciasBibTex2(String configLookAndFeel, int altoPantalla, int anchoPantalla){
	
		super(tituloVentanaPrincipal);
		
		Toolkit toolkitIcono = Toolkit.getDefaultToolkit();
		
		iconoFolder = new ImageIcon(toolkitIcono.getImage("./img/document-open.png"));
		iconojuanmx = new ImageIcon(toolkitIcono.getImage("./img/icon.png"));
		iconoInformation = new ImageIcon(toolkitIcono.getImage("./img/dialog-information.png"));
		iconoEncontradoEscribir = new ImageIcon(toolkitIcono.getImage("./img/edit-find-replace.png"));
		iconoError = new ImageIcon(toolkitIcono.getImage("./img/dialog-error.png"));
		
		//cuadriculas (grid) de los campos de texto y etiquetas
		gridCampos = new GridLayout(6, 2, 1, 1);
		gridCamposD = new GridLayout(10, 2, 1, 1);
		
		//los 3 botones con texto de ayuda
		iconoLibro = new ImageIcon(toolkitIcono.getImage("./img/x-office-address-book.png")); 
		masLibroB = new JButton("Agregar Libro", iconoLibro);
		masLibroB.setToolTipText("Si dejas los campos vacios agrega una plantilla sin llenar");
		
		iconoArticulo = new ImageIcon(toolkitIcono.getImage("./img/x-office-document.png"));
		masArticuloB = new JButton ("Agregar Articulo", iconoArticulo);
		masArticuloB.setToolTipText("Si dejas los campos vacios agrega una plantilla sin llenar");
		
		iconoGuardar = new ImageIcon(toolkitIcono.getImage("./img/document-save-as.png"));
		guardarB = new JButton ("Guardar en un archivo .bib", iconoGuardar);
		guardarB.setBorder(new LineBorder(new Color(0,255,0), 1));
		
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
		referenciasText = new JTextArea( 21, 21 );
		referenciasText.setEditable( false );
		
		//grid bag central para acomodar el area de texto y el boton de guardar archivo
		//gridCentral = new GridBagLayout();
		gridCentral = new BorderLayout(10,10);

        //3 paneles diferentes. En p1 se agregan los campos para capturar datos de libros, panelDerecha para articulos y panelCentral el area de texto y boton guardar
		p1 = new JPanel();
        p1.setLayout(gridCampos);
		p1.setBorder(new EmptyBorder(0, 10, 0, 50));
        
        panelDerecha = new JPanel();
        panelDerecha.setLayout(gridCamposD);
        panelDerecha.setBorder(new EmptyBorder(0, 50, 0, 10));

        panelCentral = new JPanel(); 
        panelCentral.setLayout(gridCentral);
		panelCentral.setBorder(new EmptyBorder(10, 20, 10, 20));
		panelCentral.add(new JScrollPane(referenciasText), BorderLayout.CENTER);
		panelCentral.add(guardarB, BorderLayout.SOUTH);

		/*
        restricciones = new GridBagConstraints();

		restricciones.fill = GridBagConstraints.BOTH;
		
		agregarComponente( new JScrollPane(referenciasText), 0, 0, 3, 2 ); //con este metodo se agrega el area de texto con un scroll al panel
		
        restricciones.fill = GridBagConstraints.CENTER;
        agregarComponente( guardarB, 2, 1, 3, 1 ); //con este metodo se agrega el boton de guardar
		*/
		/*Por si se requiere cambiar el color de los botones para agregar libros y articulos*/
		//masLibroB.setBackground(new Color( 246, 182, 119 ));
		//masArticuloB.setBackground(new Color( 246, 182, 119 ));

		//menu bar
		barMenu = new JMenuBar();
		setJMenuBar(barMenu);


		menuLookAndFeel = new JMenu("Look and feel");
		barMenu.add(menuLookAndFeel);

		// INICIO Obtiene look and feels y los guarda en un Array
		// Inicializa las listas dinamicas de look and feel

		ArrayList<String> lookAndFeelNames = new ArrayList<String>();
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            lookAndFeelNames.add(info.getName());
		}
		
		lookAndFeels = new JRadioButtonMenuItem[lookAndFeelNames.size()];
		lookAndFeelsButtonGroup = new ButtonGroup();
		
		int intSelectedLookAndFeel = 0;
		// FIN  Obtiene look and feels y los guarda en un Array

		ManejadorLookAndFeelMenuBar managerElement = new ManejadorLookAndFeelMenuBar();

		for (int i = 0; i < lookAndFeelNames.size(); i++) {
			lookAndFeels[i] = new JRadioButtonMenuItem(lookAndFeelNames.get(i));
			menuLookAndFeel.add(lookAndFeels[i]);
			lookAndFeelsButtonGroup.add(lookAndFeels[i]);
			lookAndFeels[i].addActionListener(managerElement);
			
			if(configLookAndFeel.equals(lookAndFeelNames.get(i))) {
				intSelectedLookAndFeel = i;
			}
		}
		lookAndFeels[intSelectedLookAndFeel].setSelected(true);
		
		menuAyuda = new JMenu("Ayuda");
		barMenu.add(menuAyuda);
		menuItemAcercaDe = new JMenuItem("Acerca de");
		menuAyuda.add(menuItemAcercaDe);

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
		menuItemAcercaDe.addActionListener(manejadorBotones);

		ventanaIcono= toolkitIcono.getImage("./img/icon.png");
		setIconImage(ventanaIcono);
                
	}
	//metodo para agregar componentes a un grid bag, se le pasa como parametro las restricciones de cada componente
	/*
	private void agregarComponente( Component componente, int fila, int columna, int anchura, int altura )
	{
		restricciones.gridx = columna; // establece gridx
		restricciones.gridy = fila; // establece gridy
		restricciones.gridwidth = anchura; // establece gridwidth
		restricciones.gridheight = altura; // establece gridheight
		gridCentral.setConstraints( componente, restricciones ); // establece restricciones
		panelCentral.add( componente ); // agrega el componente
	} 
	*/
	public static void main( String args[] )
	{
		String configLookAndFeel="";
		/*
		Properties prop = new Properties();
		String CONFIG_FILE = "CONFIG.config";
		try {
			prop.load(new FileInputStream(CONFIG_FILE));
			System.out.println(prop);
			configLookAndFeel = prop.getProperty("lookAndFeel");
			
		}catch(IOException e) {
			JOptionPane.showMessageDialog( null, "Hubo un problema al leer el archivo "+CONFIG_FILE, "No se pudo acceder a la configuracion", JOptionPane.ERROR_MESSAGE );
			
			e.printStackTrace();
		}
		*/
		configLookAndFeel = config.getConfig("lookAndFeel");
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if (configLookAndFeel.equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} 
		catch (Exception e) {
			JOptionPane.showMessageDialog( null, "No se pudo poner el look and feel: "+configLookAndFeel, "Hubo un incidente:", JOptionPane.PLAIN_MESSAGE);
		}

		Toolkit pantalla = Toolkit.getDefaultToolkit();
		Dimension tamanoPantalla = pantalla.getScreenSize();
		int anchoPantalla = tamanoPantalla.width;
		int altoPantalla = tamanoPantalla.height / 2;
		referenciasBibTex2 sistema = new referenciasBibTex2(configLookAndFeel, altoPantalla, anchoPantalla);
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

				boolean mostrarFileChooser = true, escribirArchivo = false;
				String ultimaRuta = config.getConfig("ultimaRuta");//System.getProperty("user.home");
				if (ultimaRuta.length()==0) ultimaRuta = System.getProperty("user.home");

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss-a");  
				Date date = new Date();
				
				while(mostrarFileChooser){
					JFileChooser fileChooser = new JFileChooser(ultimaRuta);
					fileChooser.setDialogTitle("Guardar en:");//titulo de ventana del JFileChooser

					fileChooser.setSelectedFile(new File("referencias_"+formatter.format(date)+".bib"));
					
					/*Permite ver archivos con extencion especifica*/
					//fileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt"));
					fileChooser.setFileFilter(new FileNameExtensionFilter("BibTeX (*.bib)","bib"));
					
					int seleccion = fileChooser.showSaveDialog(null);
				 
					/*Tal vez hay mas maneras de escribir archivos pero aqui se hace con un File, FileWriter, BufferedWriter y PrintWriter*/
					if (seleccion == JFileChooser.APPROVE_OPTION) {
						
						File fileToSave = fileChooser.getSelectedFile();
						ultimaRuta = fileToSave.getAbsoluteFile().getParent();
						
						if(fileToSave.exists()){
							if (JOptionPane.showConfirmDialog(null, "El archivo ya existe.\nDesea sobreescribirlo?", "Confirmar el guardado", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, iconoEncontradoEscribir) == JOptionPane.YES_OPTION) {
								escribirArchivo = true;
							} 
						}
						else{
							escribirArchivo = true;
						}
						if(escribirArchivo){
							File f;
							String rutaAbsolutaArchivo = fileToSave.getAbsolutePath();
							f = new File(rutaAbsolutaArchivo);
							
							try{
								FileWriter w = new FileWriter(f/*,true*/);//si se descomenta el "true" lo que hace es: si ya existe un archivo y se quiere escribir en el, escribe al final del archivo.
								BufferedWriter bw = new BufferedWriter(w);
								PrintWriter wr = new PrintWriter(bw);  
							
								wr.append( referenciasText.getText() );
								
								wr.close();
								bw.close();
								
								referenciasText.setText("");//al final de que se guarda el archivo se vacia el contenido del area de texto

								JOptionPane.showMessageDialog( null, rutaAbsolutaArchivo, "Guardado en:", JOptionPane.PLAIN_MESSAGE, iconoFolder);
								
								config.setConfig("ultimaRuta", ultimaRuta);
								
							}catch(IOException e){
								JOptionPane.showMessageDialog( null, "No se pudo guardar el archivo", "Hubo un incidente:", JOptionPane.PLAIN_MESSAGE, iconoError);
							}
							finally {
								mostrarFileChooser = false;
							};
						}
					}
					else{
						mostrarFileChooser = false;
					}
				}
            }
			else if (evento.getSource() == menuItemAcercaDe){
				JOptionPane.showMessageDialog( null, "Hecho por github.com/juanmx \n Bajo la Licencia: \n GNU General Public License v2.0 \n\n El codigo fuente y su jar ejecutable tuvieron que ser obtenidos de \n https://github.com/JuanMX/referencias-bibtex-java \n\n Agradecimientos al Proyecto Tango por los iconos \n https://commons.wikimedia.org/wiki/Tango_icons", "Acerca del " + tituloVentanaPrincipal, JOptionPane.PLAIN_MESSAGE, iconojuanmx );
			}
        }
    }

	private class ManejadorLookAndFeelMenuBar extends JFrame implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			String lookAndFeelSelected = "";
			
			for(int i = 0; i < lookAndFeels.length; i++) {
				
				if(event.getSource() == lookAndFeels[i]) {
					
					try {
						
						for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
							
							if (lookAndFeels[i].getText().equals(info.getName())) {
								
								lookAndFeelSelected = info.getName();
								config.setConfig("lookAndFeel", lookAndFeelSelected);
								System.out.println("Look and feel - menu bar: " + lookAndFeelSelected);
								
								JOptionPane.showMessageDialog( null, "El look and feel " + lookAndFeelSelected + " se mostrara la siguiente vez que inicie el programa", "El tema no se pudo poner en tiempo de ejecucion :(", JOptionPane.PLAIN_MESSAGE, iconoInformation );
								break;
							}
						}
					} 
					catch (Exception e) {
						
						JOptionPane.showMessageDialog( null, "Algo salio mal con el look and feel" + lookAndFeelSelected, "!", JOptionPane.ERROR_MESSAGE );
					}
				}
			}
		}
	}
}
