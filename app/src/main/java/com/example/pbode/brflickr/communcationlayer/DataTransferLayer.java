package com.example.pbode.brflickr.communcationlayer;

import java.util.HashMap;
import java.util.Map;

public class DataTransferLayer {

    // Source class, Transfer Object
    private static final Map<Object, TransferObject> data = new HashMap<>();

    public static void addData(Object sourceClass, TransferObject transferObject) {
        data.put(sourceClass, transferObject);
    }

    public static TransferObject getData(Object sourceClass) {
        TransferObject transferObject = data.get(sourceClass);
        data.remove(sourceClass);
        return transferObject;
    }
}