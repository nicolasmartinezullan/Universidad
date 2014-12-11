package Formularios;
import Modelo.Entidades.Carrera;
import Negocio.RepositorioSingleton;
import static com.wagnerandade.coollection.Coollection.from;
import com.wagnerandade.coollection.query.order.Order;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
/**
 *
 * @author Nicolas Martinez Ullan - 66947 - AED - Trabajo Investigacion -
 * Noviembre 2014
 */
public class FormHelper {
    private static FormHelper helper;
    private final KeyListener keyListener;
    private final RepositorioSingleton repo = RepositorioSingleton.getInstance();
    private FormHelper() {
        keyListener = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9')
                        || (c == KeyEvent.VK_BACK_SPACE)
                        || (c == KeyEvent.VK_DELETE))) {
//                java.awt.Window.getToolkit().beep();
                    e.consume();
                }}};
    }
    public static FormHelper getInstance() {
        if (helper == null)
            helper = new FormHelper();
        return helper;
    }
    public KeyListener getKeyListenerOnlyDigits(){return keyListener;}
    public void setMaxLengthForJTextField(JTextField tfl, int maxLength){
        tfl.setDocument(new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if(getLength() + str.length() <= maxLength)
                    super.insertString(offs, str, a);
            }
        });
    }
    
    public void limpiarModeloTabla(DefaultTableModel modelo){
        while (modelo.getRowCount()>0) 
            modelo.removeRow(0);
    }
    
    public void mensajeExito(JLabel label, String mensaje){
        label.setText(mensaje);
        label.setForeground(new Color(0, 153, 0));
    }
    
    public void mensajeError(JLabel label, String mensaje){
        label.setText(mensaje);
        label.setForeground(Color.RED);
    }
    
    public void eliminarColumnasDeTabla(JTable tabla, List<String> nombreColumnas){
        nombreColumnas.forEach(c -> {
            try {
                tabla.removeColumn(tabla.getColumn(c));
            }
            catch (Exception e) {
            }
        });
    }
    
    public void cargarComboBoxNiveles(JComboBox cboNiveles) {
        from(repo.getNiveles()).orderBy("getNivel", Order.ASC).all().forEach(n -> cboNiveles.addItem(n));
    }

    public void cargarComboBoxCarreras(JComboBox cboCarreras) {
        from(repo.getCarreras()).orderBy("getCodigo", Order.ASC).all().forEach(c -> cboCarreras.addItem(c));
    }

    public void setRendererForComboBox(JComboBox comboBox, Class clase, String nombreMetodo){
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Object item = value;
                // if the item to be rendered is Proveedores then display it's Name
                if(clase.isInstance(item)){
                try {
                    //                if (item instanceof Carrera)
//                    item = ((Carrera) item).getCodigoNombre();
//                    item = (clase.cast(item)).getCodigoNombre();
                    item = clase.getMethod(nombreMetodo).invoke(clase.cast(item));
                }
                catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(FormHelper.class.getName()).log(Level.SEVERE, null, ex);
                }}
                return super.getListCellRendererComponent(list, item, index, isSelected, cellHasFocus);
            }
        });
    }
    
    public DefaultTableModel getTableModelWithCellEditionDisabled(){
        return new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
    }
}
