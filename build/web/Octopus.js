/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let Constant = {

};

let XmlParser = {
    parse: function (xml) {
        let result = [];
        let listRecord = xml.getElementsByTagName("product");
        for (let i = 0; i < listRecord.length; i++) {
            result.push(this.parseProduct(listRecord[i]));
        }
        return result;
    },

    parseProduct: function (input) {
        let result = {};
        result.id = this.getTagContent(input, "id");
        result.label = this.getTagContent(input, "label");
        result.link = this.getTagContent(input, "link");
        result.imgLink = this.getTagContent(input, "imgLink");
        result.name = this.getTagContent(input, "name");
        result.price = this.getTagContent(input, "price");
        result.sPrice = this.getTagContent(input, "sPrice");
        result.promotion = this.getTagContent(input, "promotion");

        result.oldProduct = [];
        let oldProductXml = input.getElementsByTagName("oldProducts");
        for (let i = 0; i < oldProductXml.length; i++) {
            result.oldProduct.push(this.parseOldProduct(oldProductXml[i]));
        }

        return result;
    },

    parseOldProduct: function (input) {        
        let result = {};
        result.id = this.getTagContent(input, "id");  
        result.toProductId = this.getTagContent(input, "toProductId");   
        result.title = this.getTagContent(input, "title");   
        result.link = this.getTagContent(input, "link");   
        result.imgLink = this.getTagContent(input, "imgLink");   
        result.price = this.getTagContent(input, "price");   
        result.content = this.getTagContent(input, "content");   
        result.address = this.getTagContent(input, "address");   
        result.time = this.getTagContent(input, "time");       
        return result;
    },

    getTagContent: function (xml, tagName) {
        if (xml.getElementsByTagName(tagName).length > 0) {
            return xml.getElementsByTagName(tagName)[0].childNodes[0].nodeValue;
        }
        return "";
    }
};

let Octopus = {
    init: function () {
        Model.init();
        View.init();

        this.loadProducts();
    },

    loadProducts: function () {
        Model.loadProducts((result) => {
            let listProduct = XmlParser.parse(result);
            Model.data.products = listProduct;
            View.renderProducts(listProduct);
        });
    },
    
    search: function() {
        let keyword = View.getSearchKeyword();
        let products = Model.data.products;
        let searchResult = [];
        for (let i=0; i<products.length; i++) {
            if (products[i].name.toLowerCase().includes(keyword.toLowerCase())) {
                searchResult.push(products[i]);
            }
        }
        View.renderProducts(searchResult);
    }
};

Octopus.init();