<%-- 
    Document   : index
    Created on : Nov 10, 2018, 7:04:59 PM
    Author     : TuanVXM
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Presentation</title>   
        <link href="index.css" rel="stylesheet" >
    </head>
    <body>
        <div id="main-div">
            <div class="filter">
                <div class="filter-left">
                    Tra cứu điện thoại
                </div>
                <div class="filter-right">
                    <input id="filter-input" type="text" placeholder="Nhập tên máy"/>
                    <input id="filter-submit" type="button" value="Tìm kiếm"/>
                </div>
            </div>

            <div class="list-product">
                <div class="product">
                    <div class="product-info">
                        <div class="product-img-container">
                            <img class="product-img" src="http://www.techone.vn/image/cache/upload/data/dienthoai/samsung/a7-2018/a7-1-150x150.jpg"/>
                        </div>                
                        <div class="product-name">Samsung Galaxy A17 2018 công ty</div>
                        <div class="product-label">KM Lớn</div>   
                        <div class="product-price">7.690.000đ</div>
                        <div class="product-sPrice">8.500.000đ</div>
                        <div class="product-promotion">Tặng PMH trị giá 750k (trừ thẳng)</div>
                    </div>
                    <div class="product-list-old">
                        <div class="old-product-container">
                            <div class="old-product">
                                <div class="old-product-img-container">
                                    <img class="old-product-img" src="https://cdn.muaban.net/cdn/images/thumb-list/201811/10/135/a1de1952e2f94305a38b8026c10240ae.jpg"/>
                                </div>
                                <div class="old-product-info">
                                    <div class="old-product-title">
                                        Lên ngay Galaxy A7 128Gb giá chỉ 6.990.000đ tại Tablet Plaza
                                    </div>
                                    <div class="old-product-price">
                                        10.790.000đ
                                    </div>
                                    <div class="old-product-content">
                                        Hàng mới mua đủ hộp và phụ kiện mới toanh bao gồm quà tặng của samsung do đặt máy từ đợt đầu bao gồm: sạc đôi không dây; bao da clear view; cáp hdmi.
                                    </div>
                                    <div class="old-product-address">
                                        Quận Hải Châu - Đà Nẵng
                                    </div>
                                    <div class="old-product-time">
                                        13/11/2018
                                    </div>
                                </div>
                            </div>
                            <div class="old-product"></div>
                            <div class="old-product"></div>
                        </div>
                        <div class="product-detail-container">
                            <input type="button" class="product-detail" value="Xem thêm"/>
                        </div>
                    </div>
                </div>

                <div class="product">
                    <div class="product-info">
                        <div class="product-img-container">

                        </div>                
                        <div class="product-name">Samsung Galaxy A17 2018 công ty mới toanh</div>
                        <div class="product-label">KM Lớn</div>   
                        <div class="product-price">7.690.000đ</div>
                        <div class="product-sPrice">8.500.000đ</div>
                        <div class="product-promotion">Tặng PMH trị giá 750k (trừ thẳng)</div>
                    </div>
                    <div class="product-list-old">

                    </div>
                </div>
            </div>
        </div>

        <script src="View.js"></script>
        <script src="Model.js"></script>
        <script src="Octopus.js"></script>
    </body>
</html>
