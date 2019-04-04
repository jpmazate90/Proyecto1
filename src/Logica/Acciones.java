
package Logica;

import Objetos.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jcsr
 */
public class Acciones {
    
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static boolean ERROR = false;
    
    public boolean inicioSesion (Usuario usuario){
        boolean existe=false;
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getNombre().equals(usuario.getNombre())){
                if(usuarios.get(i).getContrasena().equals(usuario.getContrasena())){
                    existe = true;
                }else{
                    existe = false;
                }
                break;
            }
            
        }
        return existe;
    }
    
    public boolean verificarUsuario(Usuario usuario){
        boolean existe=false;
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getNombre().equals(usuario.getNombre())){
                existe = true;
                break;
            }
        }
        if(existe == true){
            return false;
        }else{
            return true;
        }        
    }
    
    public void crearUsuario(Usuario usuario){
        usuarios.add(usuario);
        actualizarArchivo();
    }
    
    public void borrarUsuario(String usuario){
        for (int i = 0; i < usuarios.size(); i++) {
            if(usuarios.get(i).getNombre().equals(usuario)){
                usuarios.remove(i);
                break;
            }
        }
        JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE EL USUAIRO:"+usuario);
        actualizarArchivo();
    }
    
    public void llenarUsuarios(DefaultTableModel modelo){
        String datos[] = new String[2];
        for (int i = 0; i < usuarios.size(); i++) {
            datos[0] = usuarios.get(i).getNombre();
            datos[1] = usuarios.get(i).getContrasena();
            modelo.addRow(datos);
            
        }
    }
    
    
    
    public void actualizarArchivo(){
        try {
            File archivo = new File("usuarios.txt");
            FileWriter reader;
            reader = new FileWriter(archivo);
            String inicioUsuario = "<USUARIO>";
            String finUsuario = "</USUARIO>";
            String inicioNombre = "<NOMBRE>";
            String finNombre = "</NOMBRE>";
            String inicioContrasena = "<CONTRASENA>";
            String finContrasena = "</CONTRASENA>";
            String corcheteAbierto = "[";
            String corcheteCerrado = "]";
            BufferedWriter escritor = new BufferedWriter(reader);
            for (int i = 0; i < usuarios.size(); i++) {
                escritor.write(inicioUsuario);
                escritor.write("\n\t");
                escritor.write(inicioNombre);
                escritor.write(corcheteAbierto);
                escritor.write(usuarios.get(i).getNombre());
                escritor.write(corcheteCerrado);
                escritor.write(finNombre);
                escritor.write("\n\t");
                escritor.write(inicioContrasena);
                escritor.write(corcheteAbierto);
                escritor.write(usuarios.get(i).getContrasena());
                escritor.write(corcheteCerrado);
                escritor.write(finContrasena);
                escritor.write("\n");
                escritor.write(finUsuario);
                escritor.write("\n\n");
            }
            
            escritor.flush();
            escritor.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void accionError(String mensaje, JTextArea area){
        area.append("\n"+mensaje);
    }
    
    

    public static ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(ArrayList<Usuario> usuarios) {
        Acciones.usuarios = usuarios;
    }

    public static boolean isERROR() {
        return ERROR;
    }

    public static void setERROR(boolean ERROR) {
        Acciones.ERROR = ERROR;
    }
    
    
    
}
