/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let UrlManager = {
    addParam: function (name, value) {
        let url = new URL(window.location.href);
        url.searchParams.set(name, value);
        window.history.replaceState(null, null, url);
    },

    getParam: function (name) {
        let params = new URLSearchParams(window.location.search);
        return params.get(name);
    },

    removeParam: function (name) {
        let url = new URL(window.location.href);
        url.searchParams.delete(name);
        window.history.replaceState(null, null, url);
    }
};

let Model = {
    init: function () {
        Model.data = {
            products: []
        };
    },

    loadProducts: function (callback) {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                callback(this.responseXML);
            }
        };
        xhttp.open("GET", "/Presentation/api/product", true);
        xhttp.send();
    },

    getProduct: function (id) {
        let products = Model.data.products;
        for (let i = 0; i < products.length; i++) {
            if (products[i].id === id) {
                return products[i];
            }
        }
        return null;
    }
};