/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Controller;

import TuanVXM.Service.CrawlService;
import TuanVXM.Service.ProductService;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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

    @PUT
    public String crawl() {
        if (new CrawlService().crawl()) {
            return "Cào dữ liệu thành công!";
        } else {
            return "Có lỗi xảy ra";
        }
    }
}
