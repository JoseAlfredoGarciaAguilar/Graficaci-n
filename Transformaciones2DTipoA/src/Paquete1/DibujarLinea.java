package Paquete1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class DibujarLinea extends JFrame {
	private static final long serialVersionUID = 1L;
	//CREAR ARREGLOS PARA GUARDAR LOS DATOS
	static double trazar_linea_original[][] = new double [2][2], trazar_linea_tranformada[][] = new double[2][2]; //LINEA
	static double trazar_triangulo_original[][] = new double [3][2], trazar_triangulo_tranformado[][]= new double [3][2]; //TRIANGULO
	static double trazar_fig_4lados_original[][] = new double [4][2], trazar_fig_4lados_transformado[][] = new double [4][2]; //FIGURAS DE 4 LADOS (CUADRADO, RECTANGULO, ROMBO, ROMBOIDE, ETC)
	
	//CREAR LA VENTANA PARA DIBUJAR
	public DibujarLinea() {
		super("TRANSFORMACIONES 2D - García Aguilar y Moreno Pérez");
		setSize(1200, 700);
		setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setLocationRelativeTo(null);
	    
	    //TEXTO DENTRO DE LA VENTANA
	    JPanel panel = new JPanel();
	    panel.setLayout(null);
	    this.getContentPane().add(panel);
	    
	    JLabel textito = new JLabel();
	    textito.setText("FIGURA ORIGINAL");
	    textito.setBounds(0, 0, 500, 50);
	    textito.setForeground(Color.RED);
	    textito.setFont(new Font("Arial",3,35));
	    panel.add(textito);
	    
	    JLabel textito2 = new JLabel();
	    textito2.setText("FIGURA TRANSFORMADA");
	    textito2.setBounds(0, 0, 500, 110);
	    textito2.setForeground(Color.BLUE);
	    textito2.setFont(new Font("Arial",3,35));
	    panel.add(textito2);
	}
	
	void dibujarLasLineas(Graphics g) {
		Scanner entrada = new Scanner(System.in);
		String eleccion;
		System.out.println();
		System.out.println("- PROGRAMA TRANSFORMACIONES 2D, TIPO A -");
		System.out.println("T = Traslación");
		System.out.println("R = Rotación");
		System.out.println("RE = Reflexión s/ una línea con x=y");
		do {
			System.out.print("a) Tipo de transformación = ");
			eleccion = entrada.next();
			if(!eleccion.equals("T")&&!eleccion.equals("t")&&!eleccion.equals("R")&&!eleccion.equals("r")&&!eleccion.equals("RE")&&!eleccion.equals("re")) {
				System.out.println("Favor de ingresar una opción válida en el menú");
			}
		}while(!eleccion.equals("T")&&!eleccion.equals("t")&&!eleccion.equals("R")&&!eleccion.equals("r")&&!eleccion.equals("RE")&&!eleccion.equals("re"));
		
		//TRASLACIÓN
		if(eleccion.equals("T")||eleccion.equals("t")) {
			System.out.println();
			System.out.println("Ha elegido Traslación");
			System.out.println("b) Teclear parámetros de transformación");
			System.out.print("Tx = ");
			double Tx = entrada.nextDouble();
			System.out.print("Ty = ");
			double Ty = entrada.nextDouble();
			
			int num_vert;
			System.out.println();
			System.out.println("c) Introducir las coordenadas de los vértices");
			do {
				System.out.print("Número de vértices de la figura (2: Linea, 3: Triángulo, 4: Figura de 4 Lados) = ");
				num_vert = entrada.nextInt();
				if(num_vert==2) { //LINEA
					System.out.println("Ha elegido dibujar una Línea");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ ORIGINAL
					trazar_linea_original[0][0] = x1;
					trazar_linea_original[0][1] = y1;
					trazar_linea_original[1][0] = x2;
					trazar_linea_original[1][1] = y2;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					//X' = X+TX            Y'=Y+TY
					double x1_prima = x1 + Tx;
					double y1_prima = y1 + Ty;
					double x2_prima = x2 + Tx;
					double y2_prima = y2 + Ty;
					
					trazar_linea_tranformada[0][0] = x1_prima;
					trazar_linea_tranformada[0][1] = y1_prima;
					trazar_linea_tranformada[1][0] = x2_prima;
					trazar_linea_tranformada[1][1] = y2_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_linea_original[0][0], trazar_linea_original[0][1], trazar_linea_original[1][0], trazar_linea_original[1][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_linea_tranformada[0][0], trazar_linea_tranformada[0][1], trazar_linea_tranformada[1][0], trazar_linea_tranformada[1][1]));
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
				}
				if(num_vert==3) { //TRIANGULO
					System.out.println("Ha elegido dibujar un Triángulo");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					System.out.println("Captura de V3:");
					System.out.print("X3 = ");
					double x3 = entrada.nextDouble();
					System.out.print("Y3 = ");
					double y3 = entrada.nextDouble();
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ
					trazar_triangulo_original[0][0] = x1;
					trazar_triangulo_original[0][1] = y1;
					trazar_triangulo_original[1][0] = x2;
					trazar_triangulo_original[1][1] = y2;
					trazar_triangulo_original[2][0] = x3;
					trazar_triangulo_original[2][1] = y3;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					//X' = X+TX            Y'=Y+TY
					double x1_prima = x1 + Tx;
					double y1_prima = y1 + Ty;
					double x2_prima = x2 + Tx;
					double y2_prima = y2 + Ty;
					double x3_prima = x3 + Tx;
					double y3_prima = y3 + Ty;
					
					trazar_triangulo_tranformado[0][0] = x1_prima;
					trazar_triangulo_tranformado[0][1] = y1_prima;
					trazar_triangulo_tranformado[1][0] = x2_prima;
					trazar_triangulo_tranformado[1][1] = y2_prima;
					trazar_triangulo_tranformado[2][0] = x3_prima;
					trazar_triangulo_tranformado[2][1] = y3_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[0][0], trazar_triangulo_original[0][1], trazar_triangulo_original[1][0], trazar_triangulo_original[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[0][0], trazar_triangulo_original[0][1], trazar_triangulo_original[2][0], trazar_triangulo_original[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[1][0], trazar_triangulo_original[1][1], trazar_triangulo_original[2][0], trazar_triangulo_original[2][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[0][0], trazar_triangulo_tranformado[0][1], trazar_triangulo_tranformado[1][0], trazar_triangulo_tranformado[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[0][0], trazar_triangulo_tranformado[0][1], trazar_triangulo_tranformado[2][0], trazar_triangulo_tranformado[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[1][0], trazar_triangulo_tranformado[1][1], trazar_triangulo_tranformado[2][0], trazar_triangulo_tranformado[2][1]));
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					System.out.println("V3: X3: " + x3 + ", Y3: " + y3);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
					System.out.println("V3: X3': " + x3_prima + ", Y3': " + y3_prima);
				}
				if(num_vert==4){ //FIGURAS DE 4 LADOS (CUADRADO, RECTANGULO, ROMBO, ROMBOIDE, ETC)
					System.out.println("Ha elegido dibujar una Figura de 4 Lados");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					System.out.println("Captura de V3:");
					System.out.print("X3 = ");
					double x3 = entrada.nextDouble();
					System.out.print("Y3 = ");
					double y3 = entrada.nextDouble();
					
					System.out.println("Captura de V4:");
					System.out.print("X4 = ");
					double x4 = entrada.nextDouble();
					System.out.print("Y4 = ");
					double y4 = entrada.nextDouble();
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ
					trazar_fig_4lados_original[0][0] = x1;
					trazar_fig_4lados_original[0][1] = y1;
					trazar_fig_4lados_original[1][0] = x2;
					trazar_fig_4lados_original[1][1] = y2;
					trazar_fig_4lados_original[2][0] = x3;
					trazar_fig_4lados_original[2][1] = y3;
					trazar_fig_4lados_original[3][0] = x4;
					trazar_fig_4lados_original[3][1] = y4;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					//X' = X+TX            Y'=Y+TY
					double x1_prima = x1 + Tx;
					double y1_prima = y1 + Ty;
					double x2_prima = x2 + Tx;
					double y2_prima = y2 + Ty;
					double x3_prima = x3 + Tx;
					double y3_prima = y3 + Ty;
					double x4_prima = x4 + Tx;
					double y4_prima = y4 + Ty;
					
					trazar_fig_4lados_transformado[0][0] = x1_prima;
					trazar_fig_4lados_transformado[0][1] = y1_prima;
					trazar_fig_4lados_transformado[1][0] = x2_prima;
					trazar_fig_4lados_transformado[1][1] = y2_prima;
					trazar_fig_4lados_transformado[2][0] = x3_prima;
					trazar_fig_4lados_transformado[2][1] = y3_prima;
					trazar_fig_4lados_transformado[3][0] = x4_prima;
					trazar_fig_4lados_transformado[3][1] = y4_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[0][0], trazar_fig_4lados_original[0][1], trazar_fig_4lados_original[1][0], trazar_fig_4lados_original[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[1][0], trazar_fig_4lados_original[1][1], trazar_fig_4lados_original[3][0], trazar_fig_4lados_original[3][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[3][0], trazar_fig_4lados_original[3][1], trazar_fig_4lados_original[2][0], trazar_fig_4lados_original[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[2][0], trazar_fig_4lados_original[2][1], trazar_fig_4lados_original[0][0], trazar_fig_4lados_original[0][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[0][0], trazar_fig_4lados_transformado[0][1], trazar_fig_4lados_transformado[1][0], trazar_fig_4lados_transformado[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[1][0], trazar_fig_4lados_transformado[1][1], trazar_fig_4lados_transformado[3][0], trazar_fig_4lados_transformado[3][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[3][0], trazar_fig_4lados_transformado[3][1], trazar_fig_4lados_transformado[2][0], trazar_fig_4lados_transformado[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[2][0], trazar_fig_4lados_transformado[2][1], trazar_fig_4lados_transformado[0][0], trazar_fig_4lados_transformado[0][1]));					
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					System.out.println("V3: X3: " + x3 + ", Y3: " + y3);
					System.out.println("V4: X4: " + x4 + ", Y4: " + y4);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
					System.out.println("V3: X3': " + x3_prima + ", Y3': " + y3_prima);
					System.out.println("V4: X4': " + x4_prima + ", Y4': " + y4_prima);
				}
				if(num_vert!=2&&num_vert!=3&&num_vert!=4) {
					System.out.println("Seleccione una opción válida...");
				}
			}while(num_vert!=2&&num_vert!=3&&num_vert!=4);
		}
		
		//ROTACIÓN
		if(eleccion.equals("R")||eleccion.equals("r")) {
			System.out.println();
			System.out.println("Ha elegido Rotación");
			
			int num_vert;
			System.out.println();
			System.out.println("c) Introducir las coordenadas de los vértices");
			do {
				System.out.print("Número de vértices de la figura (2: Linea, 3: Triángulo, 4: Figura de 4 Lados) = ");
				num_vert = entrada.nextInt();
				if(num_vert==2) { //LINEA
					System.out.println("Ha elegido dibujar una Línea");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					double grados_rot;
					do {
						System.out.println();
						System.out.print("¿Cuántos Grados ° de Rotación desea? (0° - 360°) = ");
						grados_rot = entrada.nextDouble();
						if(grados_rot<0||grados_rot>360) {
							System.out.println("Ingrese un numero de Grados correcto...");
						}else {
							System.out.println("¡Bien!, " + grados_rot + "°");
						}
					}while(grados_rot<0||grados_rot>360);
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ ORIGINAL
					trazar_linea_original[0][0] = x1;
					trazar_linea_original[0][1] = y1;
					trazar_linea_original[1][0] = x2;
					trazar_linea_original[1][1] = y2;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					double x1_prima = (trazar_linea_original[0][0] * Math.cos(-grados_rot)) + (trazar_linea_original[0][1] * -Math.sin(-grados_rot));
					double y1_prima = (trazar_linea_original[0][0] * Math.sin(-grados_rot)) + (trazar_linea_original[0][1] * Math.cos(-grados_rot));
					double x2_prima = (trazar_linea_original[1][0] * Math.cos(-grados_rot)) + (trazar_linea_original[1][1] * -Math.sin(-grados_rot));
					double y2_prima = (trazar_linea_original[1][0] * Math.sin(-grados_rot)) + (trazar_linea_original[1][1] * Math.cos(-grados_rot));
					
					trazar_linea_tranformada[0][0] = x1_prima;
					trazar_linea_tranformada[0][1] = y1_prima;
					trazar_linea_tranformada[1][0] = x2_prima;
					trazar_linea_tranformada[1][1] = y2_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_linea_original[0][0], trazar_linea_original[0][1], trazar_linea_original[1][0], trazar_linea_original[1][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_linea_tranformada[0][0], trazar_linea_tranformada[0][1], trazar_linea_tranformada[1][0], trazar_linea_tranformada[1][1]));
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
				}
				if(num_vert==3) { //TRIANGULO
					System.out.println("Ha elegido dibujar un Triángulo");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					System.out.println("Captura de V3:");
					System.out.print("X3 = ");
					double x3 = entrada.nextDouble();
					System.out.print("Y3 = ");
					double y3 = entrada.nextDouble();
					
					double grados_rot;
					do {
						System.out.println();
						System.out.print("¿Cuántos Grados ° de Rotación desea? (0° - 360°) = ");
						grados_rot = entrada.nextDouble();
						if(grados_rot<0&&grados_rot>360) {
							System.out.println("Ingrese un numero de Grados correcto...");
						}else {
							System.out.println("¡Bien!, " + grados_rot + "°");
						}
					}while(grados_rot<0&&grados_rot>360);
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ
					trazar_triangulo_original[0][0] = x1;
					trazar_triangulo_original[0][1] = y1;
					trazar_triangulo_original[1][0] = x2;
					trazar_triangulo_original[1][1] = y2;
					trazar_triangulo_original[2][0] = x3;
					trazar_triangulo_original[2][1] = y3;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					double x1_prima = (trazar_triangulo_original[0][0] * Math.cos(-grados_rot)) + (trazar_triangulo_original[0][1] * -Math.sin(-grados_rot));
					double y1_prima = (trazar_triangulo_original[0][0] * Math.sin(-grados_rot)) + (trazar_triangulo_original[0][1] * Math.cos(-grados_rot));
					double x2_prima = (trazar_triangulo_original[1][0] * Math.cos(-grados_rot)) + (trazar_triangulo_original[1][1] * -Math.sin(-grados_rot));
					double y2_prima = (trazar_triangulo_original[1][0] * Math.sin(-grados_rot)) + (trazar_triangulo_original[1][1] * Math.cos(-grados_rot));
					double x3_prima = (trazar_triangulo_original[2][0] * Math.cos(-grados_rot)) + (trazar_triangulo_original[2][1] * -Math.sin(-grados_rot));
					double y3_prima = (trazar_triangulo_original[2][0] * Math.sin(-grados_rot)) + (trazar_triangulo_original[2][1] * Math.cos(-grados_rot));
					
					trazar_triangulo_tranformado[0][0] = x1_prima;
					trazar_triangulo_tranformado[0][1] = y1_prima;
					trazar_triangulo_tranformado[1][0] = x2_prima;
					trazar_triangulo_tranformado[1][1] = y2_prima;
					trazar_triangulo_tranformado[2][0] = x3_prima;
					trazar_triangulo_tranformado[2][1] = y3_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[0][0], trazar_triangulo_original[0][1], trazar_triangulo_original[1][0], trazar_triangulo_original[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[0][0], trazar_triangulo_original[0][1], trazar_triangulo_original[2][0], trazar_triangulo_original[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_original[1][0], trazar_triangulo_original[1][1], trazar_triangulo_original[2][0], trazar_triangulo_original[2][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[0][0], trazar_triangulo_tranformado[0][1], trazar_triangulo_tranformado[1][0], trazar_triangulo_tranformado[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[0][0], trazar_triangulo_tranformado[0][1], trazar_triangulo_tranformado[2][0], trazar_triangulo_tranformado[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_triangulo_tranformado[1][0], trazar_triangulo_tranformado[1][1], trazar_triangulo_tranformado[2][0], trazar_triangulo_tranformado[2][1]));
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					System.out.println("V3: X3: " + x3 + ", Y3: " + y3);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
					System.out.println("V3: X3': " + x3_prima + ", Y3': " + y3_prima);
				}
				if(num_vert==4){ //FIGURAS DE 4 LADOS (CUADRADO, RECTANGULO, ROMBO, ROMBOIDE, ETC)
					System.out.println("Ha elegido dibujar una Figura de 4 Lados");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					System.out.println("Captura de V3:");
					System.out.print("X3 = ");
					double x3 = entrada.nextDouble();
					System.out.print("Y3 = ");
					double y3 = entrada.nextDouble();
					
					System.out.println("Captura de V4:");
					System.out.print("X4 = ");
					double x4 = entrada.nextDouble();
					System.out.print("Y4 = ");
					double y4 = entrada.nextDouble();
					
					double grados_rot;
					do {
						System.out.println();
						System.out.print("¿Cuántos Grados ° de Rotación desea? (0° - 360°) = ");
						grados_rot = entrada.nextDouble();
						if(grados_rot<0&&grados_rot>360) {
							System.out.println("Ingrese un numero de Grados correcto...");
						}else {
							System.out.println("¡Bien!, " + grados_rot + "°");
						}
					}while(grados_rot<0&&grados_rot>360);
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ
					trazar_fig_4lados_original[0][0] = x1;
					trazar_fig_4lados_original[0][1] = y1;
					trazar_fig_4lados_original[1][0] = x2;
					trazar_fig_4lados_original[1][1] = y2;
					trazar_fig_4lados_original[2][0] = x3;
					trazar_fig_4lados_original[2][1] = y3;
					trazar_fig_4lados_original[3][0] = x4;
					trazar_fig_4lados_original[3][1] = y4;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/

					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					double x1_prima = (trazar_fig_4lados_original[0][0] * Math.cos(-grados_rot)) + (trazar_fig_4lados_original[0][1] * -Math.sin(-grados_rot));
					double y1_prima = (trazar_fig_4lados_original[0][0] * Math.sin(-grados_rot)) + (trazar_fig_4lados_original[0][1] * Math.cos(-grados_rot));
					double x2_prima = (trazar_fig_4lados_original[1][0] * Math.cos(-grados_rot)) + (trazar_fig_4lados_original[1][1] * -Math.sin(-grados_rot));
					double y2_prima = (trazar_fig_4lados_original[1][0] * Math.sin(-grados_rot)) + (trazar_fig_4lados_original[1][1] * Math.cos(-grados_rot));
					double x3_prima = (trazar_fig_4lados_original[2][0] * Math.cos(-grados_rot)) + (trazar_fig_4lados_original[2][1] * -Math.sin(-grados_rot));
					double y3_prima = (trazar_fig_4lados_original[2][0] * Math.sin(-grados_rot)) + (trazar_fig_4lados_original[2][1] * Math.cos(-grados_rot));
					double x4_prima = (trazar_fig_4lados_original[3][0] * Math.cos(-grados_rot)) + (trazar_fig_4lados_original[3][1] * -Math.sin(-grados_rot));
					double y4_prima = (trazar_fig_4lados_original[3][0] * Math.sin(-grados_rot)) + (trazar_fig_4lados_original[3][1] * Math.cos(-grados_rot));
					
					trazar_fig_4lados_transformado[0][0] = x1_prima;
					trazar_fig_4lados_transformado[0][1] = y1_prima;
					trazar_fig_4lados_transformado[1][0] = x2_prima;
					trazar_fig_4lados_transformado[1][1] = y2_prima;
					trazar_fig_4lados_transformado[2][0] = x3_prima;
					trazar_fig_4lados_transformado[2][1] = y3_prima;
					trazar_fig_4lados_transformado[3][0] = x4_prima;
					trazar_fig_4lados_transformado[3][1] = y4_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[0][0], trazar_fig_4lados_original[0][1], trazar_fig_4lados_original[1][0], trazar_fig_4lados_original[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[1][0], trazar_fig_4lados_original[1][1], trazar_fig_4lados_original[3][0], trazar_fig_4lados_original[3][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[3][0], trazar_fig_4lados_original[3][1], trazar_fig_4lados_original[2][0], trazar_fig_4lados_original[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_original[2][0], trazar_fig_4lados_original[2][1], trazar_fig_4lados_original[0][0], trazar_fig_4lados_original[0][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[0][0], trazar_fig_4lados_transformado[0][1], trazar_fig_4lados_transformado[1][0], trazar_fig_4lados_transformado[1][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[1][0], trazar_fig_4lados_transformado[1][1], trazar_fig_4lados_transformado[3][0], trazar_fig_4lados_transformado[3][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[3][0], trazar_fig_4lados_transformado[3][1], trazar_fig_4lados_transformado[2][0], trazar_fig_4lados_transformado[2][1]));
					graficas_2d.draw(new Line2D.Double(trazar_fig_4lados_transformado[2][0], trazar_fig_4lados_transformado[2][1], trazar_fig_4lados_transformado[0][0], trazar_fig_4lados_transformado[0][1]));					
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					System.out.println("V3: X3: " + x3 + ", Y3: " + y3);
					System.out.println("V4: X4: " + x4 + ", Y4: " + y4);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
					System.out.println("V3: X3': " + x3_prima + ", Y3': " + y3_prima);
					System.out.println("V4: X4': " + x4_prima + ", Y4': " + y4_prima);
				}
				if(num_vert!=2&&num_vert!=3&&num_vert!=4) {
					System.out.println("Seleccione una opción válida...");
				}
			}while(num_vert!=2&&num_vert!=3&&num_vert!=4);
		}
		
		//REFLEXIÓN S/ UNA LÍNEA CON X=Y
		if(eleccion.equals("RE")||eleccion.equals("re")) {
			System.out.println();
			System.out.println("Ha elegido Reflexión s/ una línea con x=y");

			int num_vert;
			System.out.println();
			System.out.println("c) Introducir las coordenadas de los vértices");
			do {
				System.out.print("Número de vértices de la figura (2: Linea, 3: Triángulo, 4: Figura de 4 Lados) = ");
				num_vert = entrada.nextInt();
				if(num_vert==2) { //LINEA
					System.out.println("Ha elegido dibujar una Línea");
					System.out.println("Captura de V1:");
					System.out.print("X1 = ");
					double x1 = entrada.nextDouble();
					System.out.print("Y1 = ");
					double y1 = entrada.nextDouble();
					
					System.out.println("Captura de V2:");
					System.out.print("X2 = ");
					double x2 = entrada.nextDouble();
					System.out.print("Y2 = ");
					double y2 = entrada.nextDouble();
					
					//SE GUARDAN LOS NUMEROS EN LA MATRIZ ORIGINAL
					trazar_linea_original[0][0] = x1;
					trazar_linea_original[0][1] = y1;
					trazar_linea_original[1][0] = x2;
					trazar_linea_original[1][1] = y2;
					
					//SE MUESTRAN LOS DATOS DE LOS VERTICES ORIGINALES
					/*
					for(int i=0; i<matriz_original.length; i++) {
						for(int j=0; j<matriz_original[i].length; j++) {
							System.out.print(matriz_original[i][j] +" ");
						}
					}
					*/
					
					//SE GUARDAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					double x1_prima = (x1*0) + (y1*1);
					double y1_prima = (x1*1) + (y1*0);
					double x2_prima = (x2*0) + (y2*1);
					double y2_prima = (x2*1) + (y2*0);
					
					trazar_linea_tranformada[0][0] = x1_prima;
					trazar_linea_tranformada[0][1] = y1_prima;
					trazar_linea_tranformada[1][0] = x2_prima;
					trazar_linea_tranformada[1][1] = y2_prima;
					
					//SE MUESTRAN LOS DATOS DE LOS NUEVOS VERTICES TRANSFORMADOS (RESOLVIENDO LA MULTIPLICACION MATRICIAL)
					/*
					for(int i=0; i<matriz_convertida.length; i++) {
						for(int j=0; j<matriz_convertida[i].length; j++) {
							System.out.print(matriz_convertida[i][j] +" ");
						}
					}
					*/
					System.out.println("e) Graficación de los objetos");
					super.paint(g);
					Graphics2D graficas_2d = (Graphics2D) g;
					g.setColor(Color.RED);
					//graficas_2d.draw(new Line2D.Double(100.5, 100.4, 800.8, 700.1));
					graficas_2d.draw(new Line2D.Double(trazar_linea_original[0][0], trazar_linea_original[0][1], trazar_linea_original[1][0], trazar_linea_original[1][1]));
					g.setColor(Color.BLUE);
					graficas_2d.draw(new Line2D.Double(trazar_linea_tranformada[0][0], trazar_linea_tranformada[0][1], trazar_linea_tranformada[1][0], trazar_linea_tranformada[1][1]));
					
					SwingUtilities.invokeLater(new Runnable(){
						@Override
						public void run() {
							new DibujarLinea().setVisible(true);
						}
					});
					super.setVisible(true);
					
					//MUESTRA EN CONSOLA DE LAS COORDENADAS ORIGINALES Y TRANSFORMADAS
					System.out.println();
					System.out.println("g) Desplegar las coordenadas de los vértices de la Figura Original");
					System.out.println("V1: X1: " + x1 + ", Y1: " + y1);
					System.out.println("V2: X2: " + x2 + ", Y2: " + y2);
					
					System.out.println();
					System.out.println("h) Desplegar las coordenadas de los vértices de la Figura Transformada");
					System.out.println("V1: X1': " + x1_prima + ", Y1': " + y1_prima);
					System.out.println("V2: X2': " + x2_prima + ", Y2': " + y2_prima);
				}
				if(num_vert==3) { //TRIANGULO
					
				}
				if(num_vert==4) { //FIGURAS DE 4 LADOS (CUADRADO, RECTANGULO, ROMBO, ROMBOIDE, ETC)
					
				}
				if(num_vert!=2&&num_vert!=3&&num_vert!=4) {
					System.out.println("Seleccione una opción válida...");
				}
			}while(num_vert!=2&&num_vert!=3&&num_vert!=4);
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		dibujarLasLineas(g);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				new DibujarLinea().setVisible(true);
			}
		});	
	}
}