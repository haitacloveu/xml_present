/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Service;

import TuanVXM.Business.MuaBanProductBusiness;
import TuanVXM.Business.TechOneProductBusiness;
import TuanVXM.DTO.ProductsDTO;
import TuanVXM.DTO.TechOneProductDTO;
import TuanVXM.Storage.TechOneProductStorage;
import TuanVXM.Util.XmlUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.xml.sax.SAXException;

/**
 *
 * @author TuanVXM
 */
public class ProductService {

    public String getAllProducts() {
        String result = null;
        try {
            TechOneProductBusiness techOneProductBusiness = new TechOneProductBusiness();
            MuaBanProductBusiness muaBanProductBusiness = new MuaBanProductBusiness();
            List<TechOneProductDTO> products = techOneProductBusiness.getProducts();
            ProductsDTO productsDTO = new ProductsDTO();
            productsDTO.setProduct(products);

            for (TechOneProductDTO product : products) {
                product.setOldProducts(muaBanProductBusiness.getByTOProductId(product.getId()));
            }

            JAXBContext jaxbContext = JAXBContext.newInstance(ProductsDTO.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(productsDTO, sw);
            result = sw.toString();
        } catch (JAXBException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public byte[] getPdf(int id, String path) {
        FileInputStream input = null;
        byte[] bytes = null;
        try {
            TechOneProductBusiness techOneProductBusiness = new TechOneProductBusiness();
            MuaBanProductBusiness muaBanProductBusiness = new MuaBanProductBusiness();
            TechOneProductDTO product = techOneProductBusiness.getProduct(id);
            product.setOldProducts(muaBanProductBusiness.getByTOProductId(product.getId()));
            String xslPath = path + "WEB-INF/export-pdf.xsl";
            String foPath = path + "WEB-INF/fo.fo";
            File file = new File(foPath);
            File fo = new File(foPath);

            String xmlString = XmlUtil.marshallData(product);
            methodTrax(xslPath, xmlString, foPath, path);

            input = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            FopFactory ff = FopFactory.newInstance(new File(path + "WEB-INF/fop.xconf"));
            FOUserAgent fua = ff.newFOUserAgent();
            Fop fop = ff.newFop(MimeConstants.MIME_PDF, fua, out);
            TransformerFactory tff = TransformerFactory.newInstance();
            Transformer trans = tff.newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            Source src = new StreamSource(fo);
            Result result = new SAXResult(fop.getDefaultHandler());
            trans.transform(src, result);

            bytes = out.toByteArray();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException | TransformerException | IOException | JAXBException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        return bytes;
    }

    private void methodTrax(String xslPath, String xmlString, String output, String path) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            StreamSource xsltFile = new StreamSource(xslPath);
            Transformer trans = tf.newTransformer(xsltFile);
            //trans.setParameter("pathFile", path);

            InputStream is = new ByteArrayInputStream(xmlString.getBytes(StandardCharsets.UTF_8));

            StreamSource xmlFile = new StreamSource(is);
            StreamResult htmFile = new StreamResult(new FileOutputStream(output));
            trans.transform(xmlFile, htmFile);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(ProductService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
