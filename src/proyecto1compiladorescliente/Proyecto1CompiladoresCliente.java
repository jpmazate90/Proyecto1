/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1compiladorescliente;

import Analizadores.AnalizadorLexico1;
import Analizadores.parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsr
 */
public class Proyecto1CompiladoresCliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hola = "<SITIO>\n" +
"<ID> hola </ID>\n" +
"<USUARIO_CREACION> luis </USUARIO_CREACION>\n" +
"<FECHA_CREACION> 20190809 </FECHA_CREACION>\n" +
"<FECHA_MODIFICACION> 20191010 </FECHA_MODIFICACION>\n" +
"<USUARIO_MODIFICACION>jorge</USUARIO_MODIFICACION>\n" +
"</SITIO>";
        StringReader jaja = new StringReader(hola);
        File archivo = new File("archivo.txt");
        File archivo2 = new File("archivo2.txt");
        try {
          
            
           FileReader reader2 = new FileReader(archivo2);
           AnalizadorLexico1 lex1 = new AnalizadorLexico1(reader2);
           parser parser1 = new parser(lex1);
           parser1.parse();
            
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
