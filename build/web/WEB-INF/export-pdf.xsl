<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">
    <xsl:output encoding="UTF-8" indent="yes" method="xml" standalone="no" omit-xml-declaration="no"/>
    
    <xsl:template match="/Product">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="11in" page-width="8.5in"
                                       margin-top="0.5in" margin-bottom="0.5in" margin-left="0.5in" margin-right="0.5in">
                    <fo:region-body margin-top="0in" margin-left="0in" margin-right="0in"/>
                    <fo:region-before precedence="true" display-align="before" extent="0in"/>
                    <fo:region-after extent="0.5in"/>
                    
                </fo:simple-page-master>
            </fo:layout-master-set> 
            
            <fo:page-sequence master-reference="A4">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block/>
                </fo:static-content>
                
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block/>
                </fo:static-content>
                
                <fo:flow flow-name="xsl-region-body" font-family="Arial">
                    
                    <fo:block>
                        Trang tra cứu thông tin điện thoại
                    </fo:block>
                    <fo:block font-size="20pt" text-align="center"
                              margin-top="0.2in" margin-bottom="0.2in">
                        Thông tin máy
                    </fo:block>
                    
                    <fo:table font-size="10pt" table-layout="fixed">
                        <fo:table-column column-width="proportional-column-width(20)"/>
                        <fo:table-column column-width="proportional-column-width(80)"/>
                        <fo:table-body>
                            <fo:table-row >
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        ID:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./id"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Tên máy:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./name"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Link:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./link"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Ảnh:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./imgLink"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Giá:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./price"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Giá gốc:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./sPrice"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Nhãn:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./label"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                            <fo:table-row>
                                <fo:table-cell padding-top="0.1in">
                                    <fo:block>
                                        Khuyễn mãi:
                                    </fo:block>
                                </fo:table-cell>
                                <fo:table-cell padding-top="0.1in">                                   
                                    <fo:block>
                                        <xsl:value-of select="./promotion"/>
                                    </fo:block>
                                </fo:table-cell>
                            </fo:table-row>
                        </fo:table-body>                        
                    </fo:table>                   
                                                            
                    <fo:block margin-top="0.2in" margin-bottom="0.1in">
                        Các rao vặt liên quan:
                    </fo:block>
                    
                    <fo:table font-size="10pt" table-layout="fixed">
                        <fo:table-column column-width="proportional-column-width(100)"/>
                        <fo:table-body>
                            <xsl:apply-templates match="/Product/oldProducts"/>  
                        </fo:table-body>
                    </fo:table>
                                   
                </fo:flow>
            </fo:page-sequence>       
        </fo:root>
    </xsl:template>
    <xsl:template match="/Product/oldProducts">                            
        <fo:table-row border-style="solid" border-width="0.5pt">
            <fo:table-cell padding="2mm">
                <fo:table font-size="10pt" table-layout="fixed">
                    <fo:table-column column-width="proportional-column-width(20)"/>
                    <fo:table-column column-width="proportional-column-width(80)"/>
                    <fo:table-body>
                        <fo:table-row>
                            <fo:table-cell>
                                <fo:block>
                                    ID:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell>                                   
                                <fo:block>
                                    <xsl:value-of select="./id"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Tiêu đề:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./title"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Link:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./link"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Ảnh:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./imgLink"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Giá:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./price"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Địa chỉ:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./address"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                        <fo:table-row>
                            <fo:table-cell padding-top="0.05in">
                                <fo:block>
                                    Thời gian đăng:
                                </fo:block>
                            </fo:table-cell>
                            <fo:table-cell padding-top="0.05in">                                   
                                <fo:block>
                                    <xsl:value-of select="./time"/>
                                </fo:block>
                            </fo:table-cell>
                        </fo:table-row>
                    </fo:table-body>
                </fo:table>
            </fo:table-cell>
        </fo:table-row>    
    </xsl:template>

</xsl:stylesheet>