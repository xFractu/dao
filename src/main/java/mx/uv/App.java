package mx.uv;
import static spark.Spark.*;
import com.google.gson.*;
import java.util.HashMap;
import java.util.UUID;
/**
 * Hello world!
 *
 */


public class App 
{
    static Gson gson = new Gson() ;
    static HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        port(90);


        //Maestro

        get("/usuarios", (request, response)->{
            response.type("application/json");
            return gson.toJson(usuarios.values());
            //return gson.toJson(DAO.dameUsuarios());
        });

        post("/usuarios", (request, response)->{
            String payload = request.body();
            Usuario usuario = gson.fromJson(payload, Usuario.class);
            System.out.println("payload "+payload);
            String id = UUID.randomUUID().toString();
            usuario.setId(id);
            usuarios.put(id, usuario);
            DAO.crearUsuario(usuario);
            System.out.println("n "+usuario.getNombre());
            System.out.println("p "+usuario.getPassword());
            System.out.println("i "+usuario.getId());
            return usuario;
        });

        //Clase 29/11/2023

        /* 
        get("/usuarios",(request,response) ->{
            response.type(("application/json"));
            return gson.toJson(usuarios);
        });

        post("/usuarios",(request,response) ->{

            String payload = request.body();
            Usuario u = gson.fromJson(payload,Usuario.class);
            String id = UUID.randomUUID().toString();
            u.setId(id);
            //usuarios.put(payload,u);//se almacena en un diccionario 

            DAO.crearUsuario(u);

            System.out.println("Payload: "+ payload);
            System.out.println("n: "+u.getNombre());
            System.out.println(": "+u.getPassword());


            JsonObject respuesta = new JsonObject();
            respuesta.addProperty("Id: ", id);
            return respuesta;
        });*/
    }
}
