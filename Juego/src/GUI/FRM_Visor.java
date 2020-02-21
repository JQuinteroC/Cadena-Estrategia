package GUI;

import Logica.Builder;
import Logica.ConstructorHuevo;
import Logica.Huevo;
import Logica.Mascota;
import Logica.Personaje;
import Logica.Poblacion;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FRM_Visor extends javax.swing.JFrame implements KeyListener {

    ArrayList<Personaje> p = new ArrayList<>();
    ArrayList<Personaje> huevos = new ArrayList<>();
    Poblacion grupo = new Poblacion("Grupo1");
    Poblacion grupo2 = new Poblacion("Grupo2");

    public FRM_Visor(Personaje p, Personaje huevo) {
        // Instancia de la ventana
        initComponents();
        super.setLocationRelativeTo(null);

        // Configuración del personaje y grupo
        p.setPanel(panel);
        this.p.add(p);
        this.p.add(p.clone());
        this.p.add(p.clone());
        this.p.add(p.clone());
        this.p.get(1).setDesplazamientoVertical(45);
        this.p.get(1).setDesplazamientoHorizontal(460);
        this.p.get(2).setDesplazamientoVertical(-10);
        this.p.get(2).setDesplazamientoHorizontal(530);
        this.p.get(3).setDesplazamientoVertical(-10);
        this.p.get(3).setDesplazamientoHorizontal(390);
        panel.add(this.p.get(0));
        panel.add(this.p.get(1));
        panel.add(this.p.get(2));
        panel.add(this.p.get(3));

        //se crean las poblaciones del patron Composite
        grupo.addPersonaje(this.p.get(0));
        grupo2.addPersonaje(this.p.get(1));
        grupo2.addPersonaje(this.p.get(2));
        grupo2.addPersonaje(this.p.get(3));

        //Metiendo al huevito
        huevo.setPanel(panel);
        this.huevos.add(huevo);
        this.huevos.get(0).setDesplazamientoVertical(250);
        this.huevos.get(0).setDesplazamientoHorizontal(480);
        panel.add(this.huevos.get(0));

        // Integración del listener 
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    // <editor-fold defaultstate="collapsed" desc="Key listeners">  
    @Override
    public void keyPressed(KeyEvent e) {
        //JOptionPane.showMessageDialog(null, e.getKeyChar());
        switch (e.getKeyCode()) { //Personaje Individual
            case 39:
                grupo.operacion(1);
                break;
            case 38:
                grupo.operacion(2);
                break;
            case 37:
                grupo.operacion(3);
                break;
            case 40:
                grupo.operacion(4);
                break;
            case 97:
                grupo.operacion(5);
                break;
            case 98:
                grupo.operacion(7);
                break;
            case 99:
                grupo.operacion(6);
                break;
            default:
                break;
        }
        switch (e.getKeyChar()) { //Personaje clonado
            case 'w':
                grupo2.operacion(2);
                break;
            case 'a':
                grupo2.operacion(3);
                break;
            case 's':
                grupo2.operacion(4);
                break;
            case 'd':
                grupo2.operacion(1);
                break;
            case 'e':
                grupo2.operacion(5);
                break;
            case 'r':
                grupo2.operacion(6);
                break;
            case 'f':
                grupo2.operacion(7);
                break;
            case 'q':
                FRM_Selector selector = null;
                try {
                    selector = FRM_Selector.getInstance();
                } catch (IOException ex) {
                    Logger.getLogger(FRM_Visor.class.getName()).log(Level.SEVERE, null, ex);
                }
                selector.setVisible(true);

                // Se interrumpe el hilo
                grupo.operacion(8);
                grupo2.operacion(8);
                ;
                this.dispose();
                break;
            case '+':
                boolean agregado = false;
                for (int i = 0; i < 4; i++) {
                    if (grupo.isHere(p.get(i))) {
                    } else {
                        grupo2.deletePerson(p.get(i));
                        grupo.addPersonaje(this.p.get(i));
                        agregado = true;
                        i = 10;
                    }
                }
                if (!agregado) {
                    JOptionPane.showMessageDialog(null, "No hay mas personajes para agregar");
                }

                break;
            case '-':
                agregado = false;
                for (int i = 3; i >= 0; i--) {
                    if (grupo2.isHere(p.get(i))) {
                    } else {
                        grupo.deletePerson(p.get(i));
                        grupo2.addPersonaje(this.p.get(i));
                        agregado = true;
                        i = -1;
                    }
                }
                if (!agregado) {
                    JOptionPane.showMessageDialog(null, "No hay mas personajes para agregar");
                }
                break;
            default:
                break;
        }
        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < huevos.size(); j++) {
                if (colision(p.get(i), huevos.get(j)) == true) {
                    int team = 0;
                    huevos.get(j).interrumpir();
                    panel.remove(huevos.get(j));
                    huevos.remove(j);
                    p.get(i).interrumpir();
                    panel.remove(p.get(i));
                    if (grupo.isHere(p.get(i))) {
                        grupo.deletePerson(p.get(i));
                        team = 1;
                    } else {
                        grupo2.deletePerson(p.get(i));
                        team = 2;
                    }
                    Personaje mas;
                    try {
                        mas = new Mascota(p.get(i), panel);
                        p.set(i, mas);
                        if (team==1) {
                            grupo.addPersonaje(mas);
                        } else {
                            grupo2.addPersonaje(mas);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(FRM_Visor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    panel.add(p.get(i));
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }// </editor-fold>  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setOpaque(false);

        org.jdesktop.layout.GroupLayout panelLayout = new org.jdesktop.layout.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 1200, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/GUI/catarata.png"))); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 600));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JPanel panel;
    // End of variables declaration//GEN-END:variables

    private boolean colision(Personaje p, Personaje huevo) {
        System.out.println("X Duende: " + p.getDesplazamientoHorizontal() + ", Y Duende: " + p.getDesplazamientoVertical());
        System.out.println("X Huevo: " + huevo.getDesplazamientoHorizontal() + ", Y Huevo: " + huevo.getDesplazamientoVertical());
        if ((p.getDesplazamientoHorizontal() + p.getAncho() > huevo.getDesplazamientoHorizontal()) & (p.getDesplazamientoHorizontal() < huevo.getDesplazamientoHorizontal() + (huevo.getAncho())) & (p.getDesplazamientoVertical() > huevo.getDesplazamientoVertical()) & (p.getDesplazamientoVertical() < huevo.getDesplazamientoVertical() + (huevo.getAlto()))) {
            return true;
        } else {
            return false;
        }
    }
}
