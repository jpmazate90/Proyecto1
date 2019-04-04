
package GUI;

import Analizadores.AnalizadorLexicoUsuarios;
import Analizadores.parser2;
import Logica.Acciones;
import Objetos.Usuario;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerUsuarios extends javax.swing.JInternalFrame {

    JDesktopPane escritorio;
    TablaModelo modelo;
    Acciones accion;
    
    private File archivoUsuarios;
    private final String DIRECCION = "usuarios.txt";
    private FileReader reader;
    private AnalizadorLexicoUsuarios lexUsuarios;
    private parser2 parser;

    
    public VerUsuarios(JDesktopPane escritorio) {
        initComponents();
        accion = new Acciones();
        this.escritorio = escritorio;
        modelo = new TablaModelo();
        modelo.addColumn("USUARIO");
        modelo.addColumn("CONTRASENA");
        tablaUsuarios.setModel(modelo);
        ArrayList<Usuario> usuarios = Acciones.getUsuarios();
        usuarios.clear();
        Acciones.setUsuarios(usuarios);
        accion = new Acciones();
        archivoUsuarios = new File(DIRECCION);
        try {
            reader = new FileReader(archivoUsuarios);
            lexUsuarios = new AnalizadorLexicoUsuarios(reader);
            parser = new parser2(lexUsuarios);
            parser.parse();
            llenarTabla();
        } catch (FileNotFoundException ex) {
            System.out.println("PROBLEMA AL ABRIR EL ARCHIVO DE USUARIOS");
        } catch (Exception ex) {
            System.out.println("HUBO UN PROBLEMA AL PARSEAR LOS USUARIOS");
        }
    }
    
    public void borrarDatos(){
        for(int i=0;i<modelo.getRowCount();i++){
            modelo.removeRow(i);
            if(modelo.getRowCount()>0){
                borrarDatos();
            }
        }
    }
    
    public void recargarTabla(){
        borrarDatos();
        llenarTabla();
    }
    
    public void llenarTabla(){
        accion.llenarUsuarios(modelo);
    }
    
    public void crear(){
        InicioSesion sesion = new InicioSesion(escritorio);
        this.escritorio.add(sesion);
        sesion.show();
        
        
    }
    
    public void eliminar(){
        if(textoUsuario.getText()==null||textoUsuario.getText().equals("")){
            JOptionPane.showMessageDialog(null,"NO SE HA SELECCIONADO NINGUN USUARIO DE LA TABLA, \nDELE CLICK ENCIMA AL USUARIO QUE QUIERE ELIMINAR");
        }else{
            accion.borrarUsuario(textoUsuario.getText());
            recargarTabla();
            textoUsuario.setText("");
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable();
        textoUsuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("USUARIOS");

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaUsuarios);

        textoUsuario.setEditable(false);

        jLabel2.setText("USUARIO:");

        jButton1.setText("ELIMINAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CREAR USUARIO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("ACTUALIZAR TABLA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
       int seleccion = tablaUsuarios.getSelectedRow();
        textoUsuario.setText(String.valueOf(tablaUsuarios.getValueAt(seleccion, 0)));//muestra lo que se selecciono de la tabla en las casillas
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        eliminar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       crear();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        recargarTabla();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuarios;
    private javax.swing.JTextField textoUsuario;
    // End of variables declaration//GEN-END:variables
}
