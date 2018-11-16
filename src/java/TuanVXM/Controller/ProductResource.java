/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Controller;

import TuanVXM.Service.CrawlService;
import TuanVXM.Service.ProductService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author TuanVXM
 */
@Path("product")
public class ProductResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public ProductResource() {
    }

    /**
     * Retrieves representation of an instance of
     * TuanVXM.Controller.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getProducts() {
        String result = new ProductService().getAllProducts();
        return result;
    }

    @Path("/getPdf/{id}")
    @GET
    public void getPdf(@PathParam("id") int id, @Context ServletContext servletContext,
            @Context HttpServletResponse response) {
        try {
            String path = servletContext.getRealPath("/");
            byte[] bytes = new ProductService().getPdf(id, path);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            
            OutputStream out = new FileOutputStream("C:\\Users\\tuanvxm\\Desktop\\testt.pdf");
            out.write(bytes);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductResource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @PUT
    public String crawl(@Context ServletContext servletContext) {
        String realPath = servletContext.getRealPath("/");
        if (new CrawlService().crawl(realPath)) {
            return "Cào dữ liệu thành công!";
        } else {
            return "Có lỗi xảy ra";
        }
    }
}
