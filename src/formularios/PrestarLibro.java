/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import core.ConexionDB;
import core.Variables;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author funes
 */
public class PrestarLibro extends javax.swing.JInternalFrame {
    
    ConexionDB conexion;
    DefaultTableModel modeloLibros;
    int tipoBusqueda = 0;//0=no buscar, 1=por id, 2=por nombre, 3=por autor, 4=por categoria
    LinkedList<LinkedList<String>> alAutores, alCategorias, alLibros;


    /**
     * Creates new form ListadoLibros
     */
    public PrestarLibro() {
        initComponents();
        
        try{
            conexion=new ConexionDB(Variables.rutaDB, Variables.userDB, Variables.claveDB);
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e)
        {
            JOptionPane.showInternalMessageDialog(rootPane, "Error en la conexion a la base de datos. Contacte a su administrador", "Error",JOptionPane.ERROR_MESSAGE);
        }
        
        if(Variables.user.tipoUsuario==1)
        {
            modeloLibros=(DefaultTableModel) tblLibros.getModel();
            
            modeloLibros.addColumn("Precio");
            
            modeloLibros.addColumn("Precio total");
        
            tblLibros.getColumnModel().getColumn(0).setPreferredWidth(65);
            tblLibros.getColumnModel().getColumn(1).setPreferredWidth(270);
            tblLibros.getColumnModel().getColumn(2).setPreferredWidth(163);
            tblLibros.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblLibros.getColumnModel().getColumn(4).setPreferredWidth(60);
            
            tblLibros.getColumnModel().getColumn(5).setPreferredWidth(55);
            tblLibros.getColumnModel().getColumn(5).setPreferredWidth(55);
        }
        else
        {
            modeloLibros=(DefaultTableModel) tblLibros.getModel();
        
            tblLibros.getColumnModel().getColumn(0).setPreferredWidth(65);
            tblLibros.getColumnModel().getColumn(1).setPreferredWidth(415);
            tblLibros.getColumnModel().getColumn(2).setPreferredWidth(163);
            tblLibros.getColumnModel().getColumn(3).setPreferredWidth(75);
            tblLibros.getColumnModel().getColumn(4).setPreferredWidth(60);
        }
        
        
        
        cargarTabla();
        
        ocultarBuscadores();
        
        //ocultando objetos a usuarios
        if(Variables.user.tipoUsuario==0)
        {
            btnAbrirDialogoImpresion.setVisible(false);
            btnEliminarLibro.setVisible(false);
            btnModificarLibro.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibros = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        tfBuscador = new javax.swing.JTextField();
        cbBuscarPor = new javax.swing.JComboBox<>();
        cbBuscador = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbOrdenarPor = new javax.swing.JComboBox<>();
        btnModificarLibro = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(1, 64, 46));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Prestar Libro");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/libros_32x32.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/libros_32x32.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(233, 242, 241));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblLibros.setBorder(new javax.swing.border.MatteBorder(null));
        tblLibros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID libro", "Nombre", "Autor", "Categoria", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblLibros.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tblLibros.setRowHeight(32);
        tblLibros.setSelectionBackground(new java.awt.Color(0, 153, 153));
        tblLibros.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tblLibrosFocusLost(evt);
            }
        });
        tblLibros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLibrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLibros);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 389, 280));

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(1, 64, 46));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/buscar_16x16.png"))); // NOI18N
        jLabel2.setText("Buscar por:");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        tfBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfBuscadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfBuscadorKeyTyped(evt);
            }
        });
        jPanel2.add(tfBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 241, -1));

        cbBuscarPor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbBuscarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No buscar", "id libro", "nombre", "autor", "categoria" }));
        cbBuscarPor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBuscarPorItemStateChanged(evt);
            }
        });
        jPanel2.add(cbBuscarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 124, -1));

        cbBuscador.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbBuscadorItemStateChanged(evt);
            }
        });
        jPanel2.add(cbBuscador, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 241, -1));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(1, 64, 46));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ordenar_32_32.png"))); // NOI18N
        jLabel5.setText("Ordenar por:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        cbOrdenarPor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        cbOrdenarPor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No ordenar", "id libro", "nombre", "autor", "categoria", "precio" }));
        cbOrdenarPor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbOrdenarPorItemStateChanged(evt);
            }
        });
        jPanel2.add(cbOrdenarPor, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 96, -1));

        btnModificarLibro.setBackground(new java.awt.Color(1, 64, 46));
        btnModificarLibro.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        btnModificarLibro.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarLibro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/modificar_32x32.png"))); // NOI18N
        btnModificarLibro.setText("Prestar libro");
        btnModificarLibro.setBorder(null);
        btnModificarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarLibroActionPerformed(evt);
            }
        });
        jPanel2.add(btnModificarLibro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 136, 37));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbBuscarPorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBuscarPorItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() ==  ItemEvent.SELECTED)
        {
            switch(cbBuscarPor.getSelectedIndex())
            {
                case 0:
                    cargarTabla();
                    ocultarBuscadores();
                    tipoBusqueda=0;
                    break;
                case 1:
                    ocultarBuscadores();
                    tfBuscador.setVisible(true);
                    tipoBusqueda=1;
                    break;
                case 2:
                    ocultarBuscadores();
                    tfBuscador.setVisible(true);
                    tipoBusqueda=2;
                    break;
                case 3:
                    ocultarBuscadores();
                    cbBuscador.setVisible(true);
                    cargarAutores();
                    tipoBusqueda=3;
                    break;
                case 4:
                    ocultarBuscadores();
                    cbBuscador.setVisible(true);
                    cargarCategorias();
                    tipoBusqueda=4;
                    break;
            }
            SwingUtilities.updateComponentTreeUI(this);
        }
    }//GEN-LAST:event_cbBuscarPorItemStateChanged

    private void tfBuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscadorKeyTyped
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tfBuscadorKeyTyped

    private void tfBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfBuscadorKeyReleased
        // TODO add your handling code here:
        switch(tipoBusqueda)
        {
            case 1:
                buscarPorId(tfBuscador.getText());
                break;
            case 2:
                buscarPorNombre(tfBuscador.getText());
                break;
        }
    }//GEN-LAST:event_tfBuscadorKeyReleased

    private void cbBuscadorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbBuscadorItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() ==  ItemEvent.SELECTED)
        {
            switch(tipoBusqueda)
            {
                case 3:
                    buscarPorAutor(Integer.parseInt(alAutores.get(cbBuscador.getSelectedIndex()).get(0)));
                    break;
                case 4:
                    buscarPorCategoria(Integer.parseInt(alCategorias.get(cbBuscador.getSelectedIndex()).get(0)));
            }
            
        }
    }//GEN-LAST:event_cbBuscadorItemStateChanged

    private void tblLibrosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tblLibrosFocusLost
        // TODO add your handling code he
    }//GEN-LAST:event_tblLibrosFocusLost

    private void tblLibrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLibrosMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblLibrosMouseClicked

    private void cbOrdenarPorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbOrdenarPorItemStateChanged
        // TODO add your handling code here:
        if(evt.getStateChange() ==  ItemEvent.SELECTED)
        {
            cargarTabla(cbOrdenarPor.getSelectedIndex());
        }
    }//GEN-LAST:event_cbOrdenarPorItemStateChanged

    private void btnModificarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarLibroActionPerformed
        // TODO add your handling code here:
        if (tblLibros.getSelectedRows().length > 0) {
            ModificarLibroDialog jDialog = new ModificarLibroDialog(FormularioPrincipal.contex, true, modeloLibros.getValueAt(tblLibros.getSelectedRow(), 0).toString(),
                modeloLibros.getValueAt(tblLibros.getSelectedRow(), 1).toString(),
                Integer.parseInt(modeloLibros.getValueAt(tblLibros.getSelectedRow(), 4).toString()),
                alLibros.get(tblLibros.getSelectedRow()).get(5), alLibros.get(tblLibros.getSelectedRow()).get(6),
                Double.parseDouble(alLibros.get(tblLibros.getSelectedRow()).get(7)));
            jDialog.setLocationRelativeTo(this);
            jDialog.setVisible(true);

            jDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    cargarTabla();
                }
            });
        } else {
            JOptionPane.showMessageDialog(rootPane, "No se ha seleccionado ningún registro", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarLibroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnModificarLibro;
    private javax.swing.JComboBox<String> cbBuscador;
    private javax.swing.JComboBox<String> cbBuscarPor;
    private javax.swing.JComboBox<String> cbOrdenarPor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblLibros;
    private javax.swing.JTextField tfBuscador;
    // End of variables declaration//GEN-END:variables

    private void cargarTabla() {
        limpiarTabla();
        try{
            ResultSet rsResultado=conexion.ejecutar("select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad, autor.id_autor, categoria.id_categoria, libro.precio, (libro.precio*libro.cantidad) as 'Precio total' from libro inner join autor on autor.id_autor = libro.id_autor INNER JOIN categoria on categoria.id_categoria = libro.id_categoria");

            alLibros=conexion.convertirRsToArrayList(rsResultado);

            for(LinkedList<String> aux : alLibros)
            {
                if(Variables.user.tipoUsuario==1)
                    modeloLibros.addRow(new String[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4),"$"+aux.get(7),"$"+aux.get(8)});
                else
                    modeloLibros.addRow(new String[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4)});
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }
    
    private void cargarTabla(int tipoOrden) {
        limpiarTabla();
        String sql="select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad, autor.id_autor, "
                        + "categoria.id_categoria, libro.precio, (libro.precio*libro.cantidad) as 'Precio total' from libro inner join autor on autor.id_autor = libro.id_autor "
                        + "INNER JOIN categoria on categoria.id_categoria = libro.id_categoria ";
        
        switch(tipoOrden)
        {
            case 1:
                sql+="ORDER BY libro.id_libro ASC";
                break;
                
            case 2:
                sql+="ORDER BY libro.nombre ASC";
                break;
                
            case 3:
                sql+="ORDER BY autor.nombre ASC";
                break;
                
            case 4:
                sql+="ORDER BY categoria.nombre ASC";
                break;
                
            case 5:
                sql+="ORDER BY libro.precio ASC";
                break;
        }
        
        try{
            ResultSet rsResultado=conexion.ejecutar(sql);

            alLibros=conexion.convertirRsToArrayList(rsResultado);

            for(LinkedList<String> aux : alLibros)
            {
                if(Variables.user.tipoUsuario==1)
                    modeloLibros.addRow(new String[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4),"$"+aux.get(7)});
                else
                    modeloLibros.addRow(new String[] {aux.get(0), aux.get(1), aux.get(2), aux.get(3), aux.get(4)});
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void ocultarBuscadores() {
        tfBuscador.setVisible(false);
        cbBuscador.setVisible(false);
    }

    

    private void buscarPorNombre(String nombre) {
        try{
            ResultSet rsResultado=conexion.ejecutar("select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad "
                    + "from libro inner join autor on autor.id_autor = libro.id_autor INNER JOIN categoria on "
                    + "categoria.id_categoria = libro.id_categoria where libro.nombre LIKE '%"+nombre+"%'");

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            limpiarTabla();

            for(LinkedList<String> aux : alResultados)
            {
                modeloLibros.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void limpiarTabla() {
        modeloLibros.setRowCount(0);
    }

    private void buscarPorId(String idLibro) {
        try{
            ResultSet rsResultado=conexion.ejecutar("select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad "
                    + "from libro inner join autor on autor.id_autor = libro.id_autor INNER JOIN categoria on "
                    + "categoria.id_categoria = libro.id_categoria where libro.id_libro LIKE '"+idLibro+"%'");

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            limpiarTabla();

            for(LinkedList<String> aux : alResultados)
            {
                modeloLibros.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void cargarAutores() {
        try{
            ResultSet rsResultado=conexion.ejecutar("select * from autor ORDER BY nombre ASC");

            alAutores=conexion.convertirRsToArrayList(rsResultado);
            
            cbBuscador.removeAllItems();

            for(LinkedList<String> aux : alAutores)
            {
                cbBuscador.addItem(aux.get(1));
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void buscarPorAutor(int idAutor) {
        try{
            ResultSet rsResultado=conexion.ejecutar("select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad "
                    + "from libro inner join autor on autor.id_autor = libro.id_autor INNER JOIN categoria on "
                    + "categoria.id_categoria = libro.id_categoria where libro.id_autor ="+idAutor);

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            limpiarTabla();

            for(LinkedList<String> aux : alResultados)
            {
                modeloLibros.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void cargarCategorias() {
        try{
            ResultSet rsResultado=conexion.ejecutar("select * from categoria ORDER BY nombre ASC");

            alCategorias=conexion.convertirRsToArrayList(rsResultado);
            
            cbBuscador.removeAllItems();

            for(LinkedList<String> aux : alCategorias)
            {
                cbBuscador.addItem(aux.get(1));
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    private void buscarPorCategoria(int idcategoria) {
        try{
            ResultSet rsResultado=conexion.ejecutar("select libro.id_libro, libro.nombre, autor.nombre, categoria.nombre, libro.cantidad "
                    + "from libro inner join autor on autor.id_autor = libro.id_autor INNER JOIN categoria on "
                    + "categoria.id_categoria = libro.id_categoria where libro.id_categoria ="+idcategoria);

            LinkedList<LinkedList<String>> alResultados=conexion.convertirRsToArrayList(rsResultado);
            
            limpiarTabla();

            for(LinkedList<String> aux : alResultados)
            {
                modeloLibros.addRow(aux.toArray());
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(rootPane, "error: "+e);
        }
    }

    
}