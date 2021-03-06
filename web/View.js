/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let Template = {
    loading: '<div class="loading">Đang tải dữ liệu...</div>',
    filter:
            '<div class="filter">' +
            '    <div class="filter-left">' +
            '        Tra cứu điện thoại' +
            '    </div>' +
            '    <div class="filter-right">' +
            '        <form onsubmit="Octopus.search()">' +
            '            <input id="filter-input" type="text" placeholder="Nhập tên máy"/>' +
            '            <input id="filter-submit" type="submit" value="Tìm kiếm"/>' +
            '        </form>' +
            '    </div>' +
            '</div>',
    listProduct:
            '<div class="list-product" id="list-product"></div>',
    product:
            '<div class="product" id="{id}-product">' +
            '    <div class="product-info">' +
            '        <div class="product-img-container">' +
            '            <img class="product-img" src="{imgLink}"/>' +
            '        </div>' +
            '        <div class="product-name">{name}</div>' +
            '        <div class="product-label">{label}</div>' +
            '        <div class="product-price">{price}</div>' +
            '        <div class="product-sPrice">{sPrice}</div>' +
            '        <div class="product-promotion">{promotion}</div>' +
            '    </div>' +
            '    <div class="product-list-old">' +
            '        <div class="old-product-container" id="{id}-old-product-container">' +
            '        </div>' +
            '        <div class="product-detail-container">' +
            '            <input type="button" class="product-detail" value="Xem thêm" ' +
            '               onclick="Octopus.showProductDetail(\'{id}\')"/>' +
            '        </div>' +
            '    </div>' +
            '</div>',
    oldProduct:
            '<div class="old-product">' +
            '    <div class="old-product-img-container">' +
            '        <img class="old-product-img" src="{imgLink}"/>' +
            '    </div>' +
            '    <div class="old-product-info">' +
            '        <div class="old-product-title">' +
            '            {title}' +
            '        </div>' +
            '        <div class="old-product-price">' +
            '            {price}' +
            '        </div>' +
            '        <div class="old-product-content">' +
            '            {content}' +
            '        </div>' +
            '        <div class="old-product-address">' +
            '            {address}' +
            '        </div>' +
            '        <div class="old-product-time">' +
            '            {time}' +
            '        </div>' +
            '    </div>' +
            '</div>',
    noOldProduct:
            '<div class="no-old-product">Không tìm thấy rao vặt nào!</div>',
    navigator:
            '<div class="filter">' +
            '    <div class="filter-left">' +
            '        <input type="button" onclick="Octopus.backToHome()" value="Quay Lại"/>' +
            '    </div>' +
            '    <div class="filter-right">' +
            '       <input type="button" value="Xuất ra file PDF" onclick="Octopus.getPdf(\'{id}\')"/>' +
            '    </div>' +
            '</div>',
    productDetail:
            '<div class="pd-container">' +
            '   <div class="pd-img-container">' +
            '       <img class="pd-img" src="{imgLink}"/>' +
            '   </div>' +
            '   <div class="pd-info-container">' +
            '       <div class="pd-label">{label}</div>' +
            '       <div class="pd-link">' +
            '           <a href="{link}" rel="noopener noreferrer" target="_blank">Xem trên TechOne</a>' + 
            '       </div>' +
            '       <div class="pd-name">{name}</div>' +
            '       <div class="pd-price">{price}</div>' +
            '       <div class="pd-sPrice">{sPrice}</div>' +
            '       <div class="pd-promotion">{promotion}</div>' +
            '       <div class="pd-statistic"></div>' +
            '   </div>' +
            '   <div id="pd-old-products" class="pd-old-products-container"></div>' +
            '</div>',
    pdOldProduct:
            '<div class="old-product">' +
            '    <div class="old-product-img-container">' +
            '        <img class="old-product-img" src="{imgLink}"/>' +
            '    </div>' +
            '    <div class="old-product-info">' +
            '        <div class="pd-old-product-title">' +
            '            {title}' +
            '        </div>' +
            '       <div class="pd-old-product-link">' +
            '           <a href="{link}" rel="noopener noreferrer" target="_blank">Xem trên MuaBan</a>' + 
            '       </div>' +
            '        <div class="old-product-price">' +
            '            {price}' +
            '        </div>' +
            '        <div class="old-product-content">' +
            '            {content}' +
            '        </div>' +
            '        <div class="old-product-address">' +
            '            {address}' +
            '        </div>' +
            '        <div class="old-product-time">' +
            '            {time}' +
            '        </div>' +
            '    </div>' +
            '</div>'
};

