package web;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SesionesServlet")
public class SesionesServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession sesion = request.getSession();
        String titulo = null;
        
        //Solicitamos un atributo a la sesion
        Integer contadorVisitas = (Integer) sesion.getAttribute("contadorVisitas");
        
        //Preguntamos si es nulo, en caso de ser nulo significa que es la primera vez que accedemos
        //a la aplicacion
        if(contadorVisitas == null){
            contadorVisitas = 1; //cambia el valor del contador de las visitas
            titulo = "Bienvenido por primera vez";
        }
        else{
            contadorVisitas++;
            titulo = "Bienvenido Nuevamente...";
        }
        
        //agregamos el nuevo valor a la sesion
        sesion.setAttribute("contadorVisitas", contadorVisitas);
        
        //mandamos la respuesta al usuario
        PrintWriter salida = response.getWriter();
        salida.print(titulo);
        salida.print("<br>");
        salida.print("No. accesos al recurso: "+contadorVisitas);
        salida.print("<br>");
        //El ID es el identificador unico de la sesion
        salida.print("ID de la sesion: "+sesion.getId());
        salida.close();
    }   
}