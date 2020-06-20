/*
 *  Category bean.
 *  Each product has 1 or many category labels
 *  that are used for filtering in home page called index.jsp.
 */

package model;

public class Category {

    private String category;

    public Category() {
    }

    public void setCategory(String cat) {
        category = cat;
    }

    public String getCategory() {
        return category;
    }
}
