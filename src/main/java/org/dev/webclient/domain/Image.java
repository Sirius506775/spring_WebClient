package org.dev.webclient.domain;

import lombok.Data;

@Data
public class Image {

    private int image_id;
    private String image_type;
    private String image_size;
    private String image_name;
    private byte[] image;
}
