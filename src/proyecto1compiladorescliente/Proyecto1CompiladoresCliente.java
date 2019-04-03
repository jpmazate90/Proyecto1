/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1compiladorescliente;

import Analizadores.AnalizadorLexico1;
import Analizadores.AnalizadorLexicoUsuarios;
import Analizadores.parser;
import Analizadores.parser2;
import GUI.Menu;
import Logica.Acciones;
import Objetos.Usuario;
import java.awt.Frame;
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
        Menu menu = new Menu();
        menu.setExtendedState(Frame.MAXIMIZED_BOTH);
        menu.setVisible(true);
    }
    
}
