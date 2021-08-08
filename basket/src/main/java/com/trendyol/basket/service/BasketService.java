package com.trendyol.basket.service;

import com.trendyol.basket.domain.Basket;
import com.trendyol.basket.domain.Product;
import com.trendyol.basket.model.BasketResultModel;
import com.trendyol.basket.model.ProductModel;
import com.trendyol.basket.repository.BasketRepository;
import org.json.JSONArray;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

@Service
public class BasketService {
    private final BasketRepository _basketRepository;
    private static HttpURLConnection connection;

    public BasketService(BasketRepository basketRepository)
    {
        _basketRepository = basketRepository;
    }

    public Basket createBasketForCustomer(String customerId) {
        Basket basket = new Basket(customerId);
        return _basketRepository.save(basket);
    }

    public BasketResultModel getCustomersBasketInfo(String customerId)
    {
        //get customers product Id list
        //var productIdList = getProductIdsFromCustomersProductServiceByCustomerId(customerId);

        Basket customersBasket = _basketRepository.findByCustomerId(customerId);
        List<ProductModel> productModelList = new ArrayList<ProductModel>();

        for (Product product: customersBasket.getProductList()) {
            var price = getProductPriceFromProductPriceServiceByProductId(product.getId());
            ProductModel productModel = new ProductModel(product.getId(), product.getTitle(), product.getImage(), product.getQuantity(), price);
            productModelList.add(productModel);
        }
        return  new BasketResultModel(productModelList);
    }

    public void addProductToCustomerBasket(String customerId, long productId)
    {
        //get customers basket
        Basket customersBasket = _basketRepository.findByCustomerId(customerId);
        if(customersBasket == null)
        {
            customersBasket = createBasketForCustomer(customerId);
        }

        //check basket has product by productId
        boolean basketHasProduct = false;
        List<Product> basketProductList = new ArrayList<Product>();
        for (Product product: customersBasket.getProductList()) {
            if(product.getId() == productId)
            {
                basketHasProduct = true;
                basketProductList.add(new Product(product.getId(), product.getTitle(), product.getImage(), product.getQuantity() +1));
            }
            else
            {
                basketProductList.add(new Product(product.getId(), product.getTitle(), product.getImage(), product.getQuantity()));
            }
        }

        if(!basketHasProduct)
        {
            //get productFromProductService detail
            Product productDetail = getProductDetailFromProductServiceResponseStringByProductId(productId);

            if (productDetail.getTitle() != null)
            {
                //add new product to customers basket
                basketProductList.add(new Product(productDetail.getId(), productDetail.getTitle(), productDetail.getImage(),1));

                //add customersProduct
                addCustomersProduct(customerId,productId);
            }
        }
        customersBasket.setProductList(basketProductList);
        _basketRepository.save(customersBasket);
    }

    public void changeProductQuantityFromBasket(String customerId, long productId, int quantity)
    {
        //get customers basket
        Basket customersBasket = _basketRepository.findByCustomerId(customerId);

        List<Product> productList = new ArrayList<Product>();
        for (Product productItem : customersBasket.getProductList()) {
            if (productItem.getId() == productId){
                if (getProductStockFromProductStockServiceByProductId(productId) >= quantity)
                {
                    productList.add(new Product(productItem.getId(), productItem.getTitle(), productItem.getImage(), quantity));
                }
                else
                    System.out.println("Stock is not enough");
            }
            else
                productList.add(new Product(productItem.getId(), productItem.getTitle(), productItem.getImage(), productItem.getQuantity()));
        }
        customersBasket.setProductList(productList);
        _basketRepository.save(customersBasket);
    }

    public void deleteProductFromCustomersBasketById(String customerId, long productId)
    {
        Basket customersBasket = _basketRepository.findByCustomerId(customerId);

        List<Product> productList = new ArrayList<Product>();
        for (Product product: customersBasket.getProductList()) {
            if (product.getId() != productId)
            {
                productList.add(new Product(product.getId(), product.getTitle(), product.getImage(), product.getQuantity()));
            }
        }

        customersBasket.setProductList(productList);
        _basketRepository.save(customersBasket);
        deleteCustomersProduct(customerId, productId);
    }


    private static String callOtherServiceGetMethodByUrl(String url) throws Exception
    {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);
        if (responseCode != 200)
        {
            return "";
        }
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return  response.toString();
    }

    private Product getProductDetailFromProductServiceResponseStringByProductId(long productId )
    {
        try {
            String serviceUrlString = "http://127.0.0.1:8090/product/getProductDetailByProductId?productId="+productId;
            String serviceResponseString = callOtherServiceGetMethodByUrl(serviceUrlString);
            if (serviceResponseString != "")
            {
                JSONObject productObject = new JSONObject(serviceResponseString);
                Product responseProduct = new Product(productObject.getInt("id"), productObject.getString("title"), productObject.getString("image"), 1);
                return  responseProduct;
            }
            System.out.println("Product not found");
            return new Product();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return new Product();
    }

    private int getProductStockFromProductStockServiceByProductId(long productId)
    {
        try {
            String serviceUrlString = "http://127.0.0.1:8086/productStock/getProductStockByProductId?productId="+productId;
            String serviceResponse = callOtherServiceGetMethodByUrl(serviceUrlString);
            return Integer.valueOf(serviceResponse);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    private float getProductPriceFromProductPriceServiceByProductId(long productId)
    {
        try {
            String serviceUrlString = "http://127.0.0.1:8087/productPrice/getProductStockByProductId?productId="+productId;
            String serviceResponseString = callOtherServiceGetMethodByUrl(serviceUrlString);
            JSONObject productPrice = new JSONObject(serviceResponseString);

            return (float) productPrice.getDouble("price");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    private List<Long> getProductIdsFromCustomersProductServiceByCustomerId(String customerId)
    {
        List<Long> customersProductIdList = new ArrayList<Long>();
        try {
            String serviceUrlString = "http://127.0.0.1:8085/customersProduct/getCustomersProductsByCustomerId?customerId=" + customerId;
            String serviceResponseString = callOtherServiceGetMethodByUrl(serviceUrlString);

            JSONArray customersProductJsonResult = new JSONArray(serviceResponseString);

            for (int i = 0; i < customersProductJsonResult.length(); i++) {
                JSONObject customersProduct = customersProductJsonResult.getJSONObject(i);
                customersProductIdList.add(customersProduct.getLong("productId"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return customersProductIdList;
    }

    private void addCustomersProduct(String customerId, long productId)
    {
        try {
            String productServiceUrlString = "http://127.0.0.1:8085/customersProduct/addNewCustomersProduct/"+customerId+"/"+productId;
            callOtherServicePostMethodByUrl(productServiceUrlString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void deleteCustomersProduct(String customerId, long productId)
    {
        try {
            String productServiceUrlString = "http://127.0.0.1:8085/customersProduct/deleteNewCustomersProduct/"+customerId+"/"+productId;
            callOtherServicePostMethodByUrl(productServiceUrlString);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

   private void callOtherServicePostMethodByUrl(String urlString) throws Exception
   {
       URL UrlObj = new URL(urlString);

       HttpURLConnection connection = (HttpURLConnection) UrlObj.openConnection();
       connection.setRequestMethod("POST");
       connection.setRequestProperty("User-Agent", "Mozilla/5.0");
       connection.setDoOutput(true);

       DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
       outputStream.flush();
       outputStream.close();

       System.out.println("Send 'HTTP POST' request to : " + urlString);

       int responseCode = connection.getResponseCode();
       System.out.println("Response Code : " + responseCode);
   }
}
