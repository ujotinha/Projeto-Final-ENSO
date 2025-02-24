
import java.awt.Cursor;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author João
 */
public class TelaHistEmp extends javax.swing.JFrame {

    UsuarioDAO verificarAdm = new UsuarioDAO();

    /**
     * Creates new form TelaHistEmp
     */
    public TelaHistEmp() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotInicio = new javax.swing.JButton();
        BotVoltar = new javax.swing.JButton();
        BotMinhaConta = new javax.swing.JButton();
        BotSair = new javax.swing.JButton();
        BotCatalogo = new javax.swing.JButton();
        BotGerCatalogo = new javax.swing.JButton();
        ImgGerCatalogo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BotInicio.setBorder(null);
        BotInicio.setContentAreaFilled(false);
        BotInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotInicioMouseEntered(evt);
            }
        });
        BotInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotInicioActionPerformed(evt);
            }
        });
        getContentPane().add(BotInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 155, 190, 38));

        BotVoltar.setBorder(null);
        BotVoltar.setContentAreaFilled(false);
        BotVoltar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotVoltarMouseEntered(evt);
            }
        });
        BotVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotVoltarActionPerformed(evt);
            }
        });
        getContentPane().add(BotVoltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(637, 500, 103, 40));

        BotMinhaConta.setBorder(null);
        BotMinhaConta.setContentAreaFilled(false);
        BotMinhaConta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotMinhaContaMouseEntered(evt);
            }
        });
        BotMinhaConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotMinhaContaActionPerformed(evt);
            }
        });
        getContentPane().add(BotMinhaConta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 192, 35));

        BotSair.setBorder(null);
        BotSair.setContentAreaFilled(false);
        BotSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotSairMouseEntered(evt);
            }
        });
        BotSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotSairActionPerformed(evt);
            }
        });
        getContentPane().add(BotSair, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 190, 30));

        BotCatalogo.setBorder(null);
        BotCatalogo.setContentAreaFilled(false);
        BotCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotCatalogoMouseEntered(evt);
            }
        });
        BotCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotCatalogoActionPerformed(evt);
            }
        });
        getContentPane().add(BotCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 195, 190, 38));

        BotGerCatalogo.setBorder(null);
        BotGerCatalogo.setContentAreaFilled(false);
        BotGerCatalogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BotGerCatalogoMouseEntered(evt);
            }
        });
        BotGerCatalogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotGerCatalogoActionPerformed(evt);
            }
        });
        getContentPane().add(BotGerCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 236, 192, 40));
        if (verificarAdm.obterIs_AdmAtual()==true){
            BotGerCatalogo.setEnabled(true);
        } else {
            BotGerCatalogo.setEnabled(false);
        }

        ImgGerCatalogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagemTelas/BotGerenciar.png"))); // NOI18N
        getContentPane().add(ImgGerCatalogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 236, 192, 40));
        if (verificarAdm.obterIs_AdmAtual()==true){
            ImgGerCatalogo.setVisible(true);
        } else {
            ImgGerCatalogo.setVisible(false);
        }

        jTable1.setBackground(new java.awt.Color(226, 226, 226));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Titulo", "Autor", "Genero", "Nota", "Situação"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 570, 370));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ImagemTelas/TelaHistEmp.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotInicioMouseEntered
        // TODO add your handling code here:
        BotInicio.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotInicioMouseEntered

    private void BotInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotInicioActionPerformed
        // TODO add your handling code here:
        new TelaInicial().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BotInicioActionPerformed

    private void BotVoltarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotVoltarMouseEntered
        // TODO add your handling code here:
        BotVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotVoltarMouseEntered

    private void BotVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotVoltarActionPerformed
        // TODO add your handling code here:
        new TelaMinhaConta().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BotVoltarActionPerformed

    private void BotMinhaContaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotMinhaContaMouseEntered
        // TODO add your handling code here:
        BotMinhaConta.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotMinhaContaMouseEntered

    private void BotMinhaContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotMinhaContaActionPerformed
        // TODO add your handling code here:
        new TelaMinhaConta().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BotMinhaContaActionPerformed

    private void BotSairMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotSairMouseEntered
        // TODO add your handling code here:
        BotSair.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotSairMouseEntered

    private void BotSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotSairActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Você está saindo!");
        setVisible(false);
        new TelaCadastro().setVisible(true);

    }//GEN-LAST:event_BotSairActionPerformed

    private void BotCatalogoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotCatalogoMouseEntered
        // TODO add your handling code here:
        BotCatalogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotCatalogoMouseEntered

    private void BotCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotCatalogoActionPerformed
        // TODO add your handling code here:
        new TelaCatalogo().setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_BotCatalogoActionPerformed

    private void BotGerCatalogoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BotGerCatalogoMouseEntered
        // TODO add your handling code here:
        BotGerCatalogo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_BotGerCatalogoMouseEntered

    private void BotGerCatalogoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotGerCatalogoActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        new TelaGerCatalogo().setVisible(true);
    }//GEN-LAST:event_BotGerCatalogoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotCatalogo;
    private javax.swing.JButton BotGerCatalogo;
    private javax.swing.JButton BotInicio;
    private javax.swing.JButton BotMinhaConta;
    private javax.swing.JButton BotSair;
    private javax.swing.JButton BotVoltar;
    private javax.swing.JLabel ImgGerCatalogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
