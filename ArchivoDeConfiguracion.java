import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane; 

public class ArchivoDeConfiguracion {	
    public static Properties prop = new Properties();
    public String CONFIG_FILE = "CONFIG.config";
    
    public void setConfig(String key, String value) {
        
        try{
            
            prop.setProperty(key, value);
            prop.store(new FileOutputStream(CONFIG_FILE), null);
            System.out.println("Set config " + key + " " + value + " " + CONFIG_FILE);
            
        }catch(IOException e){
            JOptionPane.showMessageDialog( null, "No se pudo accesar a: " + CONFIG_FILE + "\nEl programa intentara continuar sin este archivo", "Problema con el archivo de configuarcion", JOptionPane.PLAIN_MESSAGE );
            e.printStackTrace();
        }
        
    }
    
    public String getConfig(String key) {
        
        String value = "";
        
        try {
            prop.load(new FileInputStream(CONFIG_FILE));
            value = prop.getProperty(key);
            
        }catch(IOException e) {
            JOptionPane.showMessageDialog( null, "No se pudo leer: " + CONFIG_FILE + "\nEl programa intentara continuar sin este archivo", "Problema con el archivo de configuarcion", JOptionPane.PLAIN_MESSAGE );
            
            e.printStackTrace();
        }finally {
            System.out.println("Get config " + key + " " + value + " " + CONFIG_FILE);
        }
        
        return value;
    }
}