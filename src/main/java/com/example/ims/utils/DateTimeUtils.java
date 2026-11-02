package com.example.ims.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateTimeUtils {
    public static OffsetDateTime now(){
        return OffsetDateTime.now(ZoneOffset.UTC);
    }
}
