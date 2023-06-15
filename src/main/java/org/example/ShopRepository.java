package org.example;

public class ShopRepository {
    private Product[] products = new Product[0];


    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }


    public void add(Product product) {
        if (findById(product.getId()) != null) {
            throw new RuntimeException("Продукт с  ID: " + product.getId() + " уже существует");
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }


    public void removeById(int id) {
        Product productToRemove = findById(id);
        if (productToRemove == null) { // проверяем, есть ли искомый товар с данным Id
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }

    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}