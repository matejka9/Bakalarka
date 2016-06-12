package com.dusky.festival.parcelable;

import java.io.Serializable;

/**
 * Created by dusky on 5/28/16.
 */
public enum InputMenuType implements Serializable {
    MOJE_PODUJATIA("Moje Podujatia"), NAJBLIZSIE_PODUJATIA("Najbližšie Podujatia"), MOJE_VYSTUPENIA("Moje Vystúpenia");

    private final String value;

    InputMenuType(String s) {
        this.value = s;
    }

    public String getValue() {
        return value;
    }
}
