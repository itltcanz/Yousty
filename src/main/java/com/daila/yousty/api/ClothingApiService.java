package com.daila.yousty.api;

import com.daila.yousty.entity.Clothing;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClothingApiService {

    public List<Clothing> getClothing(List<String> urls) {
        String searchUrlBase = "https://yandex.ru/images/search?rpt=imageview&url=";
        List<Clothing> productList = new ArrayList<>();

        for (String url : urls) {
            try {
                // Получение HTML-ответа
                String searchUrl = searchUrlBase + url;
                Document doc = Jsoup.connect(searchUrl).get();

                // Парсинг HTML для извлечения ссыки на товары Wildberries
                Elements linkElements = doc.select("a.Link.EProductSnippetTitle.EProductSnippetTitle_size_c.EProductSnippet-Title[href^='https://www.wildberries.ru/catalog/']");

                if (linkElements.isEmpty()) {
                    linkElements = doc.select("a.Link.EProductSnippetTitle.EProductSnippetTitle_size_c.EProductSnippet-Title[href^='https://www.ozon.ru/product/']");
                }

                if (linkElements.isEmpty()) {
                    continue;
                }

                var productUrl = linkElements.get(0).attr("href");
                String productName = linkElements.get(0).text();
                productList.add(new Clothing(productName, productUrl));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return productList;
    }
}
