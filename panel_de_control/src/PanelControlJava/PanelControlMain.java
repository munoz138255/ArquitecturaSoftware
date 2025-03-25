package PanelControlJava;

import DAO.RegistroJugadores;
import DAO.RegistroPartidas;

public class PanelControlMain {
    public static void main(String[] args) {
        
        // Instanciamos los DAOs
        RegistroJugadores jugadorDAO = new RegistroJugadores();
        RegistroPartidas partidaDAO = new RegistroPartidas();
        
        // Instanciamos la Vista
        PanelControlView view = new PanelControlView();
        
        // Instanciamos el Controlador
        PanelControlController controller = new PanelControlController(
                jugadorDAO, 
                partidaDAO, 
                view
        );
        
        // Iniciamos la aplicación (loop de menú)
        controller.iniciar();
    }
}
