package Formularios;
import Modelo.Enumeradores.Boton;
import Negocio.RepositorioSingleton;
import static com.wagnerandade.coollection.Coollection.eq;
import static com.wagnerandade.coollection.Coollection.from;
import java.awt.Color;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class FormularioNivel extends javax.swing.JFrame {

    private DefaultTableModel modeloNiveles;
    private final RepositorioSingleton repo = RepositorioSingleton.getInstance();
    private final FormHelper helper = FormHelper.getInstance();
    private Boton ultimoBotonUsado;
    
    public FormularioNivel() {
        initComponents();
    }
    
    private void configurarFormulario(){
        
        helper.setMaxLengthForJTextField(tflNivel, 2);
        tflNivel.addKeyListener(helper.getKeyListenerOnlyDigits());
        modeloNiveles = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        modeloNiveles.setColumnIdentifiers(new Object[]{ "Nivel", "Fecha Alta", "Ult. Mod. Por", "Ult. Mod. Fecha", "NivelId" });
    }

    private void reiniciarFormatoFormulario(){
        ultimoBotonUsado = Boton.CANCELAR;
        tflNivel.setText("");
        tflFechaAlta.setText("");
        tflUltimaModificacionPor.setText("");
        tflUltimaModificacionFecha.setText("");
        btnNuevo.requestFocus();
        lblMensaje.setText("");
        tblNiveles.clearSelection();
        tblNiveles.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
//        cboNiveles.setEnabled(false);
//        cboCarreras.setEnabled(false);
//        tfNumero.setEnabled(false);
    }
    
    private void mensajeExito(String mensaje){
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(new Color(0, 153, 0));
    }
    
    private void mensajeError(String mensaje){
        lblMensaje.setText(mensaje);
        lblMensaje.setForeground(Color.RED);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblNiveles = new javax.swing.JTable();
        lblNivel = new javax.swing.JLabel();
        tflNivel = new javax.swing.JTextField();
        lblFechaAlta = new javax.swing.JLabel();
        tflFechaAlta = new javax.swing.JTextField();
        lblUltimaModificacionPor = new javax.swing.JLabel();
        tflUltimaModificacionPor = new javax.swing.JTextField();
        lblUltimaModificacionFecha = new javax.swing.JLabel();
        tflUltimaModificacionFecha = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        btnRefrescar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nivel");

        tblNiveles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nivel", "Fecha Alta", "Ult. Mod. Por", "Ult. Mod Fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNiveles.setToolTipText("Tabla Niveles");
        jScrollPane1.setViewportView(tblNiveles);

        lblNivel.setText("Nivel");

        lblFechaAlta.setText("Fecha Alta");

        tflFechaAlta.setEnabled(false);

        lblUltimaModificacionPor.setText("Ult. Mod. Por");

        tflUltimaModificacionPor.setEnabled(false);

        lblUltimaModificacionFecha.setText("Ult. Mod. Fecha");

        tflUltimaModificacionFecha.setEnabled(false);

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");

        btnAceptar.setText("Aceptar");

        btnCancelar.setText("Cancelar");

        btnEliminar.setText("Eliminar");

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNivel)
                            .addComponent(lblFechaAlta)
                            .addComponent(lblUltimaModificacionPor)
                            .addComponent(lblUltimaModificacionFecha))
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tflNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                            .addComponent(tflFechaAlta)
                            .addComponent(tflUltimaModificacionPor)
                            .addComponent(tflUltimaModificacionFecha))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(btnModificar)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar))
                            .addComponent(btnAceptar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCancelar)
                            .addComponent(btnRefrescar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNivel))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaAlta)
                    .addComponent(tflFechaAlta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUltimaModificacionPor)
                    .addComponent(tflUltimaModificacionPor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflUltimaModificacionFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUltimaModificacionFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnNuevo)
                    .addComponent(btnRefrescar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
//        ultimoBotonUsado = Boton.NUEVO;
//        cboNiveles.setSelectedIndex(-1);
//        cboNiveles.requestFocus();
//        cboCarreras.setSelectedIndex(-1);
//        tfNumero.setText("");
//        tfPrevisualizacion.setText("");
//        lblMensaje.setText("");
//        tblCursos.clearSelection();
//        tblCursos.setEnabled(false);
//        btnNuevo.setEnabled(false);
//        btnModificar.setEnabled(false);
//        btnEliminar.setEnabled(false);
//        btnRefrescar.setEnabled(true);
//        btnAceptar.setEnabled(true);
//        btnCancelar.setEnabled(true);
//        cboNiveles.setEnabled(true);
//        cboCarreras.setEnabled(true);
//        tfNumero.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        ultimoBotonUsado = Boton.REFRESCAR;
        repo.refrescarNiveles();
        cargarTablaNiveles();
        reiniciarFormatoFormulario();
    }//GEN-LAST:event_btnRefrescarActionPerformed
private void cargarTablaNiveles() {
        helper.limpiarModeloTabla(modeloNiveles);
        repo.getNiveles().forEach(c -> {
            modeloNiveles.addRow(new Object[]{
                    from(repo.getNiveles()).where("getNivelId", eq(c.getNivelId())).first().getNivel()
//                    , from(repo.getCarreras()).where("getCarreraId", eq(c.getCarreraId())).first().toString()
//                    , c.getNumero()
//                    , c.getCursoId()
                    , from(repo.getNiveles()).where("getNivelId", eq(c.getNivelId())).first().getNivelId()
//                    , from(repo.getCarreras()).where("getCarreraId", eq(c.getCarreraId())).first().getCarreraId()
            });
        });
        tblNiveles.setModel(modeloNiveles);
        try {
            tblNiveles.removeColumn(tblNiveles.getColumn("NivelId"));
        }
        catch (Exception e) {
        }
}

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormularioNivel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioNivel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioNivel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioNivel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormularioNivel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaAlta;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblUltimaModificacionFecha;
    private javax.swing.JLabel lblUltimaModificacionPor;
    private javax.swing.JTable tblNiveles;
    private javax.swing.JTextField tflFechaAlta;
    private javax.swing.JTextField tflNivel;
    private javax.swing.JTextField tflUltimaModificacionFecha;
    private javax.swing.JTextField tflUltimaModificacionPor;
    // End of variables declaration//GEN-END:variables
}
