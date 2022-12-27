package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import uniandes.cupi2.lucesApagadas.mundo.Mundo;

/**
 * @author PERSONAL
 *
 */
public class Interfaz extends JFrame
{
    private Mundo mundo;
    private Castillo panelCastillo;
    private PanelBotones panelBotones;
    /**
     * 
     */
    public Interfaz()
    {
        setLayout( new BorderLayout( 50, 50 ) );
        setSize(650, 700);
        setTitle( "Luces que se apagan." );
        ImageIcon im = new ImageIcon("data/imagenes/titulo.jpg");
        setResizable( false );
        setLocation( 350, 10 );
        
        JLabel imagen = new JLabel( );
        imagen.setIcon( im );
        add(imagen, BorderLayout.NORTH);
        
        mundo = new Mundo();
        panelBotones = new PanelBotones( this );
        panelCastillo = new Castillo(this);
        add(panelCastillo, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
    /**
     * @param args
     */
    public static void main( String[] args )
    {
        Interfaz interfaz = new Interfaz();
        interfaz.setVisible( true );
        // TODO Auto-generated method stub

    }
    /**
     * @throws Exception
     */
    public void cargar () throws Exception
    {
        JFileChooser fc = new JFileChooser( "./data" );
        int respuesta = fc.showOpenDialog( this );
        if (respuesta==JFileChooser.APPROVE_OPTION)
        {
            try
            {
                File arch = fc.getSelectedFile( );
                mundo.cargarMundo( arch );
                panelCastillo.pintarCastillo( mundo.darMatriz( ) );
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            }
        }
        else if(respuesta==JFileChooser.CANCEL_OPTION)
        {
            JOptionPane.showMessageDialog( this, "Debe seleccionar un archivo de configuración para poder jugar", "Luces Apagadas", JOptionPane.WARNING_MESSAGE );
        }
    }
    /**
     * @return tamano
     */
    public int darTamano()
    {
        return mundo.darTamano( );
    }
    /**
     * @param x
     * @param y
     * @throws Exception 
     */
    public void jugar(int x, int y) throws Exception
    {
        mundo.jugar( x, y );
        panelCastillo.pintarCastillo( mundo.darMatriz( ) );
        if(mundo.darcantidadVentEncendidas( )==0)
        {
            String s = System.getProperty( "line.separator" );
            
            JOptionPane.showMessageDialog( this, "¡Felicitaciones!"+s+"Ganó el Juego", "Felicitaciones", JOptionPane.INFORMATION_MESSAGE );
        }
    }
    /**
     * 
     * 
     */
    public void reiniciar() 
    {
        try
        {
            mundo.reiniciar( );
            panelCastillo.pintarCastillo( mundo.darMatriz( ) );
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Reiniciar Juego", JOptionPane.ERROR_MESSAGE );
        }
        
    }
    /**
     * 
     */
    public void darEstadisticas()
    {
       try 
       {
          int in = mundo.darcantidadVentEncendidas( );
          int inn = mundo.darfilaConMasVentanasEncendidas( );
          if(in>0)
          {
              String s = System.getProperty( "line.separator" );
              JOptionPane.showMessageDialog( this,"Cantidad de ventanas encendidas: "+in+"."+s+"La fila con más ventanas encendidas: "+inn+"." , "estadísticas", JOptionPane.INFORMATION_MESSAGE );
          
          }
          else
          {
              JOptionPane.showMessageDialog( this,"El juego ha terminado, no hay ventanas encendidas", "Estadísticas", JOptionPane.ERROR_MESSAGE );
          }
       }
       catch (Exception e)
       {
           JOptionPane.showMessageDialog( this, e.getMessage( ),"Estadísticas" , JOptionPane.ERROR_MESSAGE );
       }
    }
    /**
     * 
     */
    public void opcion1()
    {
       JOptionPane.showMessageDialog( this, mundo.opcion1( ), "Opción 1.", JOptionPane.INFORMATION_MESSAGE );
    }
    /**
     * 
     */
    public void opcion2()
    {
        JOptionPane.showMessageDialog( this, mundo.opcion2( ), "Opción 2.", JOptionPane.INFORMATION_MESSAGE );
    }

}
