package com.example.marvelcomics.repository.gateway.images;


import com.example.marvelcomics.repository.gateway.ImageGateway;

import java.util.function.Function;

public enum ImageVersion implements Function<ImageSize, String> {
    STANDARD {
        @Override
        public String apply(ImageSize size) {
            return "standard_" + size;
        }
    }, LANDSCAPE {
        @Override
        public String apply(ImageSize size) {
            return "landscape_" + size;
        }
    }, PORTRAIT {
        @Override
        public String apply(ImageSize size) {
            return "portrait_" + size;
        }
    }
}
