
package GUI;

import Analizadores.AnalizadorLexico1;
import Analizadores.AnalizadorLexicoError;
import Analizadores.parser;
import Analizadores.parser3;
import Logica.Acciones;
import Objetos.Mandar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jcsr
 */
public class AreaTrabajo extends javax.swing.JInternalFrame implements Runnable{

    private String usuario;
    
    public AreaTrabajo(String usuario) {
        initComponents();
        this.usuario = usuario;
        textoUsuario.setText(usuario);
        Thread hilo = new Thread(this);
        hilo.start();
    }
    // sirve para validar el codigo 
    public void validarCodigo(){
        try {
            Acciones.setERROR(false);// reinicia si hubieron errores
            String texto = textoSalida.getText();
            StringReader reader = new StringReader(texto);
            AnalizadorLexico1 lex = new AnalizadorLexico1(reader, textoEntrada);
            parser parser = new parser(lex, textoEntrada);
            parser.parse();// obtiene la informacion del parser 
            if(Acciones.isERROR()==false){
                try{// si no existieron errores entonces manda el mensaje al servidor 
                Mandar mandar = new Mandar(texto, this.usuario);
                Socket cliente = new Socket("localhost", 9090);// por medio de puerto 9090
                ObjectOutputStream flujo  = new ObjectOutputStream(cliente.getOutputStream());
                flujo.writeObject(mandar);// manda el mensaje 
                cliente.close();// cierra el socket
                // dice que se mando el mensaje
                textoEntrada.append("\n"+"MENSAJE BIEN ESCRITO SE MANDARA AL SERVIDOR");
                }catch(Exception e){
                    e.printStackTrace();//muestra el error
                    textoEntrada.append("\n"+"NO SE LOGRO CONECTAR AL SERVIDOR");
                }
            }else{//muestra el error
                textoEntrada.append("\n"+"NO SE PUEDE MANDAR AL SERVIDOR YA QUE TIENE ERRORES");
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("ERROR AL PARSEAR");//muestra el error
            textoEntrada.append("\n"+"NO SE PUEDE MANDAR AL SERVIDOR YA QUE TIENE ERRORES DE PARSEO");
        }
    }
    
     public void run() {
        try {// hilo con el cual el cliente recibe mensajes de respuesta del servidor
            System.out.println("Entre");
            ServerSocket servidor = new ServerSocket(8080);
            Socket cliente;// por el puerto 8080
            AnalizadorLexicoError lex;
            parser3 parser;
            StringReader reader;

            while (true) {
                // el cliente se queda esperando 
                cliente = servidor.accept();
                DataInputStream flujo = new DataInputStream(cliente.getInputStream());
                String mensajeRecibido;
                mensajeRecibido =  flujo.readUTF();
                reader = new StringReader(mensajeRecibido);
                lex = new AnalizadorLexicoError(reader);
                parser = new parser3(lex, textoEntrada);
                parser.parse();
                cliente.close();// recibe el mensaje y lo parsea
            }

        } catch (IOException ex) {
            ex.printStackTrace();//muestra el error
        }catch (Exception ex) {//muestra el error
            textoEntrada.append("\nOCURRIO UN ERROR AL LEER EL MENSAJE DEL SERVIDOR");
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoUsuario = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoSalida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoEntrada = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("AREA DE TRABAJO");

        jLabel1.setText("AREA TRABAJO");

        jLabel2.setText("Usuario:");

        textoSalida.setColumns(20);
        textoSalida.setRows(5);
        jScrollPane1.setViewportView(textoSalida);

        textoEntrada.setColumns(20);
        textoEntrada.setRows(5);
        jScrollPane2.setViewportView(textoEntrada);

        jLabel3.setText("AREA DE CODIGO XML DE SALIDA");

        jLabel4.setText("RESPUESTAS DEL SERVIDOR Y DE ESTA APLICACION");

        jButton1.setText("ENVIAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(412, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(391, 391, 391))))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addContainerGap())
                    .addComponent(jScrollPane2)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(7, 7, 7)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        validarCodigo();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea textoEntrada;
    private javax.swing.JTextArea textoSalida;
    private javax.swing.JLabel textoUsuario;
    // End of variables declaration//GEN-END:variables
}
