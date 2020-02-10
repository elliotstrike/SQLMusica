import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BDController controladorBD = new BDController();
		Scanner sn = new Scanner(System.in);
		Scanner sc = new Scanner(System.in);
		int opc = 0;

		do {
			Main.mostrarMenu();
			opc = sn.nextInt();
			switch(opc) {
			case 1:
				for(int i=0;i<controladorBD.artistas().size();i++){
					System.out.println(controladorBD.artistas().get(i).toString());
				}
				break;
			case 2:
				System.out.println("Introduce letra: ");
				String letra = sc.nextLine();
				for(int i=0;i<controladorBD.artistasLetra(letra).size();i++){
					if(controladorBD.artistasLetra(letra).get(i).getNombre().startsWith(letra)) {
						System.out.println(controladorBD.artistasLetra(letra).get(i).toString());
					}
				}
				break;
			case 3:
				System.out.println("1. Alta \n2. Baja");
				int opc1 = sn.nextInt();
				if(opc1==1) {
					System.out.println("Introduce DNI: ");
					String dni = sc.nextLine();

					if(controladorBD.existeArtista(dni)==false) {
						System.out.println("Introduce Nombre: ");
						String nombre = sc.nextLine();
						controladorBD.altaArtista(controladorBD, nombre, dni);
					}else {
						System.out.println("DNI repetido vuelva a probar");

					}

				}
				if(opc1==2) {
					System.out.println("Introduce DNI para dar de baja: ");
					String dni = sc.nextLine();
					for(int i=0;i<controladorBD.artistas().size();i++) {
						if(controladorBD.artistas().get(i).getDni().equalsIgnoreCase(dni)) {
							controladorBD.bajaArtista(controladorBD, dni);
							System.out.println("Artista borrado");
						}
					}
				}
				break;
			case 4:
				System.out.println("Introduce letra: ");
				letra = sc.nextLine();
				for(int i=0;i<controladorBD.artistasLetraFin(letra).size();i++){
					if(controladorBD.artistasLetraFin(letra).get(i).getNombre().endsWith(letra)) {
						System.out.println(controladorBD.artistasLetraFin(letra).get(i).toString());
					}
				}
				break;
			case 5:
				for(int i=0;i<controladorBD.canciones().size();i++) {
					System.out.println(controladorBD.canciones().get(i).toString());
				}
				break;
			case 6:
				System.out.println(controladorBD.cancionMasLarga().toString());

				break;
			case 7:
				System.out.println("Introduce duración y te mostraré un listado con las canciones que duren menos a lo que introduzcas -- FORMATO = (x,x)");
				double total = sn.nextDouble();
				for(int i=0;i<controladorBD.duracionCancion(total).size();i++) {
					System.out.println(controladorBD.duracionCancion(total).get(i).toString());
				}
				break;
			case 8:
				System.out.println("Introduce título del disco: ");
				String disco = sc.nextLine();
				for(int i=0;i<controladorBD.ListadoCancionesDisco(disco).size();i++){	
					System.out.println(controladorBD.ListadoCancionesDisco(disco).get(i).toString());
				}

				break;
			case 9:
				System.out.println("Introduzca el código de la nueva canción a añadir: ");
				String codigo = sn.nextLine();
				if (!controladorBD.existeCancion(codigo)) {
					System.out.println("Introduzca el título de la canción:");
					String titulo = sc.nextLine();
					System.out.println("Introduzca la duración de la canción:");
					double duracion = sn.nextDouble();
					controladorBD.altaCancion(codigo, titulo, duracion);
				}else {
					System.out.println("Esta canción ya existe, no es posible subir una canción con el mismo código.");
				}
				break;
			case 10:
				System.out.println("Introduzca el código de la canción a borrar:");
				codigo = sn.nextLine();
				if (controladorBD.existeCancion(codigo)) {
					controladorBD.bajaCancion(codigo);
				}else {
					System.out.println("No hay ninguna canción con este código en la base de datos.");
				}
				break;
			case 11:
				for(int i=0;i<controladorBD.grupos().size();i++) {
					System.out.println(controladorBD.grupos().get(i).toString());
				}
				break;
			case 12:
				for(int i=0;i<controladorBD.discos().size();i++) {
					System.out.println(controladorBD.grupos().get(i).toString());
				}
				break;
			case 13:
				System.out.println("Introduce nombre de grupo: ");
				String nombre = sc.nextLine();
				for(int i=0;i<controladorBD.artistasGrupo(nombre).size();i++) {
					System.out.println(controladorBD.artistasGrupo(nombre).get(i).toString());
				}
				break;
			case 14:
				ArrayList<Cancion> canciones = controladorBD.canciones();
				ArrayList<Disco> discos = controladorBD.discos();
				System.out.println("DISCOS /n ------------------------------------------------------------");
				for(int i=0;i<discos.size();i++) {
					System.out.println(discos.get(i).toString());
				}
				System.out.println("CANCIONES /n----------------------------------------------------------");
				for(int i=0;i<canciones.size();i++) {
					System.out.println(canciones.get(i).toString());
				}
				System.out.println("Introduce el código del disco a que quieres que pertenezca la canción");
				String cod1 = sc.nextLine();
				System.out.println("Introduce el codigo de la canción que quieres meter: ");
				String can = sc.nextLine();
				if(controladorBD.existeEsta(can) == true){ 
					System.out.println("El registro ya existe, pruebe otra vez");
				}else {
					controladorBD.altaCancionenDisco(can,cod1);
				}
				break;
			case 15:
				System.out.println("A continuación se le mostraran los artistas y los grupos, seleccione el dni del artista y el código del grupo donde lo quiera insertar:");
				ArrayList<Artista> artistas = controladorBD.artistas();
				for (int i = 0; i < artistas.size(); i++) {
					System.out.println(artistas.get(i).toString());
					System.out.println();
				}
				String artista = "";
				do {
					System.out.println("Seleccione el dni del artista:");
					artista = sc.nextLine();
					if (!controladorBD.existeArtista(artista)) {
						System.out.println("Este dni no corresponde a ningun artista.");
					}
				} while (!controladorBD.existeArtista(artista));
				System.out.println("Ahora los grupos:\n");
				ArrayList<Grupo> grupos = controladorBD.grupos();
				for (int i = 0; i < grupos.size(); i++) {
					System.out.println(grupos.get(i).toString());
					System.out.println();
				}
				String grupo = "";
				do {
					System.out.println("Seleccione el código del grupo:");
					grupo = sc.nextLine();
					if (!controladorBD.existeGrupo(grupo)) {
						System.out.println("Este código no corresponde a ningun grupo.");
					}
				} while (!controladorBD.existeGrupo(grupo));
				System.out.println("Introduzca la función que tiene el artista en el grupo:");
				String funcion = sc.nextLine();
				controladorBD.introducirArtistaEnGrupo(artista, grupo, funcion);

				break;
			case 16:
				String titulo = "";
				do {
					System.out.println("Introduce nombre disco: ");
					titulo = sc.nextLine();
					if(controladorBD.existeDisco(titulo) == false) {
						System.out.println("Este título no corresponde a ningún disco");
					}
				}while(!controladorBD.existeDisco(titulo));

				for(int i=0;i<controladorBD.compa().size();i++) {
					System.out.println(controladorBD.compa().get(i).toString());
				}
				System.out.println("-----------------------------------------");
				String cod = "";
				do {
					System.out.println("Introduce codigo de compañia donde quieres que se ubique: ");
					cod = sc.nextLine();
					if(controladorBD.existeCompanyia(cod) == false) {
						System.out.println("Este código no pertenece a ninguna compañia");
					}
				}while(!controladorBD.existeCompanyia(cod));

				controladorBD.modificarDisco(titulo, cod);
				break;
			case 17:
				String club = "";
				do {
					System.out.println("Introduce nombre club: ");
					club = sc.nextLine();
					if(controladorBD.existeClubs(club) == false) {
						System.out.println("No hay ningún club que se llame así.");
					}	
				}while(!controladorBD.existeClubs(club));

				System.out.println("Grupos que actúan en el club "+club+":");
				for(int i=0;i<controladorBD.GrupoActuacion(club).size();i++) {
					System.out.println(controladorBD.GrupoActuacion(club).get(i).getNombre());
				}
				break;
			case 18:
				System.out.println("Artistas sin grupo: ");
				for(int i=0;i<controladorBD.artistasSinGrupo().size();i++) {
					System.out.println(controladorBD.artistasSinGrupo().get(i).toString());
				}
				break;
			case 19:
				System.out.println("Canciones sin disco: ");
				canciones = controladorBD.cancionesSinDisco();
				for(int i=0;i<canciones.size();i++) {
					System.out.println(canciones.toString());
				}
				break;
			case 20:
				System.out.println("Listado de los discos con 6 canciones o más: ");
				discos = controladorBD.discoCon6cancionesoMas();
				for (int i=0;i<discos.size();i++) {
					System.out.println("Título: " + discos.get(i).getNombre());
				}

				break;
			case 21:
				canciones = controladorBD.canciones();
				System.out.println("A continuación, mostraré listado de canciones: ");
				System.out.println("CANCIONES /n----------------------------------------------------------");
				for(int i=0;i<canciones.size();i++) {
					System.out.println(canciones.get(i).toString());
				}
				do {
					System.out.println("Introduce código de canción y mostraré la información del disco al que pertenece");
					codigo = sc.nextLine();
					if(controladorBD.existeCancion(codigo) == false) {
						System.out.println("El código no existe, vuelve a probar");
					}
				}while(!controladorBD.existeCancion(codigo));
				System.out.println(controladorBD.discosQuePertenecenaCancion(codigo).toString());
				break;	
			case 22:
				artistas = controladorBD.artistas();
				String dni ="";
				System.out.println("A continuación, mostraré listado de artistas: ");
				System.out.println("Artistas /n----------------------------------------------------------");
				for (int i=0;i<artistas.size();i++) {
					System.out.println(artistas.get(i).toString());
				}
				do {
					System.out.println("Introduce DNI de artista y mostraré la información del grupo al que pertenece");
					dni = sc.nextLine();
					if(controladorBD.existeArtista(dni) == false) {
						System.out.println("El DNI no existe, vuelve a probar");
					}
				}while(!controladorBD.existeArtista(dni));
				System.out.println(controladorBD.gruposQuePerteneceaUnArtista(dni).toString());
				break;
			case 23:
				do {
					System.out.println("Introduce nombre grupo: ");
					grupo = sc.nextLine();
					if(controladorBD.existeGrupo(grupo) == false) {
						System.out.println("No hay ningún grupo que se llame así.");
					}	
				}while(!controladorBD.existeGrupo(grupo));

				System.out.println("CLUBS DONDE ACTUÁN: ");
				for(int i=0;i<controladorBD.ClubesDondeTocaUnGrupo(grupo).size();i++) {
					System.out.println(controladorBD.ClubesDondeTocaUnGrupo(grupo).get(i).toString());
				}
				break;
			case 24:
				do {
				System.out.println("Introduce código compañia a modificar: ");
				codigo = sc.nextLine();
				if(controladorBD.existeCompanyia(codigo) == false) {
					System.out.println("El código no corresponde a ninguna compañia, vuelve a intentarlo");
				}
				}while(!controladorBD.existeCompanyia(codigo));
				System.out.println("Introduce que quieres modificar: \n"
						+ "1. Nombre \n"
						+ "2. Dirección \n"
						+ "3. Fax \n"
						+ "4. Teléfono");
				int opcC = sn.nextInt();
				switch(opcC) {
				case 1:
					System.out.println("Introduce nuevo nombre: ");
					nombre = sc.nextLine();
					controladorBD.modificarCompañiaCase1(nombre, codigo);
					break;
				case 2:
					System.out.println("Introduce nuevo direccion: ");
					String dir = sc.nextLine();
					controladorBD.modificarCompañiaCase2(dir, codigo);
					break;
				case 3:
					System.out.println("Introduce nuevo fax: ");
					String fax = sc.nextLine();
					controladorBD.modificarCompañiaCase3(fax, codigo);
					break;
				case 4:
					System.out.println("Introduce nuevo telefono: ");
					String tfno = sc.nextLine();
					controladorBD.modificarCompañiaCase4(tfno, codigo);
					break;
					
				}
				break;
			case 25:	
				System.out.println("Bye!");
				break;
				

			default: System.out.println("Opción incorrecta vuelve a intentarlo");
			}
		}while(opc!=21);


	}
	public static void mostrarMenu() {
		System.out.println("|---------------------------------MENU----------------------------------------|\n"
				+ "  1. Listado de Artistas \n"
				+ "  2. Listado de Artistas que empiecen por una letra \n"
				+ "  3. Alta o baja de artista \n"
				+ "  4. Listado de Artistas que acaben por una letra \n" 
				+ "  5. Listado de canciones \n"
				+ "  6. Canción mas larga \n"
				+ "  7. Listado de canciones con duración menos a una duración pedida al usuario \n"
				+ "  8. Listado de canciones por disco \n"
				+ "  9. Alta Cancion \n"
				+ "  10. Baja Cancion \n"
				+ "  11. Listado grupos \n"
				+ "  12. Listado discos \n"
				+ "  13. Listado de artistas de un grupo pedido al usuario \n"
				+ "  14. Meter canción en disco \n"
				+ "  15. Meter artista en grupo \n"
				+ "  16. Modificar compañia de disco \n"
				+ "  17. Mostrar los datos de los clubs donde toca un grupo pedido al usuario \n"
				+ "  18. Listado de artistas que no tienen grupo \n"
				+ "  19. Listado de canciones sin disco \n"
				+ "  20. Listado de las canciones de un disco (Debe de tener mínimo 6 canciones) \n"
				+ "  22. Mostrar la información de todos los grupos en los que está un artista \n"
				+ "  23. Mostrar información de todos los clubes donde toca un grupo \n"
				+ "  24. Modificar datos de compañia \n"
				+ "  25. Salir \n"
				+ "  Elige opción \n"
				+ "|---------------------------------MENU----------------------------------------|"
				);	
	}


}