let StringProcessor = {
    replaceAll: function (input, sub, rep) {
        while (input.indexOf(sub) >= 0) {
            input = input.replace(sub, rep);
        }
        return input;
    },

    addZero: function (input) {
        input = "" + input;
        while (input.length < 3)
            input = "0" + input;
        return input;
    },

    formatPrice: function (input) {
        if (input < 0)
            return "";

        let mil = Math.floor(input / 1000000);
        input -= mil * 1000000;
        let tho = Math.floor(input / 1000);
        input -= tho * 1000;
        let result = mil + "." + StringProcessor.addZero(tho) + "." + StringProcessor.addZero(input) + "đ";
        if (mil == 0) {
            result = tho + "." + StringProcessor.addZero(input) + "đ";
        }
        return result;
    },

    lcs: function (s1, s2) {
        let f = [];
        for (let i = 0; i <= s1.length; i++) {
            f.push([]);
            for (let j = 0; j <= s2.length; j++) {
                f[i].push(0);
            }
        }

        for (let i = 1; i <= s1.length; i++) {
            for (let j = 1; j <= s2.length; j++) {
                if (s1[i - 1] === s2[j - 1]) {
                    f[i][j] = f[i - 1][j - 1] + 1;
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }

        return f[s1.length][s2.length];
    }
};

let View = {
    init: function () {
        View.dom = {
            mainDiv: document.getElementById('main-div')
        };

        View.dom.mainDiv.innerHTML = Template.loading;
    },

    clearMainDiv: function () {
        View.dom.mainDiv.innerHTML = "";
    },

    renderFilter: function () {
        View.dom.mainDiv.innerHTML = View.dom.mainDiv.innerHTML + Template.filter;
    },

    renderProductsContainer: function () {
        View.dom.mainDiv.innerHTML = View.dom.mainDiv.innerHTML + Template.listProduct;
    },

    renderProduct: function (product) {
        let container = document.getElementById("list-product");
        let template = Template.product;
        template = StringProcessor.replaceAll(template, "{id}", product.id);
        template = StringProcessor.replaceAll(template, "{label}", product.label);
        template = StringProcessor.replaceAll(template, "{name}", product.name);
        template = StringProcessor.replaceAll(template, "{link}", product.link);
        template = StringProcessor.replaceAll(template, "{imgLink}", product.imgLink);
        template = StringProcessor.replaceAll(template, "{price}", StringProcessor.formatPrice(product.price));
        template = StringProcessor.replaceAll(template, "{sPrice}", StringProcessor.formatPrice(product.sPrice));
        template = StringProcessor.replaceAll(template, "{promotion}", product.promotion);
        container.innerHTML = container.innerHTML + template;
        View.renderOldProducts(product.id + "-old-product-container", product.oldProduct);
    },

    renderOldProducts: function (containerId, oldProducts) {
        let container = document.getElementById(containerId);
        if (oldProducts.length === 0) {
            container.innerHTML = Template.noOldProduct;
            return;
        }
        for (let i = 0; i < Math.min(3, oldProducts.length); i++) {
            let template = Template.oldProduct;
            template = StringProcessor.replaceAll(template, "{id}", oldProducts[i].id);
            template = StringProcessor.replaceAll(template, "{imgLink}", oldProducts[i].imgLink);
            template = StringProcessor.replaceAll(template, "{title}", oldProducts[i].title);
            template = StringProcessor.replaceAll(template, "{price}", StringProcessor.formatPrice(oldProducts[i].price));
            template = StringProcessor.replaceAll(template, "{content}", oldProducts[i].content);
            template = StringProcessor.replaceAll(template, "{address}", oldProducts[i].address);
            template = StringProcessor.replaceAll(template, "{time}", oldProducts[i].time);
            container.innerHTML = container.innerHTML + template;
        }
    },

    renderProducts: function (products) {
        View.clearMainDiv();
        View.renderFilter();
        View.renderProductsContainer();
        for (let i = 0; i < Math.min(50, products.length); i++) {
            View.renderProduct(products[i]);
        }
    },

    renderSearchKeyword: function (keyword) {
        console.log();
        document.getElementById("filter-input").value = keyword;
    },

    getSearchKeyword: function () {
        return document.getElementById("filter-input").value;
    },

    renderDetailNavigator: function (product) {
        let template = Template.navigator;
        View.dom.mainDiv.innerHTML = View.dom.mainDiv.innerHTML + 
                StringProcessor.replaceAll(template, '{id}', product.id);
    },

    renderProductDetailContainer: function (product) {
        let template = Template.productDetail;
        template = StringProcessor.replaceAll(template, "{id}", product.id);
        template = StringProcessor.replaceAll(template, "{label}", product.label);
        template = StringProcessor.replaceAll(template, "{name}", product.name);
        template = StringProcessor.replaceAll(template, "{link}", product.link);
        template = StringProcessor.replaceAll(template, "{imgLink}", product.imgLink);
        template = StringProcessor.replaceAll(template, "{price}", StringProcessor.formatPrice(product.price));
        template = StringProcessor.replaceAll(template, "{sPrice}", StringProcessor.formatPrice(product.sPrice));
        template = StringProcessor.replaceAll(template, "{promotion}", product.promotion);
        View.dom.mainDiv.innerHTML = View.dom.mainDiv.innerHTML + template;
    },

    renderProductDetailOldProducts: function (products) {
        for (let i = 0; i < products.length; i++) {
            let template = Template.pdOldProduct;
            template = StringProcessor.replaceAll(template, "{id}", products[i].id);
            template = StringProcessor.replaceAll(template, "{link}", products[i].link);
            template = StringProcessor.replaceAll(template, "{imgLink}", products[i].imgLink);
            template = StringProcessor.replaceAll(template, "{title}", products[i].title);
            template = StringProcessor.replaceAll(template, "{price}", StringProcessor.formatPrice(products[i].price));
            template = StringProcessor.replaceAll(template, "{content}", products[i].content);
            template = StringProcessor.replaceAll(template, "{address}", products[i].address);
            template = StringProcessor.replaceAll(template, "{time}", products[i].time);
            document.getElementById("pd-old-products").innerHTML =
                    document.getElementById("pd-old-products").innerHTML + template;
        }
    },

    showProductDetail: function (product) {
        View.clearMainDiv();
        View.renderDetailNavigator(product);
        View.renderProductDetailContainer(product);
        View.renderProductDetailOldProducts(product.oldProduct);
    }
};
