package Formularios;
import Modelo.Entidades.Nivel;
import Modelo.Entidades.Carrera;
import Negocio.*;
import javax.swing.table.DefaultTableModel;
import static com.wagnerandade.coollection.Coollection.*;
import com.wagnerandade.coollection.query.order.Order;
import java.awt.event.KeyEvent;
import Modelo.Enumeradores.Boton;
import java.util.Arrays;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class FormularioCurso extends javax.swing.JFrame {
    private DefaultTableModel modeloCursos;
    private final NegocioCurso negocioCurso = new NegocioCurso();
    private Boton ultimoBotonUsado;
    private final RepositorioSingleton repo = RepositorioSingleton.getInstance();
    private final FormHelper helper = FormHelper.getInstance();
    
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
            java.util.logging.Logger.getLogger(FormularioCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormularioCurso().setVisible(true);
        });
    }

    public FormularioCurso() {
        initComponents();
        configurarFormulario();
        cargarTablaCursos();
        cargarComboBoxCarreras();
        cargarComboBoxNiveles();
        reiniciarFormatoFormulario();
    }

    private void configurarFormulario(){
        helper.setMaxLengthForJTextField(tflNumero, 2);
        tflNumero.addKeyListener(helper.getKeyListenerOnlyDigits());
        btnModificar.setVisible(false);
        btnModificar.setEnabled(false);
        modeloCursos = helper.getTableModelWithCellEditionDisabled();
        modeloCursos.setColumnIdentifiers(new Object[]{ "Nivel", "Carrera", "Numero", "CursoId", "NivelId", "CarreraId" });
        helper.setRendererForComboBox(cboCarreras, Carrera.class, "getCodigoNombre");
        helper.setRendererForComboBox(cboNiveles, Nivel.class, "getNivel");
    }
    
    private void reiniciarFormatoFormulario(){
        ultimoBotonUsado = Boton.CANCELAR;
        tflNumero.setText("");
        cboCarreras.setSelectedIndex(-1);
        cboNiveles.setSelectedIndex(-1);
        btnNuevo.requestFocus();
        tfPrevisualizacion.setText("");
        lblMensaje.setText("");
        tblCursos.clearSelection();
        tblCursos.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
        cboNiveles.setEnabled(false);
        cboCarreras.setEnabled(false);
        tflNumero.setEnabled(false);
    }
    
    private void cargarComboBoxNiveles() {
        from(repo.getNiveles()).orderBy("getNivel", Order.ASC).all().forEach(n -> cboNiveles.addItem(n));
    }

    private void cargarComboBoxCarreras() {
        from(repo.getCarreras()).orderBy("getCodigo", Order.ASC).all().forEach(c -> cboCarreras.addItem(c));
    }

    private void cargarTablaCursos() {
        helper.limpiarModeloTabla(modeloCursos);
        repo.getCursos().forEach(c -> {
            modeloCursos.addRow(new Object[]{
                    from(repo.getNiveles()).where("getNivelId", eq(c.getNivelId())).first().getNivel()
                    , from(repo.getCarreras()).where("getCarreraId", eq(c.getCarreraId())).first().getCodigoNombre()
                    , c.getNumero()
                    , c.getCursoId()
                    , from(repo.getNiveles()).where("getNivelId", eq(c.getNivelId())).first().getNivelId()
                    , from(repo.getCarreras()).where("getCarreraId", eq(c.getCarreraId())).first().getCarreraId()
            });
        });
        tblCursos.setModel(modeloCursos);
        helper.eliminarColumnasDeTabla(tblCursos, Arrays.asList("CursoId","CarreraId","NivelId"));
    }
    
    private void aceptar(){
        if (cboNiveles.getSelectedIndex()==-1) {
            helper.mensajeError(lblMensaje, "Debe seleccionar un nivel.");
            cboNiveles.requestFocus();
            return;
        }
        if (cboCarreras.getSelectedIndex()==-1) {
            helper.mensajeError(lblMensaje, "Debe seleccionar una carrera.");
            cboCarreras.requestFocus();
            return;
        }
        if (tflNumero.getText().trim().equals("")) {
            helper.mensajeError(lblMensaje, "Debe ingresar un nombre.");
            tflNumero.requestFocus();
            return;
        }
        int nivelId = ((Nivel)cboNiveles.getSelectedItem()).getNivelId();
        int carreraId = ((Carrera)cboCarreras.getSelectedItem()).getCarreraId();
        int numero = Integer.parseInt(tflNumero.getText().trim());
        switch(ultimoBotonUsado){
            case NUEVO:{
                if (negocioCurso.existeEstaCombinacion(nivelId, carreraId, numero)) {
                    helper.mensajeError(lblMensaje, "Combinacion nivel-carrera-numero existente.");
                    return;
                }
                boolean cargaExitosa = negocioCurso.insertar(nivelId, carreraId, numero);
                if (cargaExitosa) {
                    repo.refrescarCursos();
                    cargarTablaCursos();
                    reiniciarFormatoFormulario();
                    helper.mensajeExito(lblMensaje, "Curso agregado exitosamente.");
                }
                else{
                    helper.mensajeError(lblMensaje, "No se pudo agregar curso.");
                    cboNiveles.requestFocus();
                }
                break;
            }
            case MODIFICAR:{
                // MODIFICADO: No se puede modificar un curso
//                int cursoId = Integer.parseInt(modeloCursos.getValueAt(tblCursos.getSelectedRow(), modeloCursos.findColumn("CursoId")).toString());
//                negocioCurso.modificar(cursoId, nivelId, carreraId, numero);
//                break;
            }
            default:{
                // Hacer nada...
            }
        }
    }
    
    private void armarPrevisualizacion(){
        String nivel = "";
        String codigoCarrera = "";
        if (cboNiveles.getSelectedIndex() > -1)
            nivel = ((Nivel)cboNiveles.getSelectedItem()).getNivel().toString();
        if (cboCarreras.getSelectedIndex() > -1)
            codigoCarrera = ((Carrera)cboCarreras.getSelectedItem()).getCodigo();
        tfPrevisualizacion.setText(
                nivel
                + codigoCarrera
                + tflNumero.getText().trim().toUpperCase()
        );
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCursos = new javax.swing.JTable();
        lblNombre = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        lblNivel = new javax.swing.JLabel();
        tflNumero = new javax.swing.JTextField();
        cboCarreras = new javax.swing.JComboBox();
        cboNiveles = new javax.swing.JComboBox();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tfPrevisualizacion = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cursos");

        tblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nivel", "Carrera", "Número"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCursos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblCursos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCursosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCursos);

        lblNombre.setText("Número");

        lblCarrera.setText("Carrera");

        lblNivel.setText("Nivel");

        tflNumero.setColumns(2);
        tflNumero.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tflNumeroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tflNumeroFocusLost(evt);
            }
        });
        tflNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tflNumeroKeyPressed(evt);
            }
        });

        cboCarreras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboCarrerasFocusLost(evt);
            }
        });
        cboCarreras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboCarrerasKeyPressed(evt);
            }
        });

        cboNiveles.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cboNivelesFocusLost(evt);
            }
        });
        cboNiveles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboNivelesKeyPressed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblMensaje.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMensaje.setToolTipText("");

        jLabel1.setText("Previsualización");

        tfPrevisualizacion.setEnabled(false);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblCarrera)
                                    .addComponent(lblNivel)
                                    .addComponent(jLabel1))
                                .addGap(132, 132, 132)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tflNumero)
                                    .addComponent(cboCarreras, 0, 298, Short.MAX_VALUE)
                                    .addComponent(cboNiveles, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfPrevisualizacion))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNivel)
                    .addComponent(cboNiveles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarrera)
                    .addComponent(cboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(tflNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfPrevisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnNuevo)
                        .addComponent(btnModificar)
                        .addComponent(btnEliminar))
                    .addComponent(btnRefrescar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        ultimoBotonUsado = Boton.NUEVO;
        cboNiveles.setSelectedIndex(-1);
        cboNiveles.requestFocus();
        cboCarreras.setSelectedIndex(-1);
        tflNumero.setText("");
        tfPrevisualizacion.setText("");
        lblMensaje.setText("");
        tblCursos.clearSelection();
        tblCursos.setEnabled(false);
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
        cboNiveles.setEnabled(true);
        cboCarreras.setEnabled(true);
        tflNumero.setEnabled(true);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void tblCursosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCursosMouseClicked
        if (!tblCursos.isEnabled()) 
            return;
        tflNumero.setText(modeloCursos.getValueAt(tblCursos.getSelectedRow(), modeloCursos.findColumn("Numero")).toString());
        int carreraId = Integer.parseInt(modeloCursos.getValueAt(tblCursos.getSelectedRow(), modeloCursos.findColumn("CarreraId")).toString());
        int nivelId = Integer.parseInt(modeloCursos.getValueAt(tblCursos.getSelectedRow(), modeloCursos.findColumn("NivelId")).toString());
        cboCarreras.setSelectedItem(from(repo.getCarreras()).where("getCarreraId", eq(carreraId)).first());
        cboNiveles.setSelectedItem(from(repo.getNiveles()).where("getNivelId", eq(nivelId)).first());
        armarPrevisualizacion();
        lblMensaje.setText("");
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_tblCursosMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        aceptar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void tflNumeroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tflNumeroFocusGained
        tflNumero.selectAll();
    }//GEN-LAST:event_tflNumeroFocusGained

    private void tflNumeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tflNumeroKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_tflNumeroKeyPressed

    private void cboCarrerasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboCarrerasKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_cboCarrerasKeyPressed

    private void cboNivelesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboNivelesKeyPressed
        if (evt.getKeyCode()==KeyEvent.VK_ENTER)
            aceptar();
    }//GEN-LAST:event_cboNivelesKeyPressed

    private void cboNivelesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboNivelesFocusLost
        armarPrevisualizacion();
    }//GEN-LAST:event_cboNivelesFocusLost

    private void cboCarrerasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cboCarrerasFocusLost
        armarPrevisualizacion();
    }//GEN-LAST:event_cboCarrerasFocusLost

    private void tflNumeroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tflNumeroFocusLost
        armarPrevisualizacion();
    }//GEN-LAST:event_tflNumeroFocusLost

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        ultimoBotonUsado = Boton.ELIMINAR;
        if (tblCursos.getSelectedRow() == -1) {
            helper.mensajeError(lblMensaje, "Debe seleccionar un curso.");
            return;
        }
        int cursoId = Integer.parseInt(modeloCursos.getValueAt(tblCursos.getSelectedRow(), modeloCursos.findColumn("CursoId")).toString());
        negocioCurso.eliminar(cursoId);
        repo.refrescarCursos();
        cargarTablaCursos();
        reiniciarFormatoFormulario();
        helper.mensajeExito(lblMensaje, "Curso eliminado.");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed
        ultimoBotonUsado = Boton.REFRESCAR;
        repo.refrescarCursos();
        cargarTablaCursos();
        reiniciarFormatoFormulario();
    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        reiniciarFormatoFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        ultimoBotonUsado = Boton.MODIFICAR;
        tblCursos.setEnabled(false);
        cboNiveles.requestFocus();
        btnNuevo.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(true);
        btnCancelar.setEnabled(true);
    }//GEN-LAST:event_btnModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox cboCarreras;
    private javax.swing.JComboBox cboNiveles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNivel;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTable tblCursos;
    private javax.swing.JTextField tfPrevisualizacion;
    private javax.swing.JTextField tflNumero;
    // End of variables declaration//GEN-END:variables
}
