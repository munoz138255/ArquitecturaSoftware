package tienda.dao;

import java.util.ArrayList;
import java.util.List;
import tienda.modelos.Proveedor;

interface DirectorioProveedoresInterface {

    ArrayList<Proveedor> getAll();

    String insert(Proveedor proveedor);
    
}

