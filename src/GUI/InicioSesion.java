
package GUI;

import Analizadores.AnalizadorLexicoUsuarios;
import Analizadores.parser2;
import Logica.Acciones;
import Objetos.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class InicioSesion extends javax.swing.JInternalFrame {
    // atributos privados de la clase
    private Acciones accion;
    private File archivoUsuarios;
    private final String DIRECCION = "usuarios.txt";
    private FileReader reader;
    private AnalizadorLexicoUsuarios lexUsuarios;
    private parser2 parser;
    private JDesktopPane escritorio;
    
    
    // constructor
    public InicioSesion(JDesktopPane escritorio) {
        initComponents();
        ArrayList<Usuario> usuarios = Acciones.getUsuarios();
        usuarios.clear();
        Acciones.setUsuarios(usuarios);
        this.escritorio= escritorio;
        accion = new Acciones();
        archivoUsuarios = new File(DIRECCION);
        try {// carga los usuarios al cliente
            reader = new FileReader(archivoUsuarios);
            lexUsuarios = new AnalizadorLexicoUsuarios(reader);
            parser = new parser2(lexUsuarios);
            parser.parse();
        } catch (FileNotFoundException ex) {//muestra el error
            System.out.println("PROBLEMA AL ABRIR EL ARCHIVO DE USUARIOS");
            JOptionPane.showMessageDialog(null,"PROBLEMA AL ABRIR EL ARCHIVO DE USUARIOS");
        } catch (Exception ex) {//muestra el error
            System.out.println("HUBO UN PROBLEMA AL PARSEAR LOS USUARIOS");
        }
        
        
    }
    
    public void inicio(){//mira si coincide la informacion de los usuarios
        String nombre = textoNombre.getText();
        String contrasena = textoContrasena.getText();
        Usuario usuario = new Usuario(nombre, contrasena);
        boolean match = accion.inicioSesion(usuario);
        if(match == true){
            AreaTrabajo area = new AreaTrabajo(usuario.getNombre());
            this.escritorio.removeAll();
            this.escritorio.repaint();
            this.escritorio.add(area);
            area.show();
        }else{
            JOptionPane.showMessageDialog(null,"INFORMACION INCORRECTA, NOMBRE O CONTRASENA INCORRECTOS");
        }
        
    }
    
    public void crear(){// sirve para crear un usuario
        String nombre = textoNombre.getText();
        String contrasena = textoContrasena.getText();
        Usuario usuario = new Usuario(nombre, contrasena);
        boolean match = accion.verificarUsuario(usuario);
        if(match == true){// si hizo match entonces crea el usuario
            accion.crearUsuario(usuario);//muestra el error
            JOptionPane.showMessageDialog(null,"SE CREO CORRECTAMENTE EL USUARIO: "+usuario.getNombre());
        }else{//muestra el error
            JOptionPane.showMessageDialog(null,"EL USUARIO "+nombre+" YA EXISTE, NO SE PUEDE CREAR");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoNombre = new javax.swing.JTextField();
        textoContrasena = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INICIO SESION");

        jLabel1.setText("NOMBRE:");

        jLabel2.setText("CONTRASENA:");

        jButton1.setText("ENTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CREAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoNombre)
                    .addComponent(textoContrasena)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      inicio();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        crear();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField textoContrasena;
    private javax.swing.JTextField textoNombre;
    // End of variables declaration//GEN-END:variables
}
