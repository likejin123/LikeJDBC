/**
 1.这是一个entity，用来封装
*/
package com.likejin.jdbc.entity;

import java.math.BigDecimal;

/**
 * @Description  实体类，用于封装数据库查询对象
 * @Author  李柯锦
 * @Date
 */
public class Book {


        Integer id;
        String name;
        BigDecimal price;
        String author;
        Integer sales;
        Integer stock;
        String img_path;

        @Override
        public String toString() {
            return "book{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", price=" + price +
                    ", author='" + author + '\'' +
                    ", sales=" + sales +
                    ", stock=" + stock +
                    ", imgPath='" + img_path + '\'' +
                    '}';
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public Integer getSales() {
            return sales;
        }

        public void setSales(Integer sales) {
            this.sales = sales;
        }

        public Integer getStock() {
            return stock;
        }

        public void setStock(Integer stock) {
            this.stock = stock;
        }

        public String getImgPath() {
            return img_path;
        }

        public void setImgPath(String imgPath) {
            this.img_path = imgPath;
        }


}
