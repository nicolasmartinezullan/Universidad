package Formularios;
import Modelo.Entidades.*;
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
public class FormularioEstudiante extends javax.swing.JFrame {

    private DefaultTableModel modeloEstudiantes;
    private final NegocioEstudiante negocioEstudiante = new NegocioEstudiante();
    private Boton ultimoBotonUsado;
    private final RepositorioSingleton repo = RepositorioSingleton.getInstance();
    private final FormHelper helper = FormHelper.getInstance();

    public FormularioEstudiante() {
        initComponents();
        configurarFormulario();
        cargarTablaEstudiantes();
        cargarComboBoxCarreras();
        cargarComboBoxCursos();
        reiniciarFormatoFormulario();
    }

    private void configurarFormulario() {
        helper.setMaxLengthForJTextField(tflLegajo, 5);
        tflLegajo.addKeyListener(helper.getKeyListenerOnlyDigits());
        modeloEstudiantes = helper.getTableModelWithCellEditionDisabled();
        modeloEstudiantes.setColumnIdentifiers(new Object[]{"Legajo", "Apellido", "Nombre", "DNI", "Carrera", "Curso", "Direccion", "Telefono", "Fecha Enrolamiento", "Fecha Nacimiento", "Fecha Alta", "EstudianteId", "CarreraId", "CursoId"});
        helper.setRendererForComboBox(cboCarreras, Carrera.class, "getCodigoNombre");
        helper.setRendererForComboBox(cboCursos, Curso.class, "toString");
    }

    private void reiniciarFormatoFormulario() {
        ultimoBotonUsado = Boton.CANCELAR;
        tflLegajo.setText("");
        cboCarreras.setSelectedIndex(-1);
        cboCursos.setSelectedIndex(-1);
        btnNuevo.requestFocus();
        lblMensaje.setText("");
        tblEstudiantes.clearSelection();
        tblEstudiantes.setEnabled(true);
        btnNuevo.setEnabled(true);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnRefrescar.setEnabled(true);
        btnAceptar.setEnabled(false);
        btnCancelar.setEnabled(false);
        cboCursos.setEnabled(false);
        cboCarreras.setEnabled(false);
        tflLegajo.setEnabled(false);
        tflApellido.setEnabled(false);
        tflNombre.setEnabled(false);
        tflDni.setEnabled(false);
        tflDireccion.setEnabled(false);
        tflTelefono.setEnabled(false);
        jdpFechaEnrolamiento.setEnabled(false);
        jdpFechaNacimiento.setEnabled(false);
        lblFoto.setEnabled(false);
    }

    private void cargarComboBoxCursos() {
        from(repo.getCursos()).orderBy("toString", Order.ASC).all().forEach(n -> cboCursos.addItem(n));
    }

    private void cargarComboBoxCarreras() {
        from(repo.getCarreras()).orderBy("getCodigo", Order.ASC).all().forEach(c -> cboCarreras.addItem(c));
    }

