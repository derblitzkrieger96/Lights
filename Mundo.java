package uniandes.cupi2.lucesApagadas.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

/**
 * @author PERSONAL
 *
 */
public class Mundo
{
   private int tamano = 7;
   private int matriz[][];
   private String[] pos;
   private int matrizInicial[][];
   private int contadorJuego;
   private boolean gano;
 
   
   /**
 * 
 */
public final static int CASILLA_APAGADA = 0;
   /**
 * 
 */
public final static int CASILLA_ENCENDIDA = 1;
   /**
 * 
 */
public final static int CASILLA_APAGADA_CON_FANTASMA = 2;
   /**
 * 
 */
public final static int CASILLA_PRENDIDA_CON_FANTASMA = 3;
    /**
 * 
 */
    public Mundo()
   {
      tamano =7;
      matriz = new int[tamano][tamano];
      matrizInicial = new int[tamano][tamano];
      contadorJuego =0;
      
              
   }
    /**
     * @param ar
     * @throws Exception 
     */
    public void cargarMundo(File ar) throws Exception
    {
      Properties pro = new Properties( );  
      FileInputStream in = new FileInputStream( ar );
      try
      {
          pro.load( in );
          in.close( );
          tamano = 7;
          
          for(int i=0; i<tamano; i++)
          {
              String fila =  pro.getProperty( "cupiCastillo.fila"+(i+1 ));
              pos = fila.split( "" );
            
              for(int j=0; j<tamano; j++)
              {
                  matriz[i][j]=Integer.parseInt( pos[j] );
                  matrizInicial[i][j] = Integer.parseInt( pos[j] );
                  contadorJuego = 1;
              }
          }
      }
      catch (Exception e)
      {
          throw new Exception( "El archivo no tiene el formato esperado" );
      }
    }
    /**
     * @return matriz
     */
    public int[][]darMatriz ()
    {
        return matriz;
    }
    
    /**
     * @return tamano
     */
    public int darTamano()
    {
        return tamano;
    }
    /**
     * @param x 
     * @param y 
     * 
     */
    public void jugar(int x, int y)
    {
        for(int i=0; i<matriz.length; i++)
        {
            for(int j=0; j<matriz.length; j++)
            {
               if(estaApagadaConFant( i, j ))
               {
                   apagarVentana( i, j );
               }
               else if(estaEncendidaConFant( i, j ))
               {
                   encenderVentana( i, j );
               }
               
            }
        }
        
        {
            if(estaApagada( x, y ))
            {
               encenderVenConFant( x, y );
            }
            else apagarVenConFant( x, y );
            
            {
                if(x-1>=0)
                {
                    if(estaApagada( x-1, y ))
                    {
                      encenderVentana( x-1, y );;  
                    }
                    else apagarVentana( x-1, y );
                }
                
                if(x+1<=matriz.length-1)
                {
                    if(estaApagada( x+1, y ))
                    {
                        encenderVentana( x+1, y );
                    }
                    else apagarVentana( x+1, y );
                }
               if(y-1>=0)
               {
                   if(estaApagada( x, y-1 ))
                   {
                       encenderVentana( x, y-1 );
                   }
                   else apagarVentana( x, y-1 );
               }
                if (y+1<=matriz.length-1)
                {
                    if(estaApagada( x, y+1 ))
                    {
                        encenderVentana( x, y+1 );
                    }
                    else apagarVentana( x, y+1 ); 
                }
            }
            
             
        }
        
       
    }
    /**
     * @param x
     * @param y
     */
    public void encenderVentana(int x, int y)
    {
        matriz[x][y]=CASILLA_ENCENDIDA;
    }
    /**
     * @param x
     * @param y
     */
    public void encenderVenConFant(int x, int y)
    {
        matriz[x][y]=CASILLA_PRENDIDA_CON_FANTASMA;
    }
    /**
     * @param x
     * @param y
     */
    public void apagarVentana(int x, int y)
    {
        matriz[x][y]=CASILLA_APAGADA;
    }
    /**
     * @param x
     * @param y
     */
    public void apagarVenConFant(int x, int y)
    {
        matriz[x][y]=CASILLA_APAGADA_CON_FANTASMA;
    }
    /**
     * @param x
     * @param y
     * @return si la ventana esta apagada
     */
    public boolean estaApagada(int x, int y)
    {
        boolean si= false;
        if(matriz[x][y]==CASILLA_APAGADA)
        {
            si = true;
        }
        return si;
    }
    /**
     * @param x
     * @param y
     * @return si la ventana esta apagada con fantasma
     */
    public boolean estaApagadaConFant(int x, int y)
    {
        boolean si= false;
        if(matriz[x][y]==CASILLA_APAGADA_CON_FANTASMA)
        {
            si = true;
        }
        return si;
    }
   
    /**
     * @param x
     * @param y
     * @return si la ventana esta encendida
     */
    public boolean estaEncendida(int x, int y)
    {
        boolean si = false;
        if(matriz[x][y]==CASILLA_ENCENDIDA)
        {
            si = true;
        }
        return si;
    }
    /**
     * @param x
     * @param y
     * @return si la ventana esta encendida con fantasma
     */
    public boolean estaEncendidaConFant(int x, int y)
    {
        boolean si = false;
        if(matriz[x][y]==CASILLA_PRENDIDA_CON_FANTASMA)
        {
            si = true;
        }
        return si;
    }
    /**
     * @throws Exception 
     * 
     */
    public void reiniciar() throws Exception
    {
        for( int i = 0; i < matriz.length; i++ )
        {
            for( int j = 0; j < matriz.length; j++ )
            {
                
                if(contadorJuego==0)
                {
                    throw new Exception( "No hay ningún juego en curso" );
                }
                else
                {
                    matriz[i][j]=matrizInicial[i][j];
                }
            }
        }
    }
    /**
     * @return la cantidad de filas encenidas
     * @throws Exception 
     */
    public int darcantidadVentEncendidas() throws Exception
    {
       if(contadorJuego>0)
       {
           int contador = 0;
           for( int i = 0; i < matriz.length; i++ )
              
           {
              for( int j = 0; j < matriz.length; j++ )
              {
                  if(estaEncendida( i, j )||estaEncendidaConFant( i, j ))
                  {
                      contador++;
                  }
              }
           }
       return contador;
       }
       else throw new Exception( "No hay ningún juego en curso" );
    }
    /**
     * @return la fila con mas ventanas encendidas
     * @throws Exception 
     */
    public int darfilaConMasVentanasEncendidas() throws Exception
    {
        if(contadorJuego>0)
        {
            int filaMayor =0;
            int mayor =0;
             for( int i = 0; i < matriz.length; i++ )
             {
                 int contador =0;
                 for( int j = 0; j < matriz.length; j++ )
                 {
                   if(estaEncendida( i, j )||estaEncendidaConFant( i, j ))
                   {
                    contador++; 
                   }
                 }
                 if(contador>mayor)
                 {
                     mayor = contador;
                     filaMayor =i+1;
                 }
             }
             return filaMayor;
        }
        else throw new Exception( "No hay ningún juego en curso" );
    }
    /**
     * @return cantidad deventanas apagadas
     */
   public String opcion1()
   {
       return "Respuesta 1.";
   }
   /**
 * @return opción 2
 */
public String opcion2()
   {
       return "Respuesta 2.";
   }
    
   
}
