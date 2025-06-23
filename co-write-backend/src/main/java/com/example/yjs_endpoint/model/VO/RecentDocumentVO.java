package com.example.yjs_endpoint.model.VO;

import lombok.Data;

import java.util.Date;

@Data
public class RecentDocumentVO {
    private String id;
    private String name;
    private Date accessTime;
}
