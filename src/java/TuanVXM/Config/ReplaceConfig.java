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
public class ReplaceConfig {
    private String replacement;
    private String target;

    public ReplaceConfig() {
    }

    public ReplaceConfig(String replacement, String target) {
        this.replacement = replacement;
        this.target = target;
    }

    public String getReplacement() {
        return replacement;
    }

    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
