/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

let Template = {
    loading: '<h1 class="red">Loading!</h1>'
}

let View = {
    init: function () {
        View.dom = {
            mainDiv: document.getElementById('main-div')
        };
        
        //View.dom.mainDiv.innerHTML = Template.loading;
    },

    renderProducts: function (products) {
        //View.dom.mainDiv.innerHtml = "";
    }
};
