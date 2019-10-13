package by.koshko.cyberwikia.service;

import by.koshko.cyberwikia.bean.RawData;

public interface ImageService {
    String save(RawData rawData);
    boolean delete(String relativePath);
}
