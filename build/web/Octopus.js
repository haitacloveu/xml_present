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
        let nodes = xml.childNodes;
        for (let i = 0; i < nodes.length; i++) {
            if (nodes[i].nodeName === tagName) {
                return nodes[i].childNodes[0].nodeValue;
            }
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
            Octopus.firstRender();
        });
    },

    firstRender: function () {
        if (UrlManager.getParam("page") === "detail") {
            if (Octopus.showProductDetail(UrlManager.getParam("productId"))) {
                return;
            }
        }
        Octopus.showHome();
    },

    showHome: function () {
        let search = UrlManager.getParam("search");
        if (search !== null && search.length > 0) {
            Octopus.search(search);
        } else {
            View.renderProducts(Model.data.products);
        }
        UrlManager.removeParam("page");
        UrlManager.removeParam("productId");
    },

    showProductDetail: function (id) {
        let product = Model.getProduct(id);
        if (product !== null) {
            View.showProductDetail(product);
            UrlManager.addParam("page", "detail");
            UrlManager.addParam("productId", id);
            return true;
        }
        return false;
    },

    search: function (keyword) {
        if (!keyword) {
            keyword = View.getSearchKeyword();
        }
        let products = Model.data.products;
        let searchResult = [];
        for (let i = 0; i < products.length; i++) {
            if (products[i].name.toLowerCase().includes(keyword.toLowerCase())) {
                searchResult.push(products[i]);
            }
        }
        View.renderProducts(searchResult);
        setTimeout(() => {
            View.renderSearchKeyword(keyword);
        }, 100);
        if (keyword.length > 0) {
            UrlManager.addParam("search", keyword);
        } else {
            UrlManager.removeParam("search");
        }
    },

    backToHome: function () {
        Octopus.showHome();
    }
};

Octopus.init();