    private void cargarTablaEstudiantes() {
        helper.limpiarModeloTabla(modeloEstudiantes);
        repo.getEstudiantes().forEach(c -> {
            modeloEstudiantes.addRow(new Object[]{
                c.getLegajo(),
                c.getApellido(),
                c.getNombre(),
                c.getDni(),
                from(repo.getCarreras()).where("getCarreraId", eq(c.getCarreraId())).first().getNombre(),
                from(repo.getCursos()).where("getCursoId", eq(c.getCursoId())).first().getCodigoAlfaNumerico(),
                c.getDireccion(),
                c.getTelefono(),
                c.getFechaEnrolamiento(),
                c.getFechaNacimiento(),
                c.getFechaAlta(),
                c.getEstudianteId(),
                c.getCarreraId(),
                c.getCursoId()
            });
        });
        tblEstudiantes.setModel(modeloEstudiantes);
        helper.eliminarColumnasDeTabla(tblEstudiantes, Arrays.asList("EstudianteId", "CarreraId", "CursoId"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblEstudiantes = new javax.swing.JTable();
        lblLegajo = new javax.swing.JLabel();
        lblApellido = new javax.swing.JLabel();
        lblDni = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        lblCurso = new javax.swing.JLabel();
        tflLegajo = new javax.swing.JTextField();
        tflApellido = new javax.swing.JTextField();
        tflNombre = new javax.swing.JTextField();
        tflDni = new javax.swing.JTextField();
        lblFechaEnrolamiento = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        tflDireccion = new javax.swing.JTextField();
        cboCursos = new javax.swing.JComboBox();
        cboCarreras = new javax.swing.JComboBox();
        tflTelefono = new javax.swing.JTextField();
        btnNuevo = new javax.swing.JButton();
        btnRefrescar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        lblFoto = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        lblMensaje = new javax.swing.JLabel();
        lblFechaNacimiento = new javax.swing.JLabel();
        jdpFechaNacimiento = new com.toedter.calendar.JDateChooser();
        jdpFechaEnrolamiento = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Estudiante");
        setIconImage(java.awt.Toolkit.getDefaultToolkit().getImage(getClass().getResource("..//Varios//icon.gif")));

        tblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"66947", "Martinez Ullan", "Nicolas", "32748653", "Ingeniería en Sistemas de Información", "1K9", "1-enero-2014", "Calle 123", "351-222-2222"}
            },
            new String [] {
                "Legajo", "Apellido", "Nombre", "DNI", "Carrera", "Curso", "Fecha enrolamiento", "Direccion", "Telefono"
            }
        ));
        jScrollPane1.setViewportView(tblEstudiantes);

        lblLegajo.setText("Legajo");

        lblApellido.setText("Apellido");

        lblDni.setText("DNI");

        lblNombre.setText("Nombre");

        lblCarrera.setText("Carrera");

        lblCurso.setText("Curso");

        tflLegajo.setText("66947");

        tflApellido.setText("Martinez Ullan");

        tflNombre.setText("Nicolas");

        tflDni.setText("32748653");

        lblFechaEnrolamiento.setText("Fecha enrolamiento");

        lblDireccion.setText("Direccion");

        lblTelefono.setText("Telefono");

        tflDireccion.setText("Calle 123");

        cboCursos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1K9" }));

        cboCarreras.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ingenieria en Sistemas de Informacion" }));

        tflTelefono.setText("351-222-2222");

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnRefrescar.setText("Refrescar");
        btnRefrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefrescarActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setText("Sin imagen");
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblFoto.setMaximumSize(new java.awt.Dimension(100, 100));
        lblFoto.setMinimumSize(new java.awt.Dimension(100, 100));
        lblFoto.setPreferredSize(new java.awt.Dimension(100, 100));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblFechaNacimiento.setText("Fecha Nacimiento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMensaje, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNombre)
                                    .addComponent(lblApellido)
                                    .addComponent(lblLegajo)
                                    .addComponent(lblDni)
                                    .addComponent(lblCarrera)
                                    .addComponent(lblDireccion)
                                    .addComponent(lblFechaEnrolamiento)
                                    .addComponent(lblCurso)
                                    .addComponent(lblTelefono))
                                .addGap(66, 66, 66))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblFechaNacimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jdpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tflApellido)
                            .addComponent(tflDni)
                            .addComponent(tflNombre)
                            .addComponent(tflDireccion)
                            .addComponent(cboCarreras, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboCursos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tflLegajo)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(359, 359, 359)
                                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnRefrescar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jdpFechaEnrolamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(tflTelefono))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflLegajo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLegajo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblApellido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tflDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDni))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jdpFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimiento))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCarrera)
                    .addComponent(cboCarreras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurso)
                    .addComponent(cboCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaEnrolamiento)
                    .addComponent(jdpFechaEnrolamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDireccion)
                    .addComponent(tflDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTelefono)
                    .addComponent(tflTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefrescar)
                    .addComponent(btnEliminar)
                    .addComponent(btnModificar)
                    .addComponent(btnNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed

    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed

    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnRefrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefrescarActionPerformed

    }//GEN-LAST:event_btnRefrescarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed

    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FormularioEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormularioEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormularioEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormularioEstudiante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormularioEstudiante().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRefrescar;
    private javax.swing.JComboBox cboCarreras;
    private javax.swing.JComboBox cboCursos;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdpFechaEnrolamiento;
    private com.toedter.calendar.JDateChooser jdpFechaNacimiento;
    private javax.swing.JLabel lblApellido;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblDni;
    private javax.swing.JLabel lblFechaEnrolamiento;
    private javax.swing.JLabel lblFechaNacimiento;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblLegajo;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JTable tblEstudiantes;
    private javax.swing.JTextField tflApellido;
    private javax.swing.JTextField tflDireccion;
    private javax.swing.JTextField tflDni;
    private javax.swing.JTextField tflLegajo;
    private javax.swing.JTextField tflNombre;
    private javax.swing.JTextField tflTelefono;
    // End of variables declaration//GEN-END:variables
}
