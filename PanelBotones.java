package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * @author PERSONAL
 *
 */
public class PanelBotones extends JPanel implements ActionListener

{
   private Interfaz principal;
   private JButton reiniciar;
    private JButton cargarJuego;
    private JButton estadisticas;
    private JButton opcion1;
    private JButton opcion2;
    /**
     * 
     */
    public final static String CARGAR = "Cargar";
    /**
     * 
     */
    public final static String REINICIAR = "Reiniciar";
    /**
     * 
     */
    public final static String ESTADISTICAS = "Estadisticas";
    /**
     * 
     */
    public final static String OPCION_1 = "Opción 1";
    /**
     * 
     */
    public final static String OPCION_2 = "Opción 2";
    
    /**
     * @param interfaz 
     * 
     */
    public PanelBotones(Interfaz interfaz)
      {
        principal = interfaz;
        
        setLayout( new GridLayout( 1, 5 ) );
        setPreferredSize( new Dimension( 0, 50) );
        TitledBorder ti = new TitledBorder( "Opciones" );
        setBorder( ti );
        cargarJuego = new JButton( CARGAR );
        cargarJuego.setActionCommand( CARGAR );
        cargarJuego.addActionListener( this );
        
        reiniciar = new JButton(REINICIAR );
        reiniciar.setActionCommand( REINICIAR );
        reiniciar.addActionListener( this );
        
        estadisticas = new JButton(ESTADISTICAS );
        estadisticas.setActionCommand( ESTADISTICAS );
        estadisticas.addActionListener( this );
        
        opcion1 = new JButton( OPCION_1);
        opcion1.setActionCommand( OPCION_1 );
        opcion1.addActionListener( this );
        
        opcion2 = new JButton( OPCION_2);
        opcion2.setActionCommand( OPCION_2 );
        opcion2.addActionListener( this );
        
        add(cargarJuego);
        add(reiniciar);
        add(estadisticas);
        add(opcion1);
        add(opcion2);
      }

    public void actionPerformed( ActionEvent arg0 )
    {
        // TODO Auto-generated method stub
        String co = arg0.getActionCommand( );
        if (co.equals( CARGAR ))
        {
            try
            {
                principal.cargar( );
            }
            catch (Exception e)
            {
               //JOptionPane.showMessageDialog( this, e.getMessage( ), "error", JOptionPane.ERROR_MESSAGE ); 
            }
        }
        else if(co.equals( REINICIAR ))
        {
            
                principal.reiniciar( );
            
        }
        else if(co.equals( ESTADISTICAS ))
        {
            principal.darEstadisticas( );
        }
        else if (co.equals( OPCION_1 ))
        {
            principal.opcion1( );
        }
        else if (co.equals( OPCION_2 ))
        {
            principal.opcion2( );
        }

    }
}
