package com.analitrix.sellbook.dto;

import com.analitrix.sellbook.entity.Category;
import lombok.*;

@Data

public class BookDtoPut {
    private String title;
    private Long publicationDate;
    private Long units;
    private String editorial;
    private Long cost;
    private String author;
    private String image;
    private Category category;

    public String toString(){
        return "title: "+title+", publication date: "+getPublicationDate()+", units: "+units+", editorial: "+editorial+", cost: "+cost
                +", author: "+author+", image: "+image+", category: "+category.getId();
    }
}
