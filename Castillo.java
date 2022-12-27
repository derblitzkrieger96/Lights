package uniandes.cupi2.lucesApagadas.interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import uniandes.cupi2.lucesApagadas.mundo.Mundo;

/**
 * @author PERSONAL
 *
 */
public class Castillo extends  JPanel implements ActionListener
{
   private Interfaz principal;
   private JButton[][] botones;
   private int tamanho;
    
    /**
     * @param interfaz 
     * 
     */
    public Castillo(Interfaz interfaz)
   {
       principal = interfaz;
       
       
   }
    /**
     * @param matriz
     * @throws Exception 
     */
    public void pintarCastillo(int[][] matriz) throws Exception
    {
        removeAll();
        try
        {
            tamanho = principal.darTamano( );
            //tamanho = 7;
            botones = new JButton[tamanho][tamanho];
          
          setLayout( new GridLayout( tamanho, tamanho ) );
          setPreferredSize( new Dimension( tamanho*50, tamanho*50) );
          
          for(int i=0; i< tamanho; i++)
          {
              for (int j= 0; j<tamanho; j++)
              {
                  
              
              String ruta=null;
              if(matriz[i][j]==Mundo.CASILLA_APAGADA)
              {
                 ruta = "data/imagenes/ventana_apagada.png"; 
              }
                if (matriz[i][j]==Mundo.CASILLA_APAGADA_CON_FANTASMA)    
              {
                 ruta = "data/imagenes/ventana_apagada_fantasma.png"; 
              }
                if (matriz[i][j]==1)
              {
                  ruta = "data/imagenes/ventana_encendida.png";
              }
               if (matriz[i][j]==Mundo.CASILLA_PRENDIDA_CON_FANTASMA)
              {
                  ruta = "data/imagenes/ventana_encendida_fantasma.png";
              }
              ImageIcon ic = new ImageIcon(ruta );
              botones[i][j] = new JButton( );
              botones[i][j].setIcon( ic );
              String com = i+""+j;
              botones[i][j].setActionCommand( com );
              botones[i][j].addActionListener( this );
              //botones[i][j].setPreferredSize( new Dimension( ic.getIconWidth( ), ic.getIconHeight( ) ) );
              add(botones[i][j]);
              System.out.println( ""+ruta+","+matriz[i][j]);
              
          }
          
          }
        revalidate( );
        }
        catch(Exception e)
        {
           throw new Exception( "No se han podido inicializar los botones" ) ;
        }
    }
    public void actionPerformed( ActionEvent arg0 )
    {
        String comando = arg0.getActionCommand( );
        String[] posi = comando.split( "" );
        int x = Integer.parseInt( posi[0] );
        int y = Integer.parseInt( posi[1] );
        try
        {
            principal.jugar( x, y );
        }
        catch( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        
    }
}
