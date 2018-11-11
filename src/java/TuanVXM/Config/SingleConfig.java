/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TuanVXM.Config;

/**
 *
 * @author TuanVXM
 */
public class SingleConfig {
    private String tagName;
    private String attrName;
    private String attrValue;
    private String wantedAttr;
    private String value;

    public SingleConfig() {
    }

    public SingleConfig(String tagName, String attrName, String attrValue) {
        this.tagName = tagName;
        this.attrName = attrName;
        this.attrValue = attrValue;
    }

    public SingleConfig(String tagName, String attrName, String attrValue, String wantedAttr) {
        this.tagName = tagName;
        this.attrName = attrName;
        this.attrValue = attrValue;
        this.wantedAttr = wantedAttr;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrValue() {
        return attrValue;
    }

    public void setAttrValue(String attrValue) {
        this.attrValue = attrValue;
    }

    public String getWantedAttr() {
        return wantedAttr;
    }

    public void setWantedAttr(String wantedAttr) {
        this.wantedAttr = wantedAttr;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    
